package com.zzh.rest.disruptor.basic;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-7-11 12:41
 **/
public class LongEventTranslator {
    private RingBuffer buffer;
    private EventTranslatorOneArg<LongEvent, ByteBuffer> translator = (event, sequence, bb) -> event.setValue(bb.getLong(0));

    public LongEventTranslator(RingBuffer buffer) {
        this.buffer = buffer;
    }

    public void onData(ByteBuffer bb) {
        buffer.publishEvent(translator, bb);
    }
}
