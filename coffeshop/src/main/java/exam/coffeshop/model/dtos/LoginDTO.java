package exam.coffeshop.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LoginDTO {
    @Size(min = 5, max = 20)
    @NotBlank
    private String username;
    @Size(min = 3)
    @NotBlank
    private String password;
}
