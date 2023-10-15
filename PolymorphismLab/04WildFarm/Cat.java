package PolymorphismLab04WildFarm;

import java.text.DecimalFormat;

public class Cat extends Feline {

    private final String breed;

    public Cat(String name, String type, Double weight, String livingRegion, String breed) {
        super(name, type, weight, livingRegion);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        this.setFoodEaten(food.getQuantity());
    }

    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("#0.##");

        return String.format("%s[%s, %s, %s, %s, %d]",
                this.getType(),
                this.getName(),
                this.getBreed(),
                df.format(this.getWeight()),
                this.getLivingRegion(),
                this.getFoodEaten());

    }
}
