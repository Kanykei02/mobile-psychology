package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Comment;
import kg.ItAcademy.mobilepsychology.model.CommentsModel;
import kg.ItAcademy.mobilepsychology.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment createOrUpdate(@RequestBody CommentsModel commentsModel){
        return commentService.save(commentsModel);
    }

    @GetMapping
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @GetMapping("/{commentId}")
    public Comment getByID(@PathVariable Long commentId){
        return commentService.findById(commentId);
    }

    @GetMapping("/my/comments")
    public List<Comment> findMyComments(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return commentService.findAllByUsername(username);
    }

    @DeleteMapping("/{commentId}")
    public void deleteById(@PathVariable Long commentId){
         commentService.deleteById(commentId);
    }
}
