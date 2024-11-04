package umanizales.motogp.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRoleDTO {
    private String email;
    private String password;
    private int role_code;
}
