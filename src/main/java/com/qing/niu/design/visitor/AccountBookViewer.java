package com.qing.niu.design.visitor;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/11/2
 */
public interface AccountBookViewer {

    void view(ConsumeBill consumeBill);

    void view(IncomeBill incomeBill);
}
