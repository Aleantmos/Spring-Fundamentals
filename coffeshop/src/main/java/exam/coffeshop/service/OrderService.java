package exam.coffeshop.service;

import exam.coffeshop.model.dtos.AddOrderDTO;
import exam.coffeshop.model.entity.Category;
import exam.coffeshop.model.entity.Order;
import exam.coffeshop.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    public void addOrder(AddOrderDTO addOrderDTO) {
        Order orderToAdd = modelMapper.map(addOrderDTO, Order.class);

        Category category = categoryService.findCategoryByName(addOrderDTO.getCategoryEnum());

        orderToAdd.setCategory(category);

        orderRepository.save(orderToAdd);

    }
}
