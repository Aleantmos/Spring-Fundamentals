package softuni.likebooks.model.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.likebooks.model.enums.MoodEnum;

@Getter
@Setter
@NoArgsConstructor
public class AddPostDTO {

    @Size(min = 2, max = 50, message = "Content length must be between 2 and 50 characters!")
    private String content;

    @NotNull(message = "You must select a mood!")
    private MoodEnum mood;
}
