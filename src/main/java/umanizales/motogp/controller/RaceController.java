package umanizales.motogp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umanizales.motogp.service.RaceService;
import umanizales.motogp.service.UserService;

@RestController
@RequestMapping(path = "race")
public class RaceController {
    @Autowired
    private RaceService raceService;
    @Autowired
    private UserService userService;
}
