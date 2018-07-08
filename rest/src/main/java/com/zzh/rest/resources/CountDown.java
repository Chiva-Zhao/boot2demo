package com.zzh.rest.resources;

import java.util.concurrent.CountDownLatch;

public class CountDown {
    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) {
        Runnable main = new Runnable() {
            @Override
            public void run() {
                System.out.println("main is ready and await!");
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("main is finished");
            }
        };
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("r1 is running");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("r2 is running");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        };
        Thread m = new Thread(main);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        m.start();
        t1.start();
        t2.start();
    }
}
