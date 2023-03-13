package softuni.spotify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.spotify.model.entity.Style;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
}
