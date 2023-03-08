package softuni.likebooks.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.likebooks.model.dtos.AddPostDTO;
import softuni.likebooks.service.PostService;

@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
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

    @ModelAttribute
    public AddPostDTO addPostDTO() {
        return new AddPostDTO();
    }


}
