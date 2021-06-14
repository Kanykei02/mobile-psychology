package kg.ItAcademy.mobilepsychology.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "timetable")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "psychologist_id", nullable = false)
    private User psychologistId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Тоже связь
    private User user;

    @Column(name = "meeting_date")
    private LocalDateTime dateTime;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "description")
    private String description;
}
