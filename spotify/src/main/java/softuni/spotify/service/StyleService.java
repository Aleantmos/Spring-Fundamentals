package softuni.spotify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.spotify.model.entity.Style;
import softuni.spotify.repository.StyleRepository;

@Service
public class StyleService {

    private final StyleRepository styleRepository;

    @Autowired
    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public long getRepoCnt() {
        return styleRepository.count();
    }

    public void saveStyle(Style style) {
        styleRepository.save(style);
    }
}
