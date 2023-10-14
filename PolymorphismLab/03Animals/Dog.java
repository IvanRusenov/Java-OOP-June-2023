package PolymorphismLab03Animals;

public class Dog extends Animal{

    private String sound;

    public Dog(String name, String favouriteFood) {
        super(name, favouriteFood);

        this.sound = "DJAAF";
    }

    public String getSound() {
        return sound;
    }

    @Override
    public String explainSelf() {
        return String.format("I am %s and my favourite food is %s\n%s", this.getName(), this.getFavouriteFood(), this.getSound());
    }
}
