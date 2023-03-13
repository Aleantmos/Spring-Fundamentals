package softuni.shoppinglist.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.shoppinglist.model.dtos.AddProductDTO;
import softuni.shoppinglist.model.dtos.CategoryTypeProductDTO;
import softuni.shoppinglist.model.entity.Category;
import softuni.shoppinglist.model.entity.Product;
import softuni.shoppinglist.model.entity.User;
import softuni.shoppinglist.model.enums.CategoryEnum;
import softuni.shoppinglist.model.helper.LoggedUser;
import softuni.shoppinglist.repository.CategoryRepository;
import softuni.shoppinglist.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final LoggedUser loggedUser;
    @Autowired
    public ProductService(ModelMapper modelMapper, ProductRepository productRepository, CategoryRepository categoryRepository, CategoryService categoryService, UserService userService, LoggedUser loggedUser) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    public boolean addProduct(AddProductDTO addProductDTO) {
        if (checkNameUniqueness(addProductDTO.getName())) {
            Product productToAdd = modelMapper.map(addProductDTO, Product.class);

            Category category =
                    categoryService.getCategoryByName(addProductDTO.getCategory());

            productToAdd.setCategory(category);

            User owner = userService.findUserById(loggedUser.getId());
            productToAdd.setOwner(owner);
            productRepository.save(productToAdd);

            return true;
        }
        return false;
    }

    public List<CategoryTypeProductDTO> getList(Long id, CategoryEnum categoryType) {
        return getCategoryTypeForOwner(id, categoryType);
    }

    private List<CategoryTypeProductDTO> getCategoryTypeForOwner(Long id, CategoryEnum categoryEnum) {

        return productRepository.findByOwner_IdAndCategory_Name(id, categoryEnum)
                .stream()
                .map(type -> {
                    CategoryTypeProductDTO categoryTypeProductDTO = modelMapper.map(type, CategoryTypeProductDTO.class);

                    categoryTypeProductDTO.setOwnerId(loggedUser.getId());
                    return categoryTypeProductDTO;
                })
                .collect(Collectors.toList());
    }

    private boolean checkNameUniqueness(String name) {
        return productRepository.findByName(name).isEmpty();
    }

    public void buyProduct(Long id) {
        productRepository.deleteById(id);
    }

    public BigDecimal priceOfAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void buyAll() {
        productRepository.deleteAll();
    }
}
