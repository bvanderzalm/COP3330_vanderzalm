public class Cube extends Shape3D {

    private double edge;

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
        return edge * edge * edge;
    }
}
