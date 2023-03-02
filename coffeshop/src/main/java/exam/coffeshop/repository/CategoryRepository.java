package exam.coffeshop.repository;

import exam.coffeshop.model.entity.Category;
import exam.coffeshop.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(CategoryEnum categoryEnum);
}
