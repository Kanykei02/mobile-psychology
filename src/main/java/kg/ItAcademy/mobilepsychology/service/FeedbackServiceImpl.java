package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Feedback;
import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.FeedbackModel;
import kg.ItAcademy.mobilepsychology.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserService userService;

    @Override
    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback save(FeedbackModel feedbackModel) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        User user1 = userService.findById(feedbackModel.getPsychologistId());

        if (user == user1){
            throw new ObjectNotFoundException("It is not possible!");
        } else {
            Feedback feedback = Feedback.builder()
                    .psychologistId(user1)
                    .userId(user)
                    .createdDate(LocalDateTime.now())
                    .title(feedbackModel.getTitle())
                    .text(feedbackModel.getText())
                    .build();
            return feedbackRepository.save(feedback);
        }
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback findById(Long id) throws ObjectNotFoundException{
        return feedbackRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Feedback was not found!"));
    }

    @Override
    public List<Feedback> findAllByUsername(String username) {
        return feedbackRepository.findAllByUserId_Username(username);
    }

    @Override
    public void deleteById(Long id) {
        feedbackRepository.deleteById(id);
    }
}
