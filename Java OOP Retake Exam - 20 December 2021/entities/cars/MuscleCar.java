package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class MuscleCar extends BaseCar {
    private static final double CC = 5000;
    private static final double MIN_HP = 400;
    private static final double MAX_HP = 600;


    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, CC);
        setHorsePower(horsePower);
    }
    @Override
    public void setHorsePower(int horsePower) {
        if (horsePower < MIN_HP || horsePower > MAX_HP) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }
}
