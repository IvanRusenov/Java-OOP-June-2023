package PolymorphismExercises02VehiclesExtension;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

//        vehicles.put(getVehicle(scan).getClass().getSimpleName(), getVehicle(scan));

        String[] carData = scan.nextLine().split("\\s+");
        Car car = new Car(Double.parseDouble(carData[1]), Double.parseDouble(carData[2]), Double.parseDouble(carData[3]));
        vehicles.put("Car", car);

        String[] truckData = scan.nextLine().split("\\s+");
        Truck truck = new Truck(Double.parseDouble(truckData[1]), Double.parseDouble(truckData[2]), Double.parseDouble(truckData[3]));
        vehicles.put("Truck", truck);

        String[] busData = scan.nextLine().split("\\s+");
        Bus bus = new Bus(Double.parseDouble(busData[1]), Double.parseDouble(busData[2]), Double.parseDouble(busData[3]));
        vehicles.put("Bus", bus);

        int n = Integer.parseInt(scan.nextLine());

        while (n-- > 0) {

            String[] commandData = scan.nextLine().split("\\s+");

            String command = commandData[0];
            String vehicleType = commandData[1];
            double argument = Double.parseDouble(commandData[2]);

            if ("Drive".equals(command)) {

                try {
                    vehicles.get(vehicleType).setAirConditionerOn(true);
                    System.out.println(vehicles.get(vehicleType).drive(argument));
                } catch (IllegalArgumentException exception) {
                    System.out.println(exception.getMessage());
                }


            } else if ("Refuel".equals(command)) {
                try {
                    vehicles.get(vehicleType).refuel(argument);
                } catch (IllegalArgumentException exception) {
                    System.out.println(exception.getMessage());
                }


            } else if ("DriveEmpty".equals(command)) {

                try {

                    System.out.println(vehicles.get(vehicleType).drive(argument));

                } catch (IllegalArgumentException exception) {

                    System.out.println(exception.getMessage());

                }


            }

        }

        vehicles.values().forEach(System.out::println);

    }

//    private static Vehicle getVehicle(Scanner scanner){
//
//        String[] vehicleData = scanner.nextLine().split("\\s+");
//
//        double fuelQuantity = Double.parseDouble(vehicleData[1]);
//        double fuelConsumption = Double.parseDouble(vehicleData[2]);
//        double tankCapacity = Double.parseDouble(vehicleData[3]);
//
//        switch (vehicleData[0]){
//            case "Car":
//             return new Car(fuelQuantity, fuelConsumption, tankCapacity);
//            case "Truck":
//                return new Truck(fuelQuantity, fuelConsumption, tankCapacity);
//            case "Bus":
//                return new Bus(fuelQuantity, fuelConsumption, tankCapacity);
//            default:
//                return null;
//        }

}



