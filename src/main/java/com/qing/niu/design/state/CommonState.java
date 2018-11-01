package com.qing.niu.design.state;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/11/1 23:16
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public class CommonState implements RunState{

    @Override
    public void run(Hero hero) {
        log.info("正常跑动");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            log.info("{}", Throwables.getStackTraceAsString(e));
        }
    }
}
