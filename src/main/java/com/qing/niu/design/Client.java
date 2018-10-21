package com.qing.niu.design;

import com.qing.niu.design.abstract_factory.*;
import com.qing.niu.design.factory.*;
import com.qing.niu.design.factory.Creator;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/15 22:42
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public class Client {

    public static void main(String[] args) {
        log.info("工厂模式");
        Creator buldCreator = new BuldCreator();
        Light buldLight = buldCreator.create();
        buldLight.turnOn();
        buldLight.turnOff();

        Creator tubeCreator = new TubeCreator();
        Light tubeLight = tubeCreator.create();
        tubeLight.turnOn();
        tubeLight.turnOff();
        log.info("----------------------------");

        log.info("抽象工厂模式");
        com.qing.niu.design.abstract_factory.Creator creator1 = new Creator1();
        ProductA productA1 = creator1.createProductA();
        ProductB productB1 = creator1.createProductB();
        productA1.methodA();
        productB1.methodB();

        com.qing.niu.design.abstract_factory.Creator creator2 = new Creator2();
        ProductA productA2 = creator2.createProductA();
        ProductB productB2 = creator2.createProductB();
        productA2.methodA();;
        productB2.methodB();
        log.info("----------------------------");


    }
}
