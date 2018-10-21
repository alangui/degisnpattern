package com.qing.niu.design.factory;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/21 10:58
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class BuldLight implements Light{

    @java.lang.Override
    public void turnOn() {
        System.out.println("BuldLight turn on");
    }

    @java.lang.Override
    public void turnOff() {
        System.out.println("BuldLight turnOff");
    }
}
