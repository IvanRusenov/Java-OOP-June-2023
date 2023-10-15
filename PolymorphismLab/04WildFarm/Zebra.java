package PolymorphismLab04WildFarm;

public class Zebra extends Mammal{

    public Zebra(String name, String type, Double weight, String livingRegion) {
        super(name, type, weight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }

    @Override
    public void eat(Food food) {

        String foodType = food.getClass().getSimpleName();

        if (!foodType.equals("Vegetable")){
            System.out.println("Zebras are not eating that type of food!");
        }else {
            this.setFoodEaten(food.getQuantity());
        }

    }

}
