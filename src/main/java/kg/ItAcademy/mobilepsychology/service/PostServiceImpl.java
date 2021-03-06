package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Picture;
import kg.ItAcademy.mobilepsychology.entity.Post;
import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
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

    @Autowired
    private PictureService pictureService;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post save(PostModel postModel) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userService.findByUsername(username);
        Picture picture = null;
        if(postModel.getPicture() != null){
            picture = pictureService.findById(postModel.getPicture());
        }
        Post post = Post.builder()
                .psychologistId(user)
                .createdDate(LocalDateTime.now())
                .title(postModel.getTitle())
                .info(postModel.getInfo())
                .picture(picture)
                .build();
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long id) throws ObjectNotFoundException {
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post was not found: ", id));
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> findAllByUsername(String username) {
        return postRepository.findAllByPsychologistId_Username(username);
    }
}
