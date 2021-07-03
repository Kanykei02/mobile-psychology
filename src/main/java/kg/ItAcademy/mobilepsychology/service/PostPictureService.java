package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.PostPicture;
import kg.ItAcademy.mobilepsychology.model.PostPictureModel;

import java.util.List;

public interface PostPictureService {
    PostPicture create(PostPicture postPicture);
    PostPicture create(PostPictureModel postPictureModel);
    PostPicture findPostPictureById(Long id);
    List<PostPicture> getAllPostPicture();
    void deleteById(Long id);
}
