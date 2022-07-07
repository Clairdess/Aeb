package starcode.aeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import starcode.aeb.domain.User;
import starcode.aeb.repo.UserRepository;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login?error")
    public String loginPageError(Model model) {
        model.addAttribute("error", true);
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        if (user.getEmail() == null || user.getPassword() == null || user.getRole() == null) {
            model.addAttribute("error", true);
            return "registration";
        }
        try {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                model.addAttribute("error", true);
                return "registration";
            }
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(user.getPassword());

            user.setPassword(password);
            userRepository.save(user);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", true);
            return "registration";
        }
    }
}