package softuni.likebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.likebooks.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
