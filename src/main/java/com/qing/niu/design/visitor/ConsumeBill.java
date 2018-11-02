package com.qing.niu.design.visitor;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/11/2
 */
@Getter
@AllArgsConstructor
public class ConsumeBill implements Bill{

    private double amount;

    private String item;

    @Override
    public void accept(AccountBookViewer accountBookViewer) {
        accountBookViewer.view(this);
    }
}
