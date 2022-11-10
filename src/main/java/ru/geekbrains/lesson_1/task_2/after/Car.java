package ru.geekbrains.lesson_1.task_2.after;

/**
 * -Поле Engine ранее было публичным.
 * -Удалён метод start(); так как движение машины реализуют наследники при помощи интерфейса Moveable.
 * -Добавлен Конструктор на все поля, чтобы не получилось так что машина без полей (в частности двигателя) смогла поехать.
 */

public abstract class Car {

    private Engine engine;
    private String color;
    private String name;

    public Car(Engine engine, String color, String name) {
        this.engine = engine;
        this.color = color;
        this.name = name;
    }

    abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
}

