package softuni.likebooks.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.likebooks.model.dtos.AddPostDTO;
import softuni.likebooks.model.helper.LoggedUser;
import softuni.likebooks.service.PostService;

@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostService postService;
    private final LoggedUser loggedUser;

    @Autowired
    public PostController(PostService postService, LoggedUser loggedUser) {
        this.postService = postService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/add-post")
    public String getAddPost() {

        return "post-add";
    }

    @PostMapping("/add-post")
    public String postAddPost(@Valid AddPostDTO addPostDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addPostDTO", addPostDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addPostDTO", bindingResult);
            return "redirect:add-post";
        }

        postService.addPost(addPostDTO);

        return "redirect:/";
    }


    @GetMapping("/like-post/{id}")
    public String likePost(@PathVariable Long id) {
        postService.addLiker(id, loggedUser.getId());

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removePost(@PathVariable Long id) {
        postService.removePostById(id);

        return "redirect:/home";
    }
    @ModelAttribute
    public AddPostDTO addPostDTO() {
        return new AddPostDTO();
    }


}
