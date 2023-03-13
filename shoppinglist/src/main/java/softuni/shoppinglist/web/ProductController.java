package softuni.shoppinglist.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.shoppinglist.model.dtos.AddProductDTO;
import softuni.shoppinglist.model.helper.LoggedUser;
import softuni.shoppinglist.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;
    private final LoggedUser loggedUser;


    @Autowired
    public ProductController(ProductService productService, LoggedUser loggedUser) {
        this.productService = productService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/add-product")
    public String getAddProduct() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        return "product-add";
    }

    @PostMapping("/add-product")
    public String postAddProduct(@Valid AddProductDTO addProductDTO, RedirectAttributes redirectAttributes,
                                 BindingResult bindingResult) {


        if (bindingResult.hasErrors() || !productService.addProduct(addProductDTO)) {
            redirectAttributes
                    .addFlashAttribute("addProductDTO", addProductDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addProductDTO", bindingResult);

            return "redirect:add-product";
        }

        return "redirect:/";
    }


    @GetMapping("/buy{id}")
    public String buyProduct(@PathVariable Long id) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        productService.buyProduct(id);

        return "redirect:/";
    }

    @GetMapping("/buyAll")
    public String buyAll() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        productService.buyAll();

        return "redirect:/home";
    }

    @ModelAttribute
    public AddProductDTO addProductDTO() {
        return new AddProductDTO();
    }
}
