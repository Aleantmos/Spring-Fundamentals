package softuni.shoppinglist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.shoppinglist.model.dtos.CategoryTypeProductDTO;
import softuni.shoppinglist.model.enums.CategoryEnum;
import softuni.shoppinglist.model.helper.LoggedUser;
import softuni.shoppinglist.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final ProductService productService;

    @Autowired
    public HomeController(LoggedUser loggedUser, ProductService productService) {
        this.loggedUser = loggedUser;
        this.productService = productService;
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
            return "redirect:/";
        }

        BigDecimal buyAll = productService.priceOfAllProducts();
        model.addAttribute("totalPrice", buyAll);


        List<CategoryTypeProductDTO> foods = productService.getList(loggedUser.getId(), CategoryEnum.FOOD);
        model.addAttribute("foods", foods);

        List<CategoryTypeProductDTO> drinks = productService.getList(loggedUser.getId(), CategoryEnum.DRINK);
        model.addAttribute("drinks", drinks);

        List<CategoryTypeProductDTO> households = productService.getList(loggedUser.getId(), CategoryEnum.HOUSEHOLD);
        model.addAttribute("households", households);

        List<CategoryTypeProductDTO> others = productService.getList(loggedUser.getId(), CategoryEnum.OTHER);
        model.addAttribute("others", others);
        return "home";
    }
}
