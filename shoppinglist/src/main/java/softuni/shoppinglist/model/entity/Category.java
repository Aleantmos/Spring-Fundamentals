package softuni.shoppinglist.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.shoppinglist.model.enums.CategoryEnum;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(unique = true)
    private CategoryEnum name;

    @Column(nullable = false, columnDefinition = "Text")
    private String description;
}
