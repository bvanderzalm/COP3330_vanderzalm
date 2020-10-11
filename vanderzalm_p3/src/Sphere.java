import java.lang.Math;

public class Sphere extends Shape3D {

    private final double radius;

    public Sphere(double radius) {
        super();
        this.radius = radius;
    }

    public String getName() {
        return "sphere";
    }

    public double getArea() {
        return 4 * Math.PI * radius * radius;
    }

    public double getVolume() {
        return (double) 4 / 3 * Math.PI * Math.pow(radius, 3);
    }
}