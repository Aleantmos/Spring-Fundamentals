package exam.coffeshop.model.dtos;

import exam.coffeshop.model.entity.Category;
import exam.coffeshop.model.enums.CategoryEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor

public class AddOrderDTO {
    @Size(min = 3, max = 20)
    @NotBlank
    private String name;

    @Positive
    @NotNull
    private BigDecimal price;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;

    @NotNull
    private CategoryEnum categoryEnum;

    @Size(min = 5)
    @NotBlank
    private String description;
}
