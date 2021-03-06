package kg.ItAcademy.mobilepsychology.repository;

import kg.ItAcademy.mobilepsychology.entity.PostPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostPictureRepository extends JpaRepository<PostPicture, Long> {
}
