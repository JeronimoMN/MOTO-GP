package umanizales.motogp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle {
    private Long codeMotorcycle;
    private boolean state;
    private String color;
    private String pilot;
    private List<ClassificationTime> classificationTimeList;

}
