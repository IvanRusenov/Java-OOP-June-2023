package PolymorphismLab04WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        String input = scan.nextLine();

        while (!"End".equals(input)) {

            Animal animal = createAnimal(input);

            animals.add(animal);

            input = scan.nextLine();

            Food food = createFood(input);

            if (animal != null && food != null){
                animal.makeSound();
                animal.eat(food);
            }

            input = scan.nextLine();

        }

        printAnimals(animals);

    }

    private static void printAnimals(List<Animal> animals) {
        animals.forEach(System.out::println);
    }

    private static Animal createAnimal(String input) {

        String[] data = input.split("\\s+");
        String type = data[0];
        String name = data[1];
        Double weight = Double.parseDouble(data[2]);
        String animalLivingRegion = data[3];

        switch (type) {
            case "Mouse":
                return new Mouse(name, type, weight, animalLivingRegion);
            case "Zebra":
               return new Zebra(name, type, weight, animalLivingRegion);
            case "Tiger":
                return new Tiger(name, type, weight, animalLivingRegion);
            case "Cat":
                String breed = data[4];
                return new Cat(name, type, weight, animalLivingRegion, breed);
            default:
                return null;
        }

    }

    private static Food createFood(String input) {

        String[] data = input.split("\\s+");

        String foodType = data[0];
        int quantity = Integer.parseInt(data[1]);

        switch (foodType) {

            case "Vegetable":
                return new Vegetable(quantity);
            case "Meat":
                return new Meet(quantity);
            default:
                return null;

        }

    }

}
