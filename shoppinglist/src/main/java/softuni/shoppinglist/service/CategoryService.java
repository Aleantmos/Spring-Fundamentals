package softuni.shoppinglist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.shoppinglist.model.entity.Category;
import softuni.shoppinglist.model.enums.CategoryEnum;
import softuni.shoppinglist.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public long getRepoCount() {
        return categoryRepository.count();
    }

    public Category getCategoryByName(CategoryEnum category) {
        return categoryRepository.findByName(category);
    }
}
