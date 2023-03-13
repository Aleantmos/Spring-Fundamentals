package softuni.shoppinglist.model.dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.shoppinglist.model.enums.CategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AddProductDTO {

    @Size(min = 3, max = 20)
    @NotBlank
    private String name;

    @Size(min = 5)
    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime neededBefore;

    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @NotNull
    private CategoryEnum category;
}
