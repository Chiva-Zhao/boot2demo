package com.zzh.rest.disruptor.basic;

import com.lmax.disruptor.EventFactory;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-7-11 10:40
 **/
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
