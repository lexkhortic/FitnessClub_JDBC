package by.itstep.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class FitnessTrainer {

    private String fio;
    private int age;
    private SportSection sportSection;
    private double workExperience;
    private String education;

}
