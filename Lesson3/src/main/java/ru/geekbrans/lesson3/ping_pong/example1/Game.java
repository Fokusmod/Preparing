package ru.geekbrans.lesson3.ping_pong.example1;

public class Game {

    private volatile String status = null;
    private final String ping = "PING";
    private final String pong = "PONG";
    private final String pung = "PUNG";
    private Thread threadPing;
    private Thread threadPong;
    private Thread threadPung;

    private int count;

    public Game() {
        preparingTreads();
    }

    private void preparingTreads() {
        threadPing = new Thread(() -> {
            while (count != 10) {
                if (status == null) {
                    status = ping;
                }
                if (status.equals(ping)) {
                    System.out.println(ping + " " + Thread.currentThread().getName());
                    count++;
                    status = pong;
                }
            }
        });

        threadPong = new Thread(() -> {
            while (count != 10) {
                if (status == null) {
                    status = pong;
                }
                if (status.equals(pong)) {
                    System.out.println(pong + " " + Thread.currentThread().getName());
                    count++;
                    status = pung;
                }
            }
        });

        threadPung = new Thread(() -> {
            while (count != 10) {
                if (status == null) {
                    status = pung;
                }
                if (status.equals(pung)) {
                    System.out.println(pung + " " + Thread.currentThread().getName());
                    count++;
                    status = ping;
                }
            }
        });
    }

    public void play() {
        threadPing.start();
        threadPong.start();
        threadPung.start();
    }

}
