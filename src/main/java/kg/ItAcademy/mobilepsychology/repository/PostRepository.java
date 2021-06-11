package kg.ItAcademy.mobilepsychology.repository;

import kg.ItAcademy.mobilepsychology.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByPsychologistId_Username(String username);
}
