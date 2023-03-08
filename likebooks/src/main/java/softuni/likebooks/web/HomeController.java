package softuni.likebooks.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.likebooks.model.helper.LoggedUser;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;

    @Autowired
    public HomeController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public String getIndex() {
        if (loggedUser.isLogged()) {
            return "redirect:home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String getHome() {
        if (!loggedUser.isLogged()) {
            return "redirect:index";
        }

        return "home";
    }
}
