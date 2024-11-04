package umanizales.motogp.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import umanizales.motogp.model.Motorcycle;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class MotorcycleService {
    private ArrayList<Motorcycle> listMotorcycles;

    public MotorcycleService() {
        listMotorcycles = new ArrayList<>();
        listMotorcycles.add(new Motorcycle(1, "black", true, "Fernando"));
        listMotorcycles.add(new Motorcycle(2, "blue", true, "Checo"));
        listMotorcycles.add(new Motorcycle(3, "red", true, "Max"));
        listMotorcycles.add(new Motorcycle(4, "purple", true, "Hamilton"));
    }

    public Motorcycle getMotorcycle(int codeMotorcycle) {
        return listMotorcycles.get(codeMotorcycle);
    }

    public List<Motorcycle> getMotorcycles() {
        return listMotorcycles;
    }

    public void saveMotorcycle(Motorcycle motorcycle) {
        listMotorcycles.add(motorcycle);
    }

    public void deleteMotorcycle(Motorcycle motorcycle) {
        listMotorcycles.remove(motorcycle);
    }
}