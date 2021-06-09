package kg.ItAcademy.mobilepsychology.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "psychologist_id", nullable = false)
    private User psychologistId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "info", nullable = false)
    private String info;


}
