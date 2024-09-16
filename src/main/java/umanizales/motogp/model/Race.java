package umanizales.motogp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Race {
    private Classification classification;
    private String state;
    private ListDE listDE;
    private List<PilotsDTO> pilotsFelt;
}
