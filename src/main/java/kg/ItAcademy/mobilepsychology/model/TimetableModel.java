package kg.ItAcademy.mobilepsychology.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableModel {
    private Long user;
    private LocalDate dateTime;
    private LocalDateTime createdDate;
    private String description;
}
