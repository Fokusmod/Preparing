package ru.geekbrans.lesson3.counter;

//2. Реализовать потокобезопасный счетчик с помощью интерфейса Lock.


public class Main {
    private static final Counter COUNTER = new Counter();

    public static void main(String[] args) {


        System.out.println(getCount());

        startTest();

        System.out.println(getCount());


    }

    private static int getCount(){
        return COUNTER.getCount();
    }


    private static void startTest() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                COUNTER.increment();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                COUNTER.increment();
            }
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                COUNTER.increment();
            }
        });
        t3.start();

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                COUNTER.decrement();
            }
        });
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
