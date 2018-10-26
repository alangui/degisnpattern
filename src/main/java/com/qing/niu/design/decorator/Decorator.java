package com.qing.niu.design.decorator;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/26
 */
public abstract class Decorator implements Component{

    protected Component component;

    public Decorator(Component component){
        super();
        this.component = component;
    }

    @Override
    public void method(){
        component.method();
    }
}
