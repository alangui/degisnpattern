package com.qing.niu.design.strategy;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/24
 */
@TotalValidRegion(min = 2000,max = 3000)
public class SuperVip implements CalPrice{

    /**
     * 计算价格
     *
     * @param originalPrice 原价格
     * @return 计算后的价格
     */
    @Override
    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.7;
    }
}
