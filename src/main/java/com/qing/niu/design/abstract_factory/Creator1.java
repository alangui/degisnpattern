package com.qing.niu.design.abstract_factory;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/21 16:33
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class Creator1 implements Creator{


    @Override
    public ProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB1();
    }
}
