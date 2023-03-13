package softuni.shoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.shoppinglist.model.entity.Product;
import softuni.shoppinglist.model.enums.CategoryEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional <Product> findByName(String name);

    List<Product> findByOwner_IdAndCategory_Name(Long ownerId, CategoryEnum categoryEnum);
}
