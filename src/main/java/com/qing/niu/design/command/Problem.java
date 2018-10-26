package com.qing.niu.design.command;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/26
 */
public class Problem implements Task{

    private Programmer programmer;

    public Problem(Programmer programmer){
        this.programmer = programmer;
    }

    @Override
    public void handle() {
        programmer.handleProblem();
    }
}
