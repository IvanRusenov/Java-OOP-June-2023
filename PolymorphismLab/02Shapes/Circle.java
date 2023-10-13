package PolymorphismLab02Shapes;

public class Circle extends Shape{

    private final Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return radius;
    }

    @Override
    public Double calculatePerimeter() {
        return Math.PI * radius * radius;
    }

    @Override
    public Double calculateArea() {
        return 2 * radius * Math.PI;
    }
}
