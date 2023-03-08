package softuni.likebooks.model.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDTO {
    @NotNull
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters (inclusive of 3 and 20).")
    private String username;
    @NotNull
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters (inclusive of 3 and 20).")
    private String password;
}
