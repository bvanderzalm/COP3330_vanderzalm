import java.lang.Math;

public class Circle extends Shape2D {

    private final double radius;

    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    public String getName() {
        return "circle";
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
