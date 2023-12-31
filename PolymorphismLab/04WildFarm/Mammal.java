package PolymorphismLab04WildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal{

    private String livingRegion;

    protected Mammal(String name, String type, Double weight, String livingRegion) {
        super(name, type, weight);

        this.livingRegion = livingRegion;
    }

    public String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("#0.##");
        return String.format("%s[%s, %s, %s, %d]",
                 this.getType(),
                 this.getName(), df.format(getWeight()), getLivingRegion(), getFoodEaten());

    }
}
