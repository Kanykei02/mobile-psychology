package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Post;
import kg.ItAcademy.mobilepsychology.model.PostModel;

import java.util.List;

public interface PostService {
    Post save(Post post);
    Post save(PostModel postModel);
    List<Post> getAllPosts();
    Post findById(Long id);
    Post deleteById(Long id);
    List<Post> findAllByUsername(String username);
}
