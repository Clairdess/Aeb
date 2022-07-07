package starcode.aeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import starcode.aeb.domain.Doc;
import starcode.aeb.domain.User;
import starcode.aeb.repo.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class PageController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String registration(Model model, @AuthenticationPrincipal OAuth2User oAuth2User,
                                Principal user) {
        if (oAuth2User != null) {
            String email = (String) oAuth2User.getAttributes().get("email");
            System.out.println(oAuth2User.getAttributes().get("email"));
            User userFromDB = userRepository.findByEmail(email).orElseThrow();
            model.addAttribute("user", userFromDB);
            if (userFromDB.getRole().name().equals("ADMIN")) {
                return "profile/account-mentor";
            }
            if (userFromDB.getRole().name().equals("USER")) {
                return "profile/account-student";
            }
            return "redirect/";
        } else {
            String email = user.getName();
            System.out.println(email);
            User userFromDB = userRepository.findByEmail(email).orElseThrow();
            model.addAttribute("user", userFromDB);
            if (userFromDB.getRole().name().equals("ADMIN")) {
                return "profile/account-mentor";
            }
            if (userFromDB.getRole().name().equals("USER")) {
                return "profile/account-student";
            }
            return "redirect/";
        }

    }

    @PostMapping("/profile")
    public String getData(@ModelAttribute User user, Model model) {
        try {
            User userFromDb = userRepository.findByEmail(user.getEmail()).orElseThrow();
            userFromDb.setEmail(user.getEmail());
            userFromDb.setFirstName(user.getFirstName());
            userFromDb.setLastName(user.getLastName());
            userFromDb.setPatronymic(user.getPatronymic());
            userFromDb.setPhone(user.getPhone());
            userRepository.save(userFromDb);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "/";
        }
    }

    @GetMapping("/interns")
    public String interns(Model model, Principal user) {
        if (user != null) {
            return "interns";
        }
        return "login";
    }

    @GetMapping("/download/doc")
    @ResponseBody
    public FileSystemResource downloadFile() {
        return new FileSystemResource(new File("src/main/resources/static/example.doc"));
    }
}
