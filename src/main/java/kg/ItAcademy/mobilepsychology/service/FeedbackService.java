package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Feedback;
import kg.ItAcademy.mobilepsychology.model.FeedbackModel;

import java.util.List;

public interface FeedbackService {
    Feedback save (Feedback feedback);
    Feedback save(FeedbackModel feedbackModel);
    List<Feedback> getAllFeedbacks();
    Feedback findById(Long id);
    List<Feedback> findAllByUsername(String username);
    Feedback deleteById(Long id);
}
