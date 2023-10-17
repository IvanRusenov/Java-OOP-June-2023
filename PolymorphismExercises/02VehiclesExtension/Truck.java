package PolymorphismExercises02VehiclesExtension;

public class Truck extends Vehicle {

    private static final double AIR_CONDITIONER_OVER_CONSUMPTION = 1.6;
    private static final double FUEL_AFTER_DRIVER_PRIVATE_JOB = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
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
       @Override
    public void refuel(double liters) {

        if (liters <= 0) {
            System.out.println("Fuel must be a positive number");
        }else {
            super.refuel(this.getFuelQuantity() + liters * FUEL_AFTER_DRIVER_PRIVATE_JOB);

        }

    }
}
