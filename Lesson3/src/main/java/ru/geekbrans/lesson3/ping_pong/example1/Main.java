package ru.geekbrans.lesson3.ping_pong.example1;

//1. Реализовать программу, в которой два потока поочередно пишут ping и pong.

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
