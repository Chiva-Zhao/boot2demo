package com.zzh.rest.resources;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseCyclicBarray {

    public static class Runner implements Runnable {
        private CyclicBarrier barrier;
        private String name;
        private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public Runner(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + " is ready at " + format.format(new Date()));
                Thread.sleep(2000);
                barrier.await();
                System.out.println(name + " go go go  at " + format.format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(new Thread(new Runner(barrier, "zhangsan")));
        service.submit(new Thread(new Runner(barrier, "lisi")));
        service.submit(new Thread(new Runner(barrier, "wangwu")));
        service.shutdown();
    }
}
