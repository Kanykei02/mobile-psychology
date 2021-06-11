package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Post;
import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.model.PostModel;
import kg.ItAcademy.mobilepsychology.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post save(PostModel postModel) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userService.findByUsername(username);

        Post post = Post.builder()
                .psychologistId(user)
                .createdDate(LocalDateTime.now())
                .title(postModel.getTitle())
                .info(postModel.getInfo())
                .build();
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post deleteById(Long id) {
        Post post = findById(id);
        if(post != null){
            postRepository.delete(post);
            return post;
        }
        return null;
    }

    @Override
    public List<Post> findAllByUsername(String username) {
        return postRepository.findAllByPsychologistId_Username(username);
    }
}
