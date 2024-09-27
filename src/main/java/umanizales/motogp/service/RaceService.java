package umanizales.motogp.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import umanizales.motogp.model.*;
import umanizales.motogp.model.DTO.PilotsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Data
public class RaceService {
    private MotorcycleService motorcycleService;
    private Classification classification;
    private Race race;
    private List<ClassificationTime> listClassificationTime;

    public RaceService(MotorcycleService motorcycleService){
        this.motorcycleService = motorcycleService;
        listClassificationTime = new ArrayList<>();
        classification = new Classification(1, "PRIMERA CARRERA", listClassificationTime, true);
        race = new Race(classification, "in process", new ListDE(null, 0), new ArrayList<>());
    }

    public String saveTimeMotorcycle(ClassificationTime classificationTime){
        race.setState("in process");
        if(classification.getGrill() == null){
            classification.setGrill(new ArrayList<>());
            classification.getGrill().add(classificationTime);
                listClassificationTime.add(classificationTime);
        }else{
            List<ClassificationTime> list = new ArrayList<>();
            for(ClassificationTime classificationTime1: classification.getGrill()){
                if(classificationTime.getTime() < classificationTime1.getTime()){
                    if(!list.contains(classificationTime)){
                        list.add(classificationTime);
                        listClassificationTime.add(classificationTime);
                    }
                }
                list.add(classificationTime1);
            }
            classification.setGrill(list);
        }
        return "Time successfully added!";
    }

    public boolean setRaceState(String state){
        if(Objects.equals(state, "initialized") || Objects.equals(state, "closed") || Objects.equals(state, "in process")){
            if(Objects.equals(race.getState(), "initialized")){
                return true;
            }
        }
        return false;
    }

    public void startRace(){
        for(ClassificationTime classificationTime: classification.getGrill()){
            race.getListDE().addEnd(classificationTime.getMotorcycle());
        }
        race.setState("initialized");
    }

    public List<Motorcycle> getList(){
        System.out.println(race.getState());
        return race.getListDE().getListMotorcycles();
    }
}
