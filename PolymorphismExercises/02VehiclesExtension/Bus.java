package PolymorphismExercises02VehiclesExtension;

import java.text.DecimalFormat;

public class Bus extends Vehicle {

    private static final double AIR_CONDITIONER_OVER_CONSUMPTION = 1.4;
    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public String drive(double distance) {

        if (this.isAirConditionerOn()){
            setFuelConsumption(getFuelConsumption()+AIR_CONDITIONER_OVER_CONSUMPTION);
        }

        double fuelNeeded = distance * this.getFuelConsumption();

        if (fuelNeeded > getFuelQuantity()) {
            this.setFuelConsumption(this.getFuelConsumption());
            throw new IllegalArgumentException(String.format("%s needs refueling", this.getClass().getSimpleName()));
        }

        setFuelQuantity(this.getFuelQuantity() - fuelNeeded);


        if (this.isAirConditionerOn()){
            setAirConditionerOn(false);
            setFuelConsumption(this.getFuelConsumption() - AIR_CONDITIONER_OVER_CONSUMPTION);
        }


        DecimalFormat df = new DecimalFormat("##.##");
        return String.format("%s travelled %s km", this.getClass().getSimpleName(), df.format(distance));
    }
}
