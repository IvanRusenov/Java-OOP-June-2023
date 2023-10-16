package PolymorphismExercises01Vehicles;

public abstract class Vehicle {

    private double fuelQuantity;
    private double fueConsumption;

    public Vehicle(double fuelQuantity, double fueConsumption) {
        this.setFuelQuantity(fuelQuantity);
        this.fueConsumption = fueConsumption;
    }


    public double getFueConsumption() {
        return fueConsumption;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public abstract String driving(double km);

    public abstract void refueling(double l);

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.getFuelQuantity());
    }
}
