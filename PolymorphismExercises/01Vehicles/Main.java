package PolymorphismExercises01Vehicles;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] carData = scan.nextLine().split("\\s+");

        Car car = new Car(Double.parseDouble(carData[1]),Double.parseDouble(carData[2]));

        String[] truckData = scan.nextLine().split("\\s+");

        Truck truck = new Truck(Double.parseDouble(truckData[1]),Double.parseDouble(truckData[2]));

        int n = Integer.parseInt(scan.nextLine());

        while (n-- > 0){

            String[] commandData = scan.nextLine().split("\\s+");

            String command = commandData[0];
            String vehicleType = commandData[1];

            if (command.equals("Drive")){

                if (vehicleType.equals("Car")){
                    System.out.println(car.driving(Double.parseDouble(commandData[2])));
                } else if (vehicleType.equals("Truck")) {
                    System.out.println(truck.driving(Double.parseDouble(commandData[2])));
                }

            } else if (command.equals("Refuel")) {

                if (vehicleType.equals("Car")){
                    car.refueling(Double.parseDouble(commandData[2]));
                } else if (vehicleType.equals("Truck")) {
                    truck.refueling(Double.parseDouble(commandData[2]));
                }

            }


        }

        System.out.println(car);
        System.out.println(truck);




    }



}
