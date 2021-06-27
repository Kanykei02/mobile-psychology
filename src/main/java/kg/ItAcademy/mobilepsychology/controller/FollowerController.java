package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Follower;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.FollowerModel;
import kg.ItAcademy.mobilepsychology.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follower")
public class FollowerController {
    @Autowired
    private FollowerService followerService;

    @GetMapping
    public List<Follower> getAllFollowers(){
        return followerService.getAllFollowers();
    }

    @PostMapping
    public ResponseEntity createOrUpdate(@RequestBody FollowerModel followerModel) throws ObjectNotFoundException {
        try {
            Follower followerModelName = followerService.save(followerModel);
            return new ResponseEntity<>(followerModelName, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/{followerId}")
    public ResponseEntity getById(@PathVariable Long followerId) throws ObjectNotFoundException {
        try{
            Follower followerListId = followerService.findById(followerId);
            return new ResponseEntity<>(followerListId, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/my")
    public List<Follower> findMyFollowers() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return followerService.findAllByUsername(username);
    }

    @DeleteMapping("/{followerId}")
    public void deleteById(@PathVariable Long followerId){
        followerService.deleteById(followerId);
    }
}
