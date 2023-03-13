package softuni.spotify.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.spotify.model.enums.StyleEnum;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private StyleEnum styleName;

    @Column
    private String description;
}
