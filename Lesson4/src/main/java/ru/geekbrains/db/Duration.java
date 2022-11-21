package ru.geekbrains.db;

public enum Duration {

    SHORT(60),

    MIDDLE(90),

    LONG(120);

    private int value;

    Duration(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
