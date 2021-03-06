package com.qing.niu.design;

import com.qing.niu.design.abstract_factory.Creator1;
import com.qing.niu.design.abstract_factory.Creator2;
import com.qing.niu.design.abstract_factory.ProductA;
import com.qing.niu.design.abstract_factory.ProductB;
import com.qing.niu.design.bridge.*;
import com.qing.niu.design.builder.DoppelgangerBuilder;
import com.qing.niu.design.builder.FatBuilder;
import com.qing.niu.design.builder.ThinBuilder;
import com.qing.niu.design.chain.McSubbranch;
import com.qing.niu.design.chain.Order;
import com.qing.niu.design.chain.Subbranch;
import com.qing.niu.design.command.ProductManager;
import com.qing.niu.design.command.Programmer;
import com.qing.niu.design.command.Salesman;
import com.qing.niu.design.component.Folder;
import com.qing.niu.design.component.IFile;
import com.qing.niu.design.decorator.Component;
import com.qing.niu.design.decorator.ConcreteComponent;
import com.qing.niu.design.decorator.ConcreteDecoratorA;
import com.qing.niu.design.decorator.ConcreteDecoratorB;
import com.qing.niu.design.factory.BuldCreator;
import com.qing.niu.design.factory.Creator;
import com.qing.niu.design.factory.Light;
import com.qing.niu.design.factory.TubeCreator;
import com.qing.niu.design.flyweight.HeroManager;
import com.qing.niu.design.flyweight.Role;
import com.qing.niu.design.memo.Person;
import com.qing.niu.design.observer.Reader;
import com.qing.niu.design.observer.Writer;
import com.qing.niu.design.state.Hero;
import com.qing.niu.design.strategy.Customer;
import com.qing.niu.design.templet.MyPageBuilder;
import com.qing.niu.design.visitor.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) throws Exception{
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

        log.info("备忘录模式");
        com.qing.niu.design.memo.Soul s1 = new com.qing.niu.design.memo.Soul();
        Person person1 = new Person("1");
        person1.addStory("A");
        person1.addStory("B");
        person1.addStory("C");
        person1.addStory("D");
        s1.pullAllMemory(person1);
        person1.addStory("不好的记忆");
        log.info("{}",person1);
        s1.forceFixMemory(person1);
        log.info("{}",person1);
        person1.addStory("E");
        log.info("{}",person1);
        log.info("-----------------------------");

        log.info("享元模式");
        HeroManager heroManager = HeroManager.getInstance();
        Role role1 = new Role(heroManager.getHero("恶魔巫师"));
        Role role2 = new Role(heroManager.getHero("小巧"));
        Role role3 = new Role(heroManager.getHero("恶魔巫师"));
        Role role4 = new Role(heroManager.getHero("小巧"));
        log.info("-----------------------------");

        log.info("组合模式");
        IFile root = new Folder("我的电脑");
        root.createNewFile("C盘");
        root.createNewFile("D盘");
        root.createNewFile("E盘");
        IFile D = root.getIFile(1);
        D.createNewFile("project");
        D.createNewFile("电影");
        IFile project = D.getIFile(0);
        project.createNewFile("1.java");
        project.createNewFile("2.java");
        project.createNewFile("3.java");
        IFile movie = D.getIFile(1);
        movie.createNewFile("致青春.avi");
        movie.createNewFile("速度与激情.avi");
        display(null,root);
        project.delete();
        display(null,root);
        movie.getIFile(0).delete();
        display(null,root);
        log.info("-----------------------------");

        log.info("责任链模式");
        Map<String,Integer> menu = new HashMap<>();
        menu.put("汉堡", 5);
        menu.put("薯条", 5);
        menu.put("可乐", 5);
        menu.put("雪碧", 5);
        Subbranch mcSubbranch1 = new McSubbranch(0,0,new HashMap<String,Integer>(menu));
        Subbranch mcSubbranch2 = new McSubbranch(100, 120, new HashMap<String, Integer>(menu));
        Subbranch mcSubbranch3 = new McSubbranch(-100, -120, new HashMap<String, Integer>(menu));
        Subbranch mcSubbranch4 = new McSubbranch(1000, 20, new HashMap<String, Integer>(menu));
        Subbranch mcSubbranch5 = new McSubbranch(-500, 0, new HashMap<String, Integer>(menu));
        mcSubbranch4.setSuccessor(mcSubbranch5);
        mcSubbranch3.setSuccessor(mcSubbranch4);
        mcSubbranch2.setSuccessor(mcSubbranch3);
        mcSubbranch1.setSuccessor(mcSubbranch2);
        Map<String,Integer> order = new HashMap<String,Integer>();
        order.put("汉堡", 2);
        order.put("可乐", 1);
        order.put("薯条", 1);
        print(mcSubbranch1);
        mcSubbranch1.handleOrder(new Order(900,20,order));
        print(mcSubbranch1);
        log.info("-----------------------------");

        log.info("状态模式");
        Hero hero = new Hero();
        hero.startRun();
        Thread.sleep(5000L);
        hero.setState(Hero.SPEED_UP);
        Thread.sleep(5000L);
        hero.setState(Hero.SPEED_DOWN);
        Thread.sleep(5000L);
        hero.setState(Hero.SWIM_STATE);
        Thread.sleep(5000L);
        hero.stop();
        log.info("-----------------------------");

        log.info("访问者模式");
        AccountBook accountBook = new AccountBook();
        //添加两条收入
        accountBook.addBill(new IncomeBill(1000000,"卖软件"));
        accountBook.addBill(new IncomeBill(2000000,"售后"));
        //添加两条支出
        accountBook.addBill(new ConsumeBill(50000,"工资"));
        accountBook.addBill(new ConsumeBill(100000,"固定投资"));
        AccountBookViewer boss = new Boss();
        AccountBookViewer cpa = new Cpa();
        accountBook.show(boss);
        ((Boss)boss).getTotalIncome();
        ((Boss)boss).getTotalConsume();
        accountBook.show(cpa);
        log.info("-----------------------------");
    }

    public static void display(String prefix, IFile iFile){
        if (null == prefix){
            prefix = "";
        }
        log.info("{}",prefix+iFile.getName());
        if (iFile instanceof Folder){
            for (int i = 0; ;i++){
                try {
                    if (iFile.getIFile(i) != null){
                        display(prefix+"--",iFile.getIFile(i));
                    }
                } catch (Exception e) {
                    break;
                }
            }
        }
    }

    public static void print(Subbranch subbranch){
        if (subbranch == null ) {
            return;
        }
        do {
            if (subbranch instanceof McSubbranch) {
                log.info("[{}]的菜单:{}",subbranch,((McSubbranch) subbranch).getMenu());
            }
        } while ((subbranch = ((McSubbranch) subbranch).getNextSubbranch()) != null);
    }
}
