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
        listMotorcycles.add(new Motorcycle(1, "black", true, "marcus"));
        listMotorcycles.add(new Motorcycle(2, "blue", true, "marcus"));
        listMotorcycles.add(new Motorcycle(3, "red", true, "marcus"));
        listMotorcycles.add(new Motorcycle(4, "purple", true, "marcus"));
    }

    public Motorcycle getMotorcycle(int codeMotorcycle) {
        System.out.println(listMotorcycles.toArray().length);
        return listMotorcycles.get(codeMotorcycle);
    }

    public List<Motorcycle> getMotorcycles() {
        return listMotorcycles;
    }

    public void saveMotorcycle(Motorcycle motorcycle) {
        listMotorcycles.add(motorcycle);
        System.out.println(listMotorcycles);
    }

    public String deleteMotorcycle(Motorcycle motorcycle) {
        listMotorcycles.remove(motorcycle);
        return "The motorcycle was successfully removed!";
    }
}