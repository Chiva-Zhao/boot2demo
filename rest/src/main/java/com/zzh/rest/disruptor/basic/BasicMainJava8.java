package com.zzh.rest.disruptor.basic;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-7-11 13:09
 **/
public class BasicMainJava8 {
    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 1024;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, bufferSize, executorService, ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.handleEventsWith(((event, sequence, endOfBatch) -> System.out.println(event.getValue())));
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 100; l++) {
            bb.putLong(0, l);
//            translator.onData(bb);
            ringBuffer.publishEvent((event, sequence) -> event.setValue(bb.getLong(0)));
            Thread.sleep(1000);
        }
        disruptor.shutdown();
        executorService.shutdown();
    }
}
