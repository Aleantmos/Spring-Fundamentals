package softuni.shoppinglist.model.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor

public class CategoryTypeProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long ownerId;
}
