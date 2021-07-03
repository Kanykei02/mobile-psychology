package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.PostPicture;
import kg.ItAcademy.mobilepsychology.model.PostPictureModel;
import kg.ItAcademy.mobilepsychology.service.PostPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/pictures")
public class PostPictureController {

    @Autowired
    private PostPictureService postPictureService;

    @PostMapping
    public PostPicture create(@RequestBody PostPictureModel postPictureModel){
        return postPictureService.create(postPictureModel);
    }

    @GetMapping
    public List<PostPicture> getAllPostPictures(){
        return postPictureService.getAllPostPicture();
    }

    @GetMapping("/{ppId}")
    public PostPicture getPostPictureById(@PathVariable Long ppId){
        return postPictureService.findPostPictureById(ppId);
    }

    @DeleteMapping("/{ppId}")
    public void deleteById(@PathVariable Long ppId){
        postPictureService.deleteById(ppId);
    }
}
