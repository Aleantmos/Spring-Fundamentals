package exam.coffeshop.web;

import exam.coffeshop.model.dtos.EmployeeWithOrdersDTO;
import exam.coffeshop.model.dtos.OrderDTO;
import exam.coffeshop.model.helper.LoggedUser;
import exam.coffeshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final OrderService orderService;

    @Autowired
    public HomeController(LoggedUser loggedUser, OrderService orderService) {
        this.loggedUser = loggedUser;
        this.orderService = orderService;
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

        List<OrderDTO> ordersByPriceDesc = orderService.getAllOrdersWithPriceDesc();
        model.addAttribute("ordersByPriceDesc", ordersByPriceDesc);

        int timeNeeded = orderService.getTotalTimeNeeded(ordersByPriceDesc);
        model.addAttribute("timeNeeded", timeNeeded);

        //model.addAttribute("loggedUser", loggedUser);

        EmployeeWithOrdersDTO employeesWithOrdersDTO = orderService.getEmployeeWithOrdersDTO();
        List<EmployeeWithOrdersDTO> employeesWithOrders = new ArrayList<>();
        employeesWithOrders.add(employeesWithOrdersDTO);
        model.addAttribute("employeesWithOrdersDTO", employeesWithOrders);

        return "home";
    }
}
