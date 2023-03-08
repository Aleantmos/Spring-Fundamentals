package softuni.likebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.likebooks.model.entity.Post;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Set<Post> findByCreator_Id(Long id);

    Set<Post> findByCreator_IdNot(Long id);
}
