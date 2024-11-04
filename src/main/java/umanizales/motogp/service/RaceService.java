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

            listClassificationTime.add(new ClassificationTime(0, motorcycleService.getMotorcycle(0), 12 ));
            listClassificationTime.add(new ClassificationTime(1, motorcycleService.getMotorcycle(1), 13 ));
            listClassificationTime.add(new ClassificationTime(2, motorcycleService.getMotorcycle(2), 14 ));
            listClassificationTime.add(new ClassificationTime(3, motorcycleService.getMotorcycle(3), 15 ));

            classification = new Classification(1, "PRIMERA CARRERA", listClassificationTime, true);
            race = new Race(classification, "in process", new ListDE(null, 0), new ArrayList<>());
        }

        public List<Motorcycle> getListDEMotorcycle(){
            return race.getListDE().getListMotorcycles();
        }

        public void saveTimeMotorcycle(ClassificationTime classificationTime){
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
                        }
                    }
                    list.add(classificationTime1);
                }
                classification.setGrill(list);
            }
        }

        public boolean setRaceState(String state){
            if(Objects.equals(state, "initialized") || Objects.equals(state, "closed") || Objects.equals(state, "in process")){
                return Objects.equals(race.getState(), "initialized");
            }
            return false;
        }

        public void startRace(){
            for(ClassificationTime classificationTime: classification.getGrill()){
                race.getListDE().addEnd(classificationTime.getMotorcycle());
            }
            race.setState("initialized");
        }

        public String pilotAdvance(Motorcycle motorcycle, int num){
            if(race.getListDE().advance(motorcycle.getPilot(), num)){
                return "Pilot advanced";
            }else {
                return "Invalid number";
            }
        }

        public String pilotLosePosition(Motorcycle motorcycle, int num){
            if(race.getListDE().losePosition(motorcycle.getPilot(), num)){
                return "Pilot lose positions";
            }else {
                return "Invalid number";
            }
        }

        public String pilotFell(Motorcycle motorcycle){
            race.getPilotsFelt().add(new PilotsDTO(motorcycle, race.getListDE().deletePilot(motorcycle.getPilot())));
            System.out.println(race.getPilotsFelt());
            return "Pilot out for the moment";
        }

        public String pilotReEntry(Motorcycle motorcycle, int pos){
            for (PilotsDTO pilotsDTO: this.race.getPilotsFelt()){
                if(pilotsDTO.getMotorcycle().equals(motorcycle)){
                    if(pos > pilotsDTO.getPosition()){
                        race.getListDE().addPosition(pos, motorcycle);
                        race.getPilotsFelt().remove(pilotsDTO);
                        return "Pilot reinstated";
                    }
                    return "Invalid position for reinstatement";
                }
            }
            return "There is no match with the motorcycle";
        }

        public String pilotExit(Motorcycle motorcycle){
            motorcycle.setState(false);
            motorcycleService.saveMotorcycle(motorcycle);
            race.getListDE().deletePilot(motorcycle.getPilot());
            return "pilot out of race!";
        }

        public List<Motorcycle> endRace(){
            classification.setState(false);
            race.setState("closed");
            return race.getListDE().getListMotorcycles();
        }
    }