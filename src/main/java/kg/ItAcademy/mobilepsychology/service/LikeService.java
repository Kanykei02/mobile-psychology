package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Like;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.LikeModel;

import java.util.List;

public interface LikeService {
    Like save (Like like);
    Like save(LikeModel likeModel) throws ObjectNotFoundException;
    List<Like> getAllLikes();
    Like findById(Long id) throws ObjectNotFoundException;
    List<Like> findAllByUsername(String name);
    void deleteById(Long id);
}
