package PolymorphismExercises02VehiclesExtension;

public class Car extends Vehicle {

    private static final double AIR_CONDITIONER_OVER_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }
    @Override
    public String drive(double distance) {

        if (this.isAirConditionerOn()){
            setFuelConsumption(getFuelConsumption()+AIR_CONDITIONER_OVER_CONSUMPTION);
        }

        String result = super.drive(distance);

        if (this.isAirConditionerOn()){
            setAirConditionerOn(false);
            setFuelConsumption(this.getFuelConsumption() - AIR_CONDITIONER_OVER_CONSUMPTION);
        }


        return result;
    }
}
