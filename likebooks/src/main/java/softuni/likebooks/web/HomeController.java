package softuni.likebooks.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.likebooks.model.dtos.PostDTO;
import softuni.likebooks.model.entity.Post;
import softuni.likebooks.model.helper.LoggedUser;
import softuni.likebooks.service.PostService;

import java.util.Set;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final PostService postService;

    @Autowired
    public HomeController(LoggedUser loggedUser, PostService postService) {
        this.loggedUser = loggedUser;
        this.postService = postService;
    }

    @GetMapping("/")
    public String getIndex() {
        if (loggedUser.isLogged()) {
            return "redirect:home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:index";
        }

        Set<PostDTO> myPosts = postService.getPostsWithCreator(loggedUser.getId());
        model.addAttribute("myPosts", myPosts);

        Set<PostDTO> othersPosts = postService.getPostsWithCreatorNot(loggedUser.getId());
        model.addAttribute("othersPosts", othersPosts);
        return "home";
    }
}
