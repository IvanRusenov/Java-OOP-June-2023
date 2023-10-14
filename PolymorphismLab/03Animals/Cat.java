package PolymorphismLab03Animals;

public class Cat extends Animal {

    private String sound;
    public Cat(String name, String favouriteFood) {
        super(name, favouriteFood);
        this.sound = "MEEOW";
    }

    public String getSound() {
        return sound;
    }

    @Override
    public String explainSelf() {
        return String.format("I am %s and my favourite food is %s\n%s", this.getName(), this.getFavouriteFood(), this.getSound());
    }
}
