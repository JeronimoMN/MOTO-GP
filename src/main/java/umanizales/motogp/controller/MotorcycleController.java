package umanizales.motogp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umanizales.motogp.service.MotorcycleService;
import umanizales.motogp.service.UserService;

@RestController
@RequestMapping(path = "/motorcycles")
public class MotorcycleController {
    @Autowired
    private MotorcycleService motorcycleService;
    @Autowired
    private UserService userService;
}
