import java.lang.Math;

public class Cube extends Shape3D {

    private final double edge;

    public Cube(double edge) {
        super();
        this.edge = edge;
    }

    public String getName() {
        return "cube";
    }

    public double getArea() {
        return 6 * edge * edge;
    }

    public double getVolume() {
        return Math.pow(edge, 3);
    }
}
