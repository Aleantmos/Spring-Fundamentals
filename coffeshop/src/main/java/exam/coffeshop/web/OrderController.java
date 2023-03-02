package exam.coffeshop.web;

import exam.coffeshop.model.dtos.AddOrderDTO;
import exam.coffeshop.model.helper.LoggedUser;
import exam.coffeshop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final LoggedUser loggedUser;
    private final OrderService orderService;

    @Autowired
    public OrderController(LoggedUser loggedUser, OrderService orderService) {
        this.loggedUser = loggedUser;
        this.orderService = orderService;
    }

    @GetMapping("/add-order")
    public String getAddOrder() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        return "order-add";
    }
    @RequestMapping("/add-order")
    public String postAddOrder(@Valid AddOrderDTO addOrderDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addOrderDTO", addOrderDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOrderDTO", bindingResult);

            return "redirect:add-order";
        }

        orderService.addOrder(addOrderDTO);

        return "redirect:/home";
    }

    @ModelAttribute
    public AddOrderDTO addOrderDTO() {
        return new AddOrderDTO();
    }

}
