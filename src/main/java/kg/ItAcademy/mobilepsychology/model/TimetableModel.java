package kg.ItAcademy.mobilepsychology.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableModel {
    private Long user;
    private LocalDateTime dateTime;
    private LocalDateTime createdDate;
    private String description;
}
