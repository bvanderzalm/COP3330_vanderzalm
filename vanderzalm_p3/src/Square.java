public class Square extends Shape2D{

    private final double side;

    public Square(double side) {
        super();
        this.side = side;
    }

    public String getName() {
        return "square";
    }

    public double getArea() {
        return side * side;
    }

}
