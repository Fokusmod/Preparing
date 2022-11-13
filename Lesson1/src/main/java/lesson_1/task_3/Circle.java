package lesson_1.task_3;

public class Circle extends Figure {

    private float radius;
    private int diameter;

    public Circle(float radius) {
        this.radius = radius;
    }

    public Circle(int diameter) {
        this.diameter = diameter;
    }

    public Circle(float radius, int diameter) {
        this.radius = radius;
        this.diameter = diameter;
    }

    @Override
    void draw() {
        System.out.println("draw circle");
    }

    @Override
    void area() {
        if (diameter == 0 && radius != 0) {
            float s = 3.14f * (radius * 2);
            System.out.println(s);
        } else if (diameter != 0 && radius == 0) {
            float s = ((diameter * 2f) / 4) * 3.15f;
            System.out.println(s);
        } else {
            throw new RuntimeException("diameter and radius is 0");
        }
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
