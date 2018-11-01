package com.qing.niu.design.chain;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/11/1
 */
public interface Subbranch {

    void setSuccessor(Subbranch successor);

    boolean handleOrder(Order order);
}
