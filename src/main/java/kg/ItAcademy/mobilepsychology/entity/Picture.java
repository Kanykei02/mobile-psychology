package kg.ItAcademy.mobilepsychology.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "picture")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "create_date", nullable = false)
//    private LocalDate
}
