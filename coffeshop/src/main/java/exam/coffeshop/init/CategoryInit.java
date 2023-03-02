package exam.coffeshop.init;

import exam.coffeshop.model.entity.Category;
import exam.coffeshop.model.enums.CategoryEnum;
import exam.coffeshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategoryInit implements CommandLineRunner {

    private final CategoryService categoryService;

    @Autowired
    public CategoryInit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryService.getCategoryRepoCount() == 0) {
            Arrays.stream(CategoryEnum.values())
                    .forEach(categoryEnum -> {
                        Category category = new Category();
                        category.setName(categoryEnum);

                        switch (categoryEnum) {
                            case CAKE -> category.setNeededTime(10);
                            case COFFEE -> category.setNeededTime(2);
                            case DRINK -> category.setNeededTime(1);
                            case OTHER -> category.setNeededTime(5);
                        }
                        categoryService.saveCategory(category);
                    });

        }
    }
}
