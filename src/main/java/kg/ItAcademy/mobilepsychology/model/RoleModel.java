package kg.ItAcademy.mobilepsychology.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel {
    private String roleName;
    private Long userId;
}
