package ru.geekbrans.lesson3.ping_pong.example2;

//1. Реализовать программу, в которой два потока поочередно пишут ping и pong.

public class Main {
    private static String status = "PING";
    private static final Object MONITOR = new Object();
    private static int count;
    private static final int MAX_COUNT = 10;


    public static void main(String[] args) {

        Thread pingThread = new Thread(Main::writePing);
        pingThread.start();

        Thread pongThread = new Thread(Main::writePong);
        pongThread.start();

    }

    private static void writePing() {
        final String PING = "PING";
        synchronized (MONITOR) {
            while (count < MAX_COUNT) {
                if (status.equals(PING)) {
                    count++;
                    System.out.println(PING + " " + count);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    status = "PONG";
                    MONITOR.notify();
                } else {
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private static void writePong() {
        final String PONG = "PONG";
        synchronized (MONITOR) {
            while (count < MAX_COUNT) {
                if (status.equals(PONG)) {
                    count++;
                    System.out.println(PONG + " " + count);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    status = "PING";
                    MONITOR.notify();
                } else {
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}
