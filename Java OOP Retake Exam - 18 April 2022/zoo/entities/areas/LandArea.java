package zoo.entities.areas;

import zoo.entities.animals.Animal;

import java.util.stream.Collectors;

public class LandArea extends BaseArea{

    private static final int CAPACITY = 25;
    public LandArea(String name) {
        super(name, CAPACITY);
    }


}
