package softuni.shoppinglist.model.entity;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private CategoryEnum name;

    @Column(nullable = false, columnDefinition = "Text")
    private String description;
}
