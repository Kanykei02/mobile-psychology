package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Comment;
import kg.ItAcademy.mobilepsychology.entity.Post;
import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.CommentsModel;
import kg.ItAcademy.mobilepsychology.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment save(CommentsModel commentModel) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        Post post = postService.findById(commentModel.getPostId());
        if(post == null) throw new IllegalArgumentException("Post was not found!");
        else if(user == null) {
            throw new ObjectNotFoundException("Something went wrong");
        } else {
            Comment comment = Comment.builder()
                    .createdDate(LocalDateTime.now())
                    .text(commentModel.getText())
                    .post(post)
                    .commentatorUser(user)
                    .build();
            return commentRepository.save(comment);
        }
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) throws ObjectNotFoundException {
        return commentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Comment was not found: ", id));
    }

    @Override
    public List<Comment> findAllByUsername(String name) {
        return commentRepository.findAllByCommentatorUser_Username(name);
    }

    @Override
    public void deleteById(Long id) throws ObjectNotFoundException{

        commentRepository.deleteById(id);
    }
}
