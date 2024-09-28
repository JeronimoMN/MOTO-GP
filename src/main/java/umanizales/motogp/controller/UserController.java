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

    @PostMapping(path = "/save_role")
    public String saveRole(@RequestBody Role role) {
        return userService.saveRole(role);
    }

    @DeleteMapping(path = "/delete_role")
    private String deleteRole(@RequestBody Role role) {
        return userService.deleteRole(role);
    }

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return userService.getListUsers();
    }

    @PostMapping(path = "/save_user")
    public String saveUser(@RequestBody UserRoleDTO userRoleDTO) {
        User newUser = new User(userRoleDTO.getEmail(), userRoleDTO.getPassword(),
                userService.findRole(userRoleDTO.getRole_code()));
        return userService.saveUser(newUser);
    }

    @DeleteMapping(path = "/delete_user")
    private String deleteRole(@RequestBody User user) {
        return userService.deleteUser(user);
    }

    @PostMapping(path = "/choose_user")
    public User chooseUser(@RequestBody UserDTO userDTO) {
        return userService.chooseUser(userDTO);
    }
}
