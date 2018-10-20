package com.qing.niu.design.singleton;

/**
 * <p>
 *     双层加锁模式单例
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/15 22:59
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class Singleton1 {

    private static Singleton1 singleton1;

    private Singleton1(){}

    public static Singleton1 getInstance(){
        if (null == singleton1){
            synchronized (Singleton1.class){
                singleton1 = new Singleton1();
            }
        }
        return singleton1;
    }
}
