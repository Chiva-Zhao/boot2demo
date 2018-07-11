package com.zzh.rest.disruptor.carpark;

import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-7-11 14:30
 **/
public class CarParkMain {
    public static void main(String[] args) throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        int bufferSize = 2048; // 2的N次方
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executor = Executors.newCachedThreadPool();
        Disruptor<CarParkEvent> disruptor = new Disruptor<CarParkEvent>(CarParkEvent::new, bufferSize,
                executor, ProducerType.SINGLE, new YieldingWaitStrategy());
        EventHandlerGroup<CarParkEvent> group = disruptor.handleEventsWith(new SaveCarInfoHandler(), new KafkaHandler());
        group.then(new SmsHandler());
        disruptor.start();
        executor.submit(new CarParkPulbisher(disruptor, latch));
        latch.await();
        disruptor.shutdown();
        executor.shutdown();
        System.out.println("总耗时:" + (System.currentTimeMillis() - beginTime));
    }
}
