package umanizales.motogp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umanizales.motogp.model.DTO.UserDTO;
import umanizales.motogp.model.DTO.UserRoleDTO;
import umanizales.motogp.model.Role;
import umanizales.motogp.model.User;
import umanizales.motogp.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/roles")
    public List<Role> getRoles() {
        return userService.getListRoles();
    }

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return userService.getListUsers();
    }

    @PostMapping(path = "/save_role")
    public String saveRole(@RequestBody Role role) {
        userService.saveRole(role);
        return "Role saved successfully";
    }

    @DeleteMapping(path = "/delete_role")
    private String deleteRole(@RequestBody Role role) {
        userService.deleteRole(role);
        return "Role deleted successfully!";
    }


    @PostMapping(path = "/save_user")
    public String saveUser(@RequestBody UserRoleDTO userRoleDTO) {
        User newUser = new User(userRoleDTO.getEmail(), userRoleDTO.getPassword(),
                userService.findRole(userRoleDTO.getRole_code()));
        userService.saveUser(newUser);
        return "User saved successfully";
    }

    @DeleteMapping(path = "/delete_user")
    private String deleteRole(@RequestBody User user) {
        userService.deleteUser(user);
        return "User deleted successfully!";
    }

    @PostMapping(path = "/choose_user")
    public User chooseUser(@RequestBody UserDTO userDTO) {
        return userService.chooseUser(userDTO);
    }
}
