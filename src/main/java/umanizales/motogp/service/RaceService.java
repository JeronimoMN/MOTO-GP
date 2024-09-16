package umanizales.motogp.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import umanizales.motogp.model.Classification;
import umanizales.motogp.model.Race;

import java.util.List;

@Service
@Data
public class RaceService {
    private MotorcycleService motorcycleService;
    private Classification classification;
    private Race race;
    private List<Classification> classifications;
}
