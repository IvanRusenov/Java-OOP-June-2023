package PolymorphismLab04WildFarm;

public class Mouse extends Mammal {

    public Mouse(String name, String type, Double weight, String livingRegion) {
        super(name, type, weight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    public void eat(Food food) {

        String foodType = food.getClass().getSimpleName();

        if (!foodType.equals("Vegetable")){
            System.out.println("Mice are not eating that type of food!");
        }else {
            this.setFoodEaten(food.getQuantity());
        }

    }

}
