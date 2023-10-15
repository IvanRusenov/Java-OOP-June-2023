package PolymorphismLab04WildFarm;

public class Tiger extends Feline {

    public Tiger(String name, String type, Double weight, String livingRegion) {
        super(name, type, weight,  livingRegion);
    }
    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {

        String foodType = food.getClass().getSimpleName();

        //TODO food.getClass.getSimpleName !!!

        if (!foodType.equals("Meet")){
            System.out.printf("%ss are not eating that type of food!\n", this.getType());
        }else {
            this.setFoodEaten(food.getQuantity());
        }

    }

}
