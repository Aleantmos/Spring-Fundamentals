package exam.coffeshop.web;

import exam.coffeshop.model.dtos.LoginDTO;
import exam.coffeshop.model.dtos.RegisterDTO;
import exam.coffeshop.model.helper.LoggedUser;
import exam.coffeshop.service.UserService;
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

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final LoggedUser loggedUser;


    @Autowired
    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid RegisterDTO registerDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userService.checkUniquenessAndRegister(registerDTO)) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return "redirect:register";
        }

        return "redirect:login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {

        if (!model.containsAttribute("notFound")) {
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid LoginDTO loginDTO, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:login";
        }

        if (!userService.loginSuccessfully(loginDTO)){
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("notFound", true);

            return "redirect:login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        loggedUser.setId(null);
        loggedUser.setUsername(null);
        return "redirect:/";
    }

    @ModelAttribute
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }

    @ModelAttribute
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }


}
