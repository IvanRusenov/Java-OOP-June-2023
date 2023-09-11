package WorkingWithAbstractionExercise.T06GreedyTimes2;

public class Item {

    private String name;
    private int quantity;

    private String type;

    public Item(String name, int quantity, String type) {
        this.name = name;
        this.quantity = quantity;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(long quantity) {
        this.quantity += quantity;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
}
