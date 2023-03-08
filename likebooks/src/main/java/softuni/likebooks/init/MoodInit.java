package softuni.likebooks.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.likebooks.model.entity.Mood;
import softuni.likebooks.model.enums.MoodEnum;
import softuni.likebooks.service.MoodService;

import java.util.Arrays;

@Component
public class MoodInit implements CommandLineRunner {

    private final MoodService moodService;

    @Autowired
    public MoodInit(MoodService moodService) {
        this.moodService = moodService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (moodService.findMoodRepoCount() == 0) {

            Arrays.stream(MoodEnum.values())
                    .forEach(moodEnum -> {
                        Mood mood = new Mood();
                        mood.setMoodName(moodEnum);
                        mood.setDescription("type description here...");

                        moodService.saveMood(mood);
                    });

        }
    }
}
