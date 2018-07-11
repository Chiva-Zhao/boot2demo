package com.zzh.rest.disruptor.carpark;

import com.lmax.disruptor.EventHandler;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-7-11 14:29
 **/
public class SmsHandler implements EventHandler<CarParkEvent> {
    @Override
    public void onEvent(CarParkEvent event, long sequence, boolean endOfBatch) throws Exception {
        long id = Thread.currentThread().getId();
        System.out.println(String.format("Thread %s send sms to %s", id, event.getPlatNumber()));
    }
}
