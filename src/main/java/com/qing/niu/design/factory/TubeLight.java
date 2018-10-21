package com.qing.niu.design.factory;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/21 11:04
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class TubeLight implements Light{

    @Override
    public void turnOn() {
        System.out.println("TubeLight turn on");
    }

    @Override
    public void turnOff() {
        System.out.println("TubeLight turn off");
    }
}
