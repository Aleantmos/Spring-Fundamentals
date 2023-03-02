package exam.coffeshop.service;

import exam.coffeshop.model.entity.Category;
import exam.coffeshop.model.enums.CategoryEnum;
import exam.coffeshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public long getCategoryRepoCount() {
        return categoryRepository.count();
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category findCategoryByName(CategoryEnum categoryEnum) {

        return categoryRepository.findByName(categoryEnum);
    }
}
