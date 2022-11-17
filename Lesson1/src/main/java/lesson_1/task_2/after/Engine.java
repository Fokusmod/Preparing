package lesson_1.task_2.after;

/**
 * Добавлен класс Engine которого не было.
 */

public class Engine {

    private final int cylinders;

    private final float volume;

    public int getCylinders() {
        return cylinders;
    }

    public float getVolume() {
        return volume;
    }

    public Engine(int cylinders, float volume) {
        this.cylinders = cylinders;
        this.volume = volume;
    }
}
