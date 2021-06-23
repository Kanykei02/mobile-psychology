package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Post;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.PostModel;
import kg.ItAcademy.mobilepsychology.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public Post createOrUpdate(@RequestBody PostModel postModel){
        return postService.save(postModel);
    }

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public ResponseEntity getById(@PathVariable Long postId) {
        try{
            Post postsId = postService.findById(postId);
            return new ResponseEntity<>(postsId, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/my")
    public List<Post> findMyPosts(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return postService.findAllByUsername(username);
    }

    @DeleteMapping("/{postId}")
    public void deleteById(@PathVariable Long postId){
        postService.deleteById(postId);
    }
}
