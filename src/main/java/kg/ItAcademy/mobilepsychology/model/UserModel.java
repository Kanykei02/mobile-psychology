package kg.ItAcademy.mobilepsychology.model;

import kg.ItAcademy.mobilepsychology.entity.Picture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String email;
    private String username;
    private String password;
    private String biography;
    private Long status;
    private Long profilePicture;
}
