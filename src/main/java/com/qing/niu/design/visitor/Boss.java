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
public class Boss implements AccountBookViewer{

    private double totalIncome;

    private double totalConsume;

    @Override
    public void view(ConsumeBill consumeBill) {
        totalConsume += consumeBill.getAmount();
    }

    @Override
    public void view(IncomeBill incomeBill) {
        totalIncome += incomeBill.getAmount();
    }

    public double getTotalIncome(){
        log.info("老板查看一共收入，数目是:{}",totalIncome);
        return totalIncome;
    }

    public double getTotalConsume(){
        log.info("老板查看一共支出，数目是:{}",totalConsume);
        return totalConsume;
    }
}
