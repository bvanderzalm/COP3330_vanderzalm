import java.lang.Math;

public class Pyramid extends Shape3D {

    private final double length;
    private final double width;
    private final double height;

    public Pyramid(double length, double width, double height) {
        super();
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return "pyramid";
    }

    public double getArea() {
        return (length * width)
                + (length * Math.sqrt(Math.pow(width/2, 2) + (height * height)))
                + (width * Math.sqrt(Math.pow(length/2, 2) + (height * height)));
    }

    public double getVolume() {
        return -1;
    }
}
