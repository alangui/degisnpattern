package com.qing.niu.design.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/11/2
 */
public class AccountBook {

    private List<Bill> billList = new ArrayList<>();

    public void addBill(Bill bill){
        billList.add(bill);
    }

    public void show(AccountBookViewer accountBookViewer){
        for (Bill bill : billList){
            bill.accept(accountBookViewer);
        }
    }
}
