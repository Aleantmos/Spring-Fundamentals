package softuni.likebooks.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private User creator;

    @OneToMany
    private Set<User> usersLikes;

    @ManyToOne
    private Mood mood;
}
