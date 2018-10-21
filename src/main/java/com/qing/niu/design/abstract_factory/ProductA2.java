package com.qing.niu.design.abstract_factory;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/21 16:27
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public class ProductA2 implements ProductA{
    @Override
    public void methodA() {
        log.info("产品A系列型号2产品");
    }
}
