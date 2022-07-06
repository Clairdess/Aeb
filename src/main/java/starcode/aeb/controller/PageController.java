package starcode.aeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import starcode.aeb.domain.User;
import starcode.aeb.repo.UserRepository;

import java.security.Principal;

@Controller
public class PageController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration(Model model, Principal user) {
        model.addAttribute("profile", "SomeInterestingValue");
        if (user != null) {
            String name = user.getName();
            if (userRepository.findByEmail(name).isPresent()) {
                User newUser = userRepository.findByEmail(name).get();
                System.out.println(newUser);
                if ("USER".equals(newUser.getRole().name())) {
                    return "profile/account-student";
                }
                if ("ADMIN".equals(newUser.getRole().name())) {
                    return "profile/account-mentor";
                }
            }
        }
        return "registration";
    }

    @GetMapping("/interns")
    public String interns(Model model, Principal user) {
        if (user != null) {


        }
        return "login";
    }
}
