package softuni.likebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.likebooks.model.entity.Mood;
import softuni.likebooks.model.enums.MoodEnum;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {

    Mood findByMoodName(MoodEnum moodEnum);
}
