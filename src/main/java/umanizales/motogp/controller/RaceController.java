package umanizales.motogp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umanizales.motogp.model.ClassificationTime;
import umanizales.motogp.model.DTO.PilotTimeDTO;
import umanizales.motogp.model.DTO.PilotsDTO;
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

    @GetMapping(path = "/get_listDE_motorcycles")
    public List<Motorcycle> getListDEMotorcycles() {
        return raceService.getListDEMotorcycle();
    }

    @GetMapping(path = "/classifications")
    public List<ClassificationTime> getClassification() {
        return raceService.getRace().getClassification().getGrill();
    }

    @PostMapping(path = "/times")
    public String pilotTime(@RequestBody PilotTimeDTO pilotTimeDTO) {
        if (userService.getCurrentUser().getRole().getCode() == 1) {
            if (raceService.getClassification().isState()) {
                ClassificationTime classificationTime = new ClassificationTime(raceService.hashCode(), raceService.getMotorcycleService().getMotorcycle(pilotTimeDTO.getCodeMotorcycle()),
                        pilotTimeDTO.getTime());
                raceService.saveTimeMotorcycle(classificationTime);
                return "Time successfully added!";
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

    @PostMapping(path = "/advance")
    public String pilotAdvance (@RequestBody PilotsDTO pilotsDTO){
        if(Objects.equals(raceService.getRace().getState(), "initialized")){
            return raceService.pilotAdvance(pilotsDTO.getMotorcycle(), pilotsDTO.getPosition());
        }
        return "Race hasn't started";
    }

    @PostMapping(path = "/lose_position")
    public String pilotLoosePosition (@RequestBody PilotsDTO pilotsDTO){
        if(Objects.equals(raceService.getRace().getState(), "initialized")){
            return raceService.pilotLosePosition(pilotsDTO.getMotorcycle(), pilotsDTO.getPosition());
        }
        return "Race hasn't started";
    }

    @PostMapping(path = "/pilot_fell")
    public String pilotFell (@RequestBody Motorcycle motorcycle){
        if (Objects.equals(raceService.getRace().getState(), "initialized")){
            return raceService.pilotFell(motorcycle);
        }
        return "La carrera no ha iniciado";
    }

    @PostMapping(path = "/pilot_entry")
    public String pilotEntry(@RequestBody PilotsDTO pilotsDTO){
        if(Objects.equals(raceService.getRace().getState(), "initialized")){
            return raceService.pilotReEntry(pilotsDTO.getMotorcycle(), pilotsDTO.getPosition());
        }
        return "La carrera no ha iniciado";
    }

    @PostMapping(path="/takeout_pilot")
    public String pilotExit(@RequestBody Motorcycle motorcycle){
        if (Objects.equals(raceService.getRace().getState(), "initialized")) {
            return raceService.pilotExit(motorcycle);
        }
        return "Race hasn't started";
    }

    @GetMapping(path = "/end")
    public List<Motorcycle> endRace(){
        if(Objects.equals(raceService.getRace().getState(), "initialized")){
            return raceService.endRace();
        }
        return null;
    }
}