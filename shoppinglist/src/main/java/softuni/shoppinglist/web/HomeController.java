package softuni.shoppinglist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.shoppinglist.model.helper.LoggedUser;

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
            return "redirect:/";
        }
        return "home";
    }
}
