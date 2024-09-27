package umanizales.motogp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classification {
    private int code;
    private String description;
    private List<ClassificationTime> grill;
    private boolean state;
}
