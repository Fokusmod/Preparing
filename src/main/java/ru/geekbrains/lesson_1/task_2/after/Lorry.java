package ru.geekbrains.lesson_1.task_2.after;

/**
 * У грузовика ранее не был реализован метод open(), хотя абстрактный класс Car требует его реализации.
 *
 * В данном коде интерфейсы Moveable и Stopable можно объединить в один, но оставил так как есть.
 */

public class Lorry extends Car implements Moveable, Stopable {


    public Lorry(Engine engine, String color, String name) {
        super(engine, color, name);
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }

    @Override
    public void stop() {
        System.out.println("Car is stop");
    }

    @Override
    void open() {
        System.out.println("Car is open");
    }
}
