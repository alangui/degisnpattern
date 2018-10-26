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
public class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    public void methodB(){
        log.info("装饰类B扩展的功能");
    }

    @Override
    public void method(){
        log.info("针对该方法加一层B包装");
        component.method();
    }
}
