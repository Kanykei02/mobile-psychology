package kg.ItAcademy.mobilepsychology.repository;

import kg.ItAcademy.mobilepsychology.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findAllByUrl (String url);
}
