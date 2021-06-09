package kg.ItAcademy.mobilepsychology.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "post_picture")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "picture_id", nullable = false)
    private Picture pictureId;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post postId;
}
