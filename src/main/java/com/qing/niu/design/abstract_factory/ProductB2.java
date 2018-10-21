package com.qing.niu.design.abstract_factory;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/21 16:28
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public class ProductB2 implements ProductB{
    @Override
    public void methodB() {
        log.info("产品B系列型号2产品");
    }
}
