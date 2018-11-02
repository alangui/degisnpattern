package com.qing.niu.design.state;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/11/1 23:15
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public class Hero {

    public static final RunState COMMON = new CommonState();

    public static final RunState SPEED_UP = new SpeedUpState();

    public static final RunState SPEED_DOWN = new SpeedDownState();

    public static final RunState SWIM_STATE = new SwimState();

    private RunState state = COMMON;

    private Thread runThread;

    public void setState(RunState runState){
        state = runState;
    }

    public void stop(){
        if (isRunning()){
            runThread.interrupt();
        }
        log.info("----------停止跑动-----------");
    }

    public void startRun(){
        if (isRunning()){
            return;
        }
        final Hero hero = this;
        runThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!runThread.isInterrupted()){
                    state.run(hero);
                }
            }
        });
        log.info("-------------开始跑动--------------");
        runThread.start();
    }

    private boolean isRunning(){
        return null != runThread && !runThread.isInterrupted();
    }
}
