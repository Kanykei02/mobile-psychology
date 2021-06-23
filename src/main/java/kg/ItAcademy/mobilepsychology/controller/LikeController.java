package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Like;
import kg.ItAcademy.mobilepsychology.model.LikeModel;
import kg.ItAcademy.mobilepsychology.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping
    public Like createOrUpdate(@RequestBody LikeModel likeModel){
        return likeService.save(likeModel);
    }

    @GetMapping
    public List<Like> getAllLikes(){
        return likeService.getAllLikes();
    }

    @GetMapping("/{likeId}")
    public Like getById(@PathVariable Long likeId){
        return likeService.findById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteById(@PathVariable Long likeId){
        likeService.deleteById(likeId);
    }
}
