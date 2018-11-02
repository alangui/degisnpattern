package com.qing.niu.design.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/11/2
 */
@Slf4j
public class Cpa implements AccountBookViewer{

    @Override
    public void view(ConsumeBill consumeBill) {
        if ("工资".equals(consumeBill.getItem())){
            log.info("会计查看账本时，如果单子的消费目的是发工资，则会计会查看有没有交个人所得税");
        }
    }

    @Override
    public void view(IncomeBill incomeBill) {
        log.info("会计查看账本时，只要是收入，会计都要查看公司交税了没");
    }
}
