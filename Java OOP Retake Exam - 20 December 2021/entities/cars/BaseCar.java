package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public abstract class BaseCar implements Car {

    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        setModel(model);
        setHorsePower(horsePower);
        setCubicCentimeters(cubicCentimeters);
    }

    public void setModel(String model) {

        if (model == null || model.trim().isEmpty() || model.length() < 4) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MODEL);
        }

        this.model = model;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return cubicCentimeters / horsePower * laps;
    }
}
