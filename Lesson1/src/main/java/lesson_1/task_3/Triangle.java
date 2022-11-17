package lesson_1.task_3;

public class Triangle extends Figure {

    private float side;

    private float height;

    public Triangle(float side, float height) {
        this.side = side;
        this.height = height;
    }

    @Override
    void draw() {
        System.out.println("draw triangle");
    }

    @Override
    void area() {
        float s = (side * height) / 2f;
        System.out.println(s);
    }

    public float getSide() {
        return side;
    }

    public void setSide(float side) {
        this.side = side;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "side=" + side +
                ", height=" + height +
                '}';
    }
}
