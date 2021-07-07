package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Feedback;
import kg.ItAcademy.mobilepsychology.model.FeedbackModel;
import kg.ItAcademy.mobilepsychology.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<Feedback> getAllFeedback(){
        return feedbackService.getAllFeedbacks();
    }

    @PostMapping
    public ResponseEntity createOrUpdate(@RequestBody FeedbackModel feedbackModel){
        try{
            Feedback feedback = feedbackService.save(feedbackModel);
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/{feedbackId}")
    public ResponseEntity getById(@PathVariable Long feedbackId){
        try {
            Feedback feedback = feedbackService.findById(feedbackId);
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/my")
    public List<Feedback> findMyFeedback(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return feedbackService.findAllByUsername(username);
    }

    @DeleteMapping("/{feedbackId}")
    public void deleteById(@PathVariable Long feedbackId){
         feedbackService.deleteById(feedbackId);
    }
}
