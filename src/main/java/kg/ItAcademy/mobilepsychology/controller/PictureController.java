package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Picture;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping
    public List<Picture> getAll() {
        return pictureService.getAllPictures();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws ObjectNotFoundException {
        try{
            Picture picturesId = pictureService.getById(id);
            return new ResponseEntity<>(picturesId, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping
    public Picture create(@RequestParam(name = "file") MultipartFile multipartFile) {
        return pictureService.createPicture(multipartFile);
    }
}
