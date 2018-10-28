package com.qing.niu.design;

import com.qing.niu.design.abstract_factory.*;
import com.qing.niu.design.bridge.*;
import com.qing.niu.design.bridge.Doppelganger;
import com.qing.niu.design.bridge.Soul;
import com.qing.niu.design.builder.*;
import com.qing.niu.design.command.ProductManager;
import com.qing.niu.design.command.Programmer;
import com.qing.niu.design.command.Salesman;
import com.qing.niu.design.decorator.Component;
import com.qing.niu.design.decorator.ConcreteComponent;
import com.qing.niu.design.decorator.ConcreteDecoratorA;
import com.qing.niu.design.decorator.ConcreteDecoratorB;
import com.qing.niu.design.factory.*;
import com.qing.niu.design.factory.Creator;
import com.qing.niu.design.observer.Reader;
import com.qing.niu.design.observer.Writer;
import com.qing.niu.design.strategy.Customer;
import com.qing.niu.design.templet.MyPageBuilder;
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
        log.info("----------------------------");

        log.info("策略模式");
        Customer customer = new Customer();
        customer.buy(900D);
        log.info("客户需要付钱:{}",customer.calLastAmount());
        customer.buy(200D);
        log.info("客户需要付钱:{}",customer.calLastAmount());
        customer.buy(900D);
        log.info("客户需要付钱:{}",customer.calLastAmount());
        customer.buy(1000D);
        log.info("客户需要付钱:{}",customer.calLastAmount());
        log.info("-----------------------------");

        log.info("模版方法模式");
        MyPageBuilder myPageBuilder = new MyPageBuilder();
        log.info("我的页面:{}",myPageBuilder.buildHtml());
        log.info("-----------------------------");

        log.info("装饰器模式");
        Component component = new ConcreteComponent();
        component.method();
        ConcreteDecoratorA concreteDecoratorA = new ConcreteDecoratorA(component);
        concreteDecoratorA.methodA();
        concreteDecoratorA.method();
        ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB(component);
        concreteDecoratorB.methodB();
        concreteDecoratorB.method();
        concreteDecoratorB = new ConcreteDecoratorB(concreteDecoratorA);
        concreteDecoratorB.method();
        concreteDecoratorB.methodB();
        log.info("------------------------------");

        log.info("命令模式");
        Programmer programmer1 = new Programmer("程序A");
        ProductManager productManager = new ProductManager(programmer1);

        Salesman salesman1 = new Salesman("1",productManager);
        Salesman salesman2 = new Salesman("2",productManager);
        Salesman salesman3 = new Salesman("3",productManager);
        Salesman salesman4 = new Salesman("4",productManager);

        salesman1.putDemand();
        salesman1.putBug();
        salesman1.putProblem();
        salesman2.putProblem();
        salesman3.putBug();
        salesman4.putDemand();

        productManager.assign();
        productManager.printTaskList();
        productManager.assign();
        productManager.printTaskList();
        log.info("-------------------------------");

        log.info("桥接模式");
        Soul soul = new Doppelganger();
        soul.setAppearance(new TRH());
        soul.setSkills(new Invisible());
        soul.doAllLikePeople();
        soul.show();
        soul.release();
        soul.setAppearance(new Loser());
        soul.show();
        soul.setSkills(new ReadMind());
        soul.release();
        log.info("-------------------------------");

        log.info("建造者模式");
        com.qing.niu.design.builder.Soul soul1 = new com.qing.niu.design.builder.Soul();
        DoppelgangerBuilder doppelgangerBuilder = new ThinBuilder();
        com.qing.niu.design.builder.Doppelganger doppelganger = soul1.createDoppelganger(doppelgangerBuilder,"A");
        log.info("{}",doppelganger);
        DoppelgangerBuilder doppelgangerBuilder1 = new FatBuilder();
        doppelganger = soul1.createDoppelganger(doppelgangerBuilder1,"B");
        log.info("{}",doppelganger);
        log.info("-------------------------------");
    }
}
