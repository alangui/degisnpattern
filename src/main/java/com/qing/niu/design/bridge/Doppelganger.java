package com.qing.niu.design.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/28 14:31
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public class Doppelganger extends Soul{

    public Doppelganger(){
        log.info("制作一个暂无外貌和技能的分身");
    }

    @Override
    public void doAllLikePeople() {
        log.info("正常人");
    }
}
