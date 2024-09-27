package umanizales.motogp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle {
    private int codeMotorcycle;
    private String color;
    private boolean state;
    private String pilot;
}
