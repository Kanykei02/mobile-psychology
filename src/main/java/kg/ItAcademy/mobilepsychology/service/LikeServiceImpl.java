package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Like;
import kg.ItAcademy.mobilepsychology.entity.Post;
import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.model.LikeModel;
import kg.ItAcademy.mobilepsychology.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Like save(LikeModel likeModel) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        Post post = postService.findById(likeModel.getPostId());

        Like like = Like.builder()
                .createdDate(LocalDateTime.now())
                .postId(post)
                .user(user)
                .build();
        return likeRepository.save(like);
    }

    @Override
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Override
    public Like findById(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Like> findAllByUsername(String name) {
        return likeRepository.findAllByUser_Username(name);
    }

    @Override
    public void deleteById(Long id) {
        likeRepository.deleteById(id);
    }
}
