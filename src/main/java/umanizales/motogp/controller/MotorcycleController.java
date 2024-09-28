package umanizales.motogp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umanizales.motogp.model.Motorcycle;
import umanizales.motogp.service.MotorcycleService;
import umanizales.motogp.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/motorcycles")
public class MotorcycleController {
    @Autowired
    private MotorcycleService motorcycleService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Motorcycle> getListMotorcycles() {
        if (userService.getCurrentUser().getRole().getCode() == 1) {
            return motorcycleService.getMotorcycles();
        }
        return null;
    }


    @PostMapping(path = "/search")
    public Motorcycle findMotorcycle(@RequestBody int codeMotorcycle) {
        if (userService.getCurrentUser().getRole().getCode() == 1) {
            return motorcycleService.getMotorcycle(codeMotorcycle);
        }
        return null;
    }


    @PostMapping
    public String saveMotorcycle(@RequestBody Motorcycle motorcycle) {
        if (userService.getCurrentUser().getRole().getCode() == 1) {
            motorcycleService.saveMotorcycle(motorcycle);
            return "list";
        }
        return "User must be administrator";
    }

    @DeleteMapping
    public String deleteMotorcycle(@RequestBody Motorcycle motorcycle) {
        if (userService.getCurrentUser().getRole().getCode() == 1) {
            return motorcycleService.deleteMotorcycle(motorcycle);
        }
        return "User must be administrator";
    }
}
