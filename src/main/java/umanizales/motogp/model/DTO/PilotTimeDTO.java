package umanizales.motogp.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PilotTimeDTO {
    private int code;
    private int codeMotorcycle;
    private float time;
}
