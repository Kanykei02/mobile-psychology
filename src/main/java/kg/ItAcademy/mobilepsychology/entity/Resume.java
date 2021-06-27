package kg.ItAcademy.mobilepsychology.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resume")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "picture")
    private Picture picture;
    //добавить статус три статуса(рассмотрен, отклонен, на рассмотрение)
    //получать по статусам()
    //обновление заявки по статусу/айди
    //файнд бай резюме) в репо
}
