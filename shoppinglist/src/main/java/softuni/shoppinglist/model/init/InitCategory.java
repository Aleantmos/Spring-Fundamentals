package softuni.shoppinglist.model.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softuni.shoppinglist.model.entity.Category;
import softuni.shoppinglist.model.enums.CategoryEnum;
import softuni.shoppinglist.service.CategoryService;

import java.util.Arrays;

@Component
public class InitCategory implements Runnable {

    private final CategoryService categoryService;

    @Autowired
    public InitCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run() {
        if (categoryService.getRepoCount() == 0) {
            Arrays.stream(CategoryEnum.values())
                    .forEach(categoryEnum -> {
                        Category category = new Category();
                        category.setName(categoryEnum);
                        category.setDescription("type description here...");

                        categoryService.saveCategory(category);
                    });
        }
    }
}
