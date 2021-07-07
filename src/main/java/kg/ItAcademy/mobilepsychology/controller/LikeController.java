package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Like;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.LikeModel;
import kg.ItAcademy.mobilepsychology.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping
    public ResponseEntity createOrUpdate(@RequestBody LikeModel likeModel) throws ObjectNotFoundException {
        try {
            Like likeModelName = likeService.save(likeModel);
            return new ResponseEntity<>(likeModelName, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping
    public List<Like> getAllLikes(){
        return likeService.getAllLikes();
    }

    @GetMapping("/{likeId}")
    public ResponseEntity getById(@PathVariable Long likeId) throws ObjectNotFoundException{
        try {
            Like like = likeService.findById(likeId);
            return new ResponseEntity<>(like, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{likeId}")
    public void deleteById(@PathVariable Long likeId){
        likeService.deleteById(likeId);
    }

    @GetMapping("/username/{username}")
    public List<Like> getLikesByUsername(@PathVariable String username){
        return likeService.findAllByUsername(username);
    }
}
