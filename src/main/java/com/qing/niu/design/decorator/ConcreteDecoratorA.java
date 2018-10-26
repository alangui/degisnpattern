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
public class ConcreteDecoratorA extends Decorator{

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    public void methodA(){
        log.info("装饰类A扩展的功能");
    }

    @Override
    public void method(){
        log.info("针对该方法加一层A包装");
        component.method();
    }
}
