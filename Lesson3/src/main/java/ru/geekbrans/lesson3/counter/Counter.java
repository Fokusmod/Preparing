package ru.geekbrans.lesson3.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private int count;
    private final Lock lock = new ReentrantLock();


    public void increment() {
        lock.lock();
        this.count++;
        lock.unlock();
    }

    public void decrement() {
        lock.lock();
        this.count--;
        lock.unlock();
    }

    public int getCount() {
        return count;
    }
}
