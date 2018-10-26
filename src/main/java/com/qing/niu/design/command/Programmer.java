package com.qing.niu.design.command;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/26
 */
@Getter
@Slf4j
@ToString
public class Programmer {

    private String name;

    public Programmer(String name){
        this.name = name;
    }

    public void handleDemand(){
        log.info(name + "处理新需求");
    }

    public void handleBug(){
        log.info(name + "处理bug");
    }

    public void handleProblem(){
        log.info(name + "处理线上问题");
    }
}
