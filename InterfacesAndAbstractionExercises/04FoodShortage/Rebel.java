package InterfacesAndAbstractionExercises04FoodShortage;

public class Rebel implements Person, Buyer{

    private String nme;
    private int age;
    private String group;
    private int food;

    public Rebel(String nme, int age, String group) {
        this.nme = nme;
        this.age = age;
        this.group = group;
        this.food = 0;
    }

    public String getGroup() {
        return this.group;
    }

    @Override
    public void buyFood() {
        this.food += 5;
    }

    @Override
    public int getFood() {
        return this.food;
    }

    @Override
    public String getName() {
        return this.nme;
    }

    @Override
    public int getAge() {
        return this.age;
    }
}