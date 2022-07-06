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
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping("/registration-error")
    public String registerError(Model model) {
        model.addAttribute("error", true);
        return "register";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String getSuccessPage(@ModelAttribute User user, Model model) {
        if (user.getEmail() == null || user.getPassword() == null || user.getRole() == null) {
            model.addAttribute("error", true);
            return "registration";
        }
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(password);
            userRepository.save(user);
            return "redirect:/login";
        } catch (Exception e) {
            // Ошибка регистрации
            model.addAttribute("error", true);
            return "register";
        }
    }
}