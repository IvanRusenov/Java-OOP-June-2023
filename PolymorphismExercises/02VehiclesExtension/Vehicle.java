package PolymorphismExercises02VehiclesExtension;

import java.text.DecimalFormat;

public abstract class Vehicle {

    private static final double AIR_CONDITIONER_OVER_CONSUMPTION = 0.0;
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;
    private boolean isAirConditionerOn;


    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.tankCapacity = tankCapacity;
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.isAirConditionerOn = false;
    }

    public void setAirConditionerOn(boolean isOn) {
        isAirConditionerOn = isOn;
    }

    public boolean isAirConditionerOn() {
        return isAirConditionerOn;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {

        if (fuelQuantity <= 0) {

            throw new IllegalArgumentException("Fuel must be a positive number");

        }

        this.fuelQuantity = fuelQuantity;
    }

    public void setFuelConsumption(double fuelConsumption) {
       this.fuelConsumption = fuelConsumption;

    }


    public double getTankCapacity() {
        return tankCapacity;
    }

       public String drive(double distance) {

        double fuelNeeded = distance * this.getFuelConsumption();

        if (fuelNeeded > getFuelQuantity()) {
            this.setFuelConsumption(this.getFuelConsumption());
            throw new IllegalArgumentException(String.format("%s needs refueling", this.getClass().getSimpleName()));
        }

        setFuelQuantity(this.getFuelQuantity() - fuelNeeded);

        DecimalFormat df = new DecimalFormat("##.##");
        return String.format("%s travelled %s km", this.getClass().getSimpleName(), df.format(distance));

    }


    public void refuel(double liters) {

        if (liters < 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        } else if (this.getFuelQuantity() + liters > this.getTankCapacity()) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        } else {
            this.setFuelQuantity(this.getFuelQuantity() + liters);
        }


    }

    @Override
    public String toString() {

        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.getFuelQuantity());
    }
}
