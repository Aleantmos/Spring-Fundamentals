package exam.coffeshop.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class RegisterDTO {

    @Size(min = 5, max = 20)
    @NotBlank
    private String username;


    private String firstName;

    @Size(min = 5, max = 20)
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @Size(min = 3)
    @NotBlank
    private String password;

    @Size(min = 3)
    @NotBlank
    private String confirmPassword;
}
