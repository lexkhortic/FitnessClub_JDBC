package by.itstep.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SportSection {

    private String nameSection;
    private int maxCountPeople;
    private Gym typeGym;

}
