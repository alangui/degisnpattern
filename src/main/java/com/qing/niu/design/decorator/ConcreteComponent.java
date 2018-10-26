package com.qing.niu.design.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/26
 */
@Slf4j
public class ConcreteComponent implements Component{

    @Override
    public void method() {
        log.info("待装饰类方法");
    }
}
