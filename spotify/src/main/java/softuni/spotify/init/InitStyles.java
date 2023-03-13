package softuni.spotify.init;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softuni.spotify.model.entity.Style;
import softuni.spotify.model.enums.StyleEnum;
import softuni.spotify.service.StyleService;

import java.util.Arrays;

@Component
public class InitStyles {

    private final StyleService styleService;

    @Autowired
    public InitStyles(StyleService styleService) {
        this.styleService = styleService;
    }

    @PostConstruct
    public void initStyles() {
        if (styleService.getRepoCnt() == 0) {

            Arrays.stream(StyleEnum.values())
                    .forEach(styleEnum -> {
                        Style style = new Style();
                        style.setStyleName(styleEnum);

                        styleService.saveStyle(style);
                    });

        }
    }
}
