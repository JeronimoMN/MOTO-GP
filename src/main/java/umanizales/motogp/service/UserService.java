package umanizales.motogp.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import umanizales.motogp.model.DTO.UserDTO;
import umanizales.motogp.model.Role;
import umanizales.motogp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Data
public class UserService {

    private List<User> listUsers;
    private List<Role> listRoles;
    private User currentUser;

    public UserService() {
        listUsers = new ArrayList<>();
        listRoles = new ArrayList<>();

        listUsers.add(new User("jmn@gmail.com", "perrogus", new Role(1, "ADMIN", new ArrayList<>())));
    }

    public String saveRole(Role role) {
        listRoles.add(role);
        return "Role Guardado";
    }

    public String saveUser(User user) {
        listUsers.add(user);
        return "Usuario Guardado";
    }

    public String deleteRole(Role role) {
        listRoles.remove(role);
        return "Rol Eliminado!";
    }

    public String deleteUser(User user) {
        listUsers.remove(user);
        return "Usuario Eliminado!";
    }

    public Role findRole(Long code) {
        for (Role role : getListRoles()) {
            if (Objects.equals(role.getCode(), code)) {
                return role;
            }
        }
        return null;
    }

    public User chooseUser(UserDTO user) {
        for (User u : getListUsers()) {
            if (Objects.equals(u.getEmail(), user.getEmail()) && Objects.equals(u.getPassword(), user.getPassword())) {
                this.currentUser = u;
                return u;
            }
        }
        return null;
    }
}
