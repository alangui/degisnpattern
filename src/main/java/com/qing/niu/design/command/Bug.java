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
public class Bug implements Task{

    private Programmer programmer;

    public Bug(Programmer programmer){
        this.programmer = programmer;
    }

    @Override
    public void handle() {
        programmer.handleBug();
    }
}
