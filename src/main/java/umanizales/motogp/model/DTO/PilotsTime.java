package umanizales.motogp.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import umanizales.motogp.model.Motorcycle;

@Data
@AllArgsConstructor
public class PilotsTime {
    private Motorcycle motorcycle;
    private int position;
}
