package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Follower;
import kg.ItAcademy.mobilepsychology.exception.FollowerNotFoundException;
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
    public ResponseEntity createOrUpdate(@RequestBody FollowerModel followerModel) throws FollowerNotFoundException{
        try {
            Follower followerModelName = followerService.save(followerModel);
            return new ResponseEntity<>(followerModelName, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/{listId}")
    public ResponseEntity getById(@PathVariable Long listId) throws FollowerNotFoundException {
        try{
            Follower followerListId = followerService.findById(listId);
            return new ResponseEntity<>(followerListId, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/my")
    public ResponseEntity findMyFollowers() throws FollowerNotFoundException{
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            List<Follower> followerListByUsername = followerService.findAllByUsername(username);
            return new ResponseEntity<>(followerListByUsername, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{listId}")
    public void deleteById(@PathVariable Long listId){
        followerService.deleteById(listId);
    }
}
