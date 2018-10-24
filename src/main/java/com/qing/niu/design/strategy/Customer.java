package com.qing.niu.design.strategy;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/24
 */
@Setter
@Getter
public class Customer {

    private Double totalAmount = 0D;

    private Double amount = 0D;

    private CalPrice calPrice = new Common();

    public void buy(Double amount){
        this.amount = amount;
        totalAmount += amount;
        calPrice = CalPriceFactory.getInstance().createCalPrice(this);
    }

    public Double calLastAmount(){
        return calPrice.calPrice(amount);
    }
}
