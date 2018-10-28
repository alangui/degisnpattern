package com.qing.niu.design.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/28 14:10
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public class Loser implements Appearance{

    @Override
    public void show() {
        log.info("展示屌丝形象");
    }
}
