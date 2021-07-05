package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Comment;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.CommentsModel;
import kg.ItAcademy.mobilepsychology.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity createOrUpdate(@RequestBody CommentsModel commentsModel){
        try{
            Comment comment = commentService.save(commentsModel);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @GetMapping("/{commentId}")
    public ResponseEntity getByID(@PathVariable Long commentId) throws ObjectNotFoundException {
        try {
            Comment comment = commentService.findById(commentId);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
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
