package com.zzh.rest.disruptor.carpark;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-7-11 14:13
 **/
public class CarParkEvent {
    private String platNumber;

    public String getPlatNumber() {
        return platNumber;
    }

    public void setPlatNumber(String platNumber) {
        this.platNumber = platNumber;
    }

    @Override
    public String toString() {
        return "CarParkEvent{" +
                "platNumber='" + platNumber + '\'' +
                '}';
    }
}
