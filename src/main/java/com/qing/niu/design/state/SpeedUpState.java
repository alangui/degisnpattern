package com.qing.niu.design.state;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/11/1 23:18
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public class SpeedUpState implements RunState{

    @Override
    public void run(Hero hero) {
        log.info("----------加速跑动----------");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            log.info("加速跑动异常:{}", Throwables.getStackTraceAsString(e));
        }
        hero.setState(Hero.COMMON);
        log.info("----------加速状态结束，恢复正常状态----------");
    }
}
