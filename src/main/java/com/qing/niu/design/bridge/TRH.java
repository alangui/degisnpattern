package com.qing.niu.design.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/28 14:11
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public class TRH implements Appearance{

    @Override
    public void show() {
        log.info("高富帅形象");
    }
}
