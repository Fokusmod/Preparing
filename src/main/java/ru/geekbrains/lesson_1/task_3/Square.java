package ru.geekbrains.lesson_1.task_3;

public class Square extends Figure{

    private float side;

    public Square(float side) {
        this.side = side;
    }

    @Override
    void draw() {
        System.out.println("draw square");
    }

    @Override
    void area() {
        float s = side * side;
        System.out.println(s);
    }

    public float getSide() {
        return side;
    }

    public void setSide(float side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                '}';
    }
}
