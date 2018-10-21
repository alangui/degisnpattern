package com.qing.niu.design;

import com.qing.niu.design.abstract_factory.*;
import com.qing.niu.design.factory.*;
import com.qing.niu.design.factory.Creator;
import com.qing.niu.design.observer.Reader;
import com.qing.niu.design.observer.Writer;
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

        log.info("观察者模式");
        Reader r1 = new Reader("张三");
        Reader r2 = new Reader("李四");
        Reader r3 = new Reader("王五");
        Writer writer = new Writer("赵六");
        r1.subscribe(writer.getName());
        r2.subscribe(writer.getName());
        r3.subscribe(writer.getName());
        writer.addNovel("我叫赵六");
        r1.unSubcribe(writer.getName());
        r2.unSubcribe(writer.getName());
        writer.addNovel("我不叫赵六");
    }
}
