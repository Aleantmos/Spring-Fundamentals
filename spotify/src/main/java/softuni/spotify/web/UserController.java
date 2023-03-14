package softuni.spotify.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.spotify.model.dtos.LoginDTO;
import softuni.spotify.model.dtos.RegisterDTO;
import softuni.spotify.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid RegisterDTO registerDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userService.registerUser(registerDTO)) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return "redirect:register";
        }

        return "redirect:login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {

        if (model.containsAttribute("notFound")) {
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid LoginDTO loginDTO, RedirectAttributes redirectAttributes,
                            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:login";
        }

        if (!userService.loginUser(loginDTO)) {
            model.addAttribute("notFound", true);
            return "redirect:login";
        }

        return "redirect:/";
    }

    @ModelAttribute
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }

    @ModelAttribute
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }
}
