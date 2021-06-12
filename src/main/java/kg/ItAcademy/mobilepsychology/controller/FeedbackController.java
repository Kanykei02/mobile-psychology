package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Feedback;
import kg.ItAcademy.mobilepsychology.model.FeedbackModel;
import kg.ItAcademy.mobilepsychology.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<Feedback> getAllFeedbacks(){
        return feedbackService.getAllFeedbacks();
    }

    @PostMapping
    public Feedback createOrUpdate(@RequestBody FeedbackModel feedbackModel){
        return feedbackService.save(feedbackModel);
    }

    @GetMapping("/{feedbackId}")
    public Feedback getById(@PathVariable Long feedbackId){
        return feedbackService.findById(feedbackId);
    }

    @GetMapping("/my")
    public List<Feedback> findMyFeedbacks(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return feedbackService.findAllByUsername(username);
    }

    @DeleteMapping("/{feedbackId}")
    public Feedback deleteById(@PathVariable Long feedbackId){
        return feedbackService.deleteById(feedbackId);
    }
}
