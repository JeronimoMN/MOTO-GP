package umanizales.motogp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationTime {
    private int code;
    private Motorcycle motorcycle;
    private float time;

}
