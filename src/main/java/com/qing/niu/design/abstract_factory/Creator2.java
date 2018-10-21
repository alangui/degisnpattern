package com.qing.niu.design.abstract_factory;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/21 16:34
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class Creator2 implements Creator{
    @Override
    public ProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB2();
    }
}
