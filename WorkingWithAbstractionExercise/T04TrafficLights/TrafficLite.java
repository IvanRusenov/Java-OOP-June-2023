package WorkingWithAbstractionExercise.T04TrafficLights;

public class TrafficLite {

    private Color color;

    public TrafficLite(Color color) {
        this.color = color;
    }

    public void update() {

        if (this.color == Color.RED) {
            this.color = Color.GREEN;
        } else if (this.color == Color.GREEN) {
            this.color = Color.YELLOW;
        } else if (this.color == Color.YELLOW) {
            this.color = Color.RED;
        }
    }

    public void print() {
        System.out.print(this.color + " ");
    }


}
