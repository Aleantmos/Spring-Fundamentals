package exam.coffeshop.service;

import exam.coffeshop.model.dtos.AddOrderDTO;
import exam.coffeshop.model.dtos.EmployeeWithOrdersDTO;
import exam.coffeshop.model.dtos.OrderDTO;
import exam.coffeshop.model.entity.Category;
import exam.coffeshop.model.entity.Order;
import exam.coffeshop.model.helper.LoggedUser;
import exam.coffeshop.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final LoggedUser loggedUser;
    private final UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper, CategoryService categoryService, LoggedUser loggedUser, UserService userService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    public void addOrder(AddOrderDTO addOrderDTO) {
        Order orderToAdd = modelMapper.map(addOrderDTO, Order.class);

        Category category = categoryService.findCategoryByName(addOrderDTO.getCategoryEnum());

        orderToAdd.setCategory(category);
        orderToAdd.setEmployee(userService.findUserByUserId(loggedUser.getId()));

        orderRepository.save(orderToAdd);

    }

    public List<OrderDTO> getAllOrdersWithPriceDesc() {

        return orderRepository.getAllOrderByPriceDesc()
                .stream()
                .map(order -> {
                    OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
                    orderDTO.setCategory(order.getCategory().getName());
                    orderDTO.setNeededTime(order.getCategory().getNeededTime());
                    return orderDTO;
                })
                .collect(Collectors.toList());
    }

    public int getTotalTimeNeeded(List<OrderDTO> ordersByPriceDesc) {

        return ordersByPriceDesc.stream()
                .mapToInt(OrderDTO::getNeededTime)
                .sum();
    }

    public EmployeeWithOrdersDTO getEmployeeWithOrdersDTO() {
        List<Order> orderByEmployeeUsername = orderRepository.getOrderByEmployee_Username(loggedUser.getUsername());

        EmployeeWithOrdersDTO employeeWithOrdersDTO = new EmployeeWithOrdersDTO();
        employeeWithOrdersDTO.setUsername(loggedUser.getUsername());
        employeeWithOrdersDTO.setCountOfOrders(orderByEmployeeUsername.size());

        return employeeWithOrdersDTO;
    }

    public void completeTheOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
