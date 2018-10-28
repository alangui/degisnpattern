package com.qing.niu.design.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/28 14:09
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public class ReadMind implements Skills{

    @Override
    public void releaseSkills() {
        log.info("释放读心技能");
    }
}
