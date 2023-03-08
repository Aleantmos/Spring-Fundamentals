package softuni.likebooks.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.likebooks.model.enums.MoodEnum;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "moods")
public class Mood extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private MoodEnum moodName;

    @Column(columnDefinition = "TEXT")
    private String description;
}
