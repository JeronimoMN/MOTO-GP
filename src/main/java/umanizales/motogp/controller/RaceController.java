package umanizales.motogp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umanizales.motogp.model.ClassificationTime;
import umanizales.motogp.model.DTO.PilotTimeDTO;
import umanizales.motogp.model.Motorcycle;
import umanizales.motogp.service.RaceService;
import umanizales.motogp.service.UserService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "race")
public class RaceController {
    @Autowired
    private RaceService raceService;
    @Autowired
    private UserService userService;

    @GetMapping(path = "/get_list_motorcycles")
    public List<Motorcycle> getMotor() {
        System.out.println(raceService.getList());
        return raceService.getList();
    }

    @GetMapping(path = "/get_classification")
    public List<ClassificationTime> getClassification() {
        return raceService.getRace().getClassification().getGrill();
    }

    @PostMapping(path = "/times")
    public String pilotTime(@RequestBody PilotTimeDTO pilotTimeDTO) {
        if (userService.getCurrentUser().getRole().getCode() == 1) {
            if (raceService.getClassification().isState()) {
                ClassificationTime classificationTime = new ClassificationTime(raceService.hashCode(), raceService.getMotorcycleService().getMotorcycle(pilotTimeDTO.getCodeMotorcycle()),
                        pilotTimeDTO.getTime());
                return raceService.saveTimeMotorcycle(classificationTime);
            }
            return "classification doesn't exists";
        }
        return "User must be administrator";
    }

    @PostMapping(path = "/state")
    public String setState(@RequestBody String state) {
        if (userService.getCurrentUser().getRole().getCode() == 1) {
            if (raceService.setRaceState(state)) {
                return "state actualized";
            } else {
                return "invalid state, it must be: initialized, closed or in process";
            }
        }
        return "User must be Admin";
    }

    @GetMapping(path = "/start")
    public String startRace() {
        if (Objects.equals(raceService.getRace().getState(), "in process")) {
            raceService.startRace();
            return "Race started";
        }
        return "Race must be in process to start it";
    }
}