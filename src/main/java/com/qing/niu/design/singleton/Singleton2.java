package com.qing.niu.design.singleton;

/**
 * <p>
 *     标准单例模式
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/15 23:09
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class Singleton2 {

    private Singleton2(){}

    public static Singleton2 getInstance(){
        return SingletonInstance.singleton2;
    }

    private static class SingletonInstance{
        static Singleton2 singleton2 = new Singleton2();
    }
}
