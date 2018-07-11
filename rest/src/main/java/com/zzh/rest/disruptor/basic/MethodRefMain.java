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
 * @date 2018-7-11 13:44
 **/
public class MethodRefMain {
    public static void handleEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println(event.getValue());
    }

    public static void translate(LongEvent event, long sequence, ByteBuffer buffer) {
        event.setValue(buffer.getLong(0));
    }

    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 1024;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, bufferSize, executorService, ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.handleEventsWith(MethodRefMain::handleEvent);
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 100; l++) {
            bb.putLong(0, l);
//            translator.onData(bb);
            ringBuffer.publishEvent(MethodRefMain::translate, bb);
            Thread.sleep(1000);
        }
        disruptor.shutdown();
        executorService.shutdown();
    }
}
