package starcode.aeb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import starcode.aeb.CustomOAuth2UserService;
import starcode.aeb.domain.CustomOAuth2User;
import starcode.aeb.domain.User;
import starcode.aeb.repo.UserRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;


    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/auth/registration", "/login**", "/css/**", "/js/**", "/plugins/**", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin().permitAll()
                    .loginPage("/auth/login")
                    .failureForwardUrl("/auth/login?error").permitAll()
                    .defaultSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .loginPage("/auth/login")
                    .userInfoEndpoint()
                    .userService(oauthUserService)
                    .and()
                    .successHandler((request, response, authentication) -> {
                        DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
                        String email = oauthUser.getAttribute("email");
                        String name = oauthUser.getAttribute("name");
                        User newUser = new User();
                        newUser.setEmail(email);
                        newUser.setFirstName(name);
                        userRepository.save(newUser);
                        response.sendRedirect("/");
                    })
                    .failureHandler((request, response, exception) ->
                            response.sendRedirect("/")
                    )
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    private CustomOAuth2UserService oauthUserService;
    @Autowired
    public void setOauthUserService(CustomOAuth2UserService oauthUserService) {
        this.oauthUserService = oauthUserService;
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}