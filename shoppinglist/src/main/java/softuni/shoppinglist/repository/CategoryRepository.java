package softuni.shoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.shoppinglist.model.entity.Category;
import softuni.shoppinglist.model.entity.Product;
import softuni.shoppinglist.model.enums.CategoryEnum;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryEnum categoryEnum);
}
