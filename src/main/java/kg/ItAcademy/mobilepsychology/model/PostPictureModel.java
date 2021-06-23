package kg.ItAcademy.mobilepsychology.model;

import kg.ItAcademy.mobilepsychology.entity.Picture;
import kg.ItAcademy.mobilepsychology.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostPictureModel {
    private Picture pictureId;
    private Post postId;
}
