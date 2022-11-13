package ru.geekbrains.lesson_1.task_2.before;

public class Lorry extends Car implements Moveable, Stopable{


    @Override
    public void move() {

    }

    @Override
    public void stop() {
        System.out.println("Car is stop");
    }
}
