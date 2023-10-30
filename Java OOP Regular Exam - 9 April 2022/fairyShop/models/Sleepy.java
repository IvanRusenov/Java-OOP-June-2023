package fairyShop.models;

public class Sleepy extends BaseHelper{
    private static final int ENERGY = 50;
    public Sleepy(String name) {
        super(name, ENERGY);
    }

    @Override
    public void work() {
        //todo check logic
      setEnergy(Math.max(getEnergy()-15, 0));
    }
}
