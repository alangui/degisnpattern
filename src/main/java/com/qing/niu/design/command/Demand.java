package com.qing.niu.design.command;

import lombok.ToString;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/26
 */
@ToString
public class Demand implements Task{

    private Programmer programmer;

    public Demand(Programmer programmer){
        this.programmer = programmer;
    }

    @Override
    public void handle() {
        programmer.handleDemand();
    }
}
