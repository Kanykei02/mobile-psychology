package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Comment;
import kg.ItAcademy.mobilepsychology.model.CommentsModel;

import java.util.List;

public interface CommentService {
    Comment save (Comment comment);
    Comment save(CommentsModel createCommentModel);
    List<Comment> getAllComments();
    Comment findById(Long id);
    List<Comment> findAllByUsername(String name);
    void deleteById(Long id);
}
