package com.zzh.rest.disruptor.basic;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-7-11 10:47
 **/
public class LongEventProducer {

    private RingBuffer<LongEvent> buffer;

    public LongEventProducer(RingBuffer<LongEvent> buffer) {
        this.buffer = buffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence = buffer.next();
        try {
            LongEvent data = buffer.get(sequence);
            data.setValue(bb.getLong(0));
        } finally {
            buffer.publish(sequence);
        }
    }
}
