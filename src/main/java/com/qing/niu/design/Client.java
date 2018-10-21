package com.qing.niu.design;

import com.qing.niu.design.factory.*;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/15 22:42
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        Creator buldCreator = new BuldCreator();
        Light buldLight = buldCreator.createLight();
        buldLight.turnOn();
        buldLight.turnOff();

        Creator tubeCreator = new TubeCreator();
        Light tubeLight = tubeCreator.createLight();
        tubeLight.turnOn();
        tubeLight.turnOff();
        System.out.print("----------------------------");

    }
}
