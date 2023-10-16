package PolymorphismExercises01Vehicles;

import java.text.DecimalFormat;

public class Truck extends Vehicle{

    private static final double SEASON_OVER_CONSUMPTION = 1.6;
    public Truck(double fuelQuantity, double fueConsumption) {
        super(fuelQuantity, fueConsumption + SEASON_OVER_CONSUMPTION);
    }

    @Override
    public String driving(double km) {

        double fuelNeeded = km * getFueConsumption();

        if (fuelNeeded > getFuelQuantity()){
            return "Truck needs refueling";
        }

        setFuelQuantity(this.getFuelQuantity() - fuelNeeded);
        DecimalFormat df = new DecimalFormat("##.##");
        return String.format("Truck travelled %s km", df.format(km));

    }

    @Override
    public void refueling(double l) {

        setFuelQuantity(this.getFuelQuantity() + l * 0.95);

    }
}
