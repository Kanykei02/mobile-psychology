package kg.ItAcademy.mobilepsychology.model;

import kg.ItAcademy.mobilepsychology.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackModel {
    private Long psychologistId;
    private String title;
    private String text;
    private LocalDateTime createdDate;
}
