package softuni.likebooks.model.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.likebooks.model.entity.Mood;
import softuni.likebooks.model.entity.User;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class PostDTO {


    private Long id;

    private String content;

    private User creator;

    private Set<User> usersLikes;

    private Mood mood;

}
