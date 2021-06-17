package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureService {
    Picture createPicture(MultipartFile multipartFile);
    List<Picture> getAllPictures();
    Picture getById(Long id);
}
