package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Comment;
import kg.ItAcademy.mobilepsychology.model.CommentsModel;
import kg.ItAcademy.mobilepsychology.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Comment save(CommentsModel createCommentModel) {
        return null;
    }

    @Override
    public List<Comment> getAllComments() {
        return null;
    }

    @Override
    public Comment findById(Long id) {
        return null;
    }

    @Override
    public List<Comment> findAllByUsername(String name) {
        return null;
    }

    @Override
    public Comment deleteById(Long id) {
        return null;
    }
}
