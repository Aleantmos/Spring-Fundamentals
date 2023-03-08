package softuni.likebooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.likebooks.model.entity.Mood;
import softuni.likebooks.model.enums.MoodEnum;
import softuni.likebooks.repository.MoodRepository;

@Service
public class MoodService {

    private final MoodRepository moodRepository;

    @Autowired
    public MoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    public long findMoodRepoCount() {
        return moodRepository.count();
    }

    public void saveMood(Mood mood) {
        moodRepository.save(mood);
    }

    public Mood getMood(MoodEnum mood) {
        return moodRepository.findByMoodName(mood);
    }
}
