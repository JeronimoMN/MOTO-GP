package umanizales.motogp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationTime {
    private long code;
    private Motorcycle motorcycle;
    private float time;
    private Classification classification;

}
