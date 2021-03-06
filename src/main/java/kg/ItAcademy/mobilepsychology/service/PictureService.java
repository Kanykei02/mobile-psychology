package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Picture;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureService {
    Picture createPicture(MultipartFile multipartFile);
    List<Picture> getAllPictures();
    Picture findById(Long id) throws ObjectNotFoundException;
    List<Picture> findAllByUrl(String url);
    void deleteByUrl(String url);
}
