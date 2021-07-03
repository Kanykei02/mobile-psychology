package kg.ItAcademy.mobilepsychology.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
