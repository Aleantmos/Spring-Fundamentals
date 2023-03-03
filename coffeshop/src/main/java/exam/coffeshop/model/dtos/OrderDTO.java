package exam.coffeshop.model.dtos;

import exam.coffeshop.model.enums.CategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor

public class OrderDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    private CategoryEnum category;

    private int neededTime;


}
