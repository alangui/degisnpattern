package com.qing.niu.design.factory;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/21 11:14
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class BuldCreator implements Creator{
    @Override
    public Light createLight() {
        return new BuldLight();
    }
}
