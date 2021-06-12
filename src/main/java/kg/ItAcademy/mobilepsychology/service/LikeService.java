package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Like;
import kg.ItAcademy.mobilepsychology.model.LikeModel;

import java.util.List;

public interface LikeService {
    Like save (Like like);
    Like save(LikeModel likeModel);
    List<Like> getAllLikes();
    Like findById(Long id);
    List<Like> findAllByUsername(String name);
    Like deleteById(Long id);
}
