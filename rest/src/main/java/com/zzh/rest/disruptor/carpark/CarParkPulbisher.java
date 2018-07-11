package com.zzh.rest.disruptor.carpark;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-7-11 14:30
 **/
public class CarParkPulbisher implements Runnable {
    private Disruptor<CarParkEvent> disruptor;
    private CountDownLatch countDownLatch;
    private final int NUM = 10;

    public CarParkPulbisher(Disruptor<CarParkEvent> disruptor, CountDownLatch countDownLatch) {
        this.disruptor = disruptor;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < NUM; i++) {
                disruptor.publishEvent(((event, sequence) -> {
                    event.setPlatNumber("PlatNumber" + Math.random() * 10000);
                    System.out.println("Thread " + Thread.currentThread().getId() + " generate one platNumber");
                }));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
            System.out.println(NUM + " car parking finished");
        }
    }
}
