package PolymorphismLab04WildFarm;

public abstract class Animal {

    private final String name;
    private final String type;
    private final Double weight;
    private Integer foodEaten;

    protected Animal(String name, String type, Double weight) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.foodEaten = 0;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getFoodEaten() {
        return foodEaten;
    }

    public void setFoodEaten(Integer foodEaten) {
        this.foodEaten += foodEaten;
    }

    public abstract void makeSound();
    public abstract void eat(Food food);
}
