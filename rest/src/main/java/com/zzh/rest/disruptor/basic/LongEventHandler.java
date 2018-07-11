package com.zzh.rest.disruptor.basic;


import com.lmax.disruptor.EventHandler;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-7-11 10:55
 **/
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue());
    }
}
