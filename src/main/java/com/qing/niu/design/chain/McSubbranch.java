package com.qing.niu.design.chain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/11/1
 */
@Slf4j
public class McSubbranch implements Subbranch{

    private final static int MIN_DISTANCE = 500;

    private static int count = 1;

    private int number;

    private int x;

    private int y;

    private Map<String,Integer> menu;

    @Getter
    private Subbranch nextSubbranch;

    public McSubbranch(int x, int y, Map<String,Integer> memu){
        this.x = x;
        this.y = y;
        this.menu = memu;
        this.number = count++;
    }

    @Override
    public void setSuccessor(Subbranch successor) {
        this.nextSubbranch = successor;
    }

    @Override
    public boolean handleOrder(Order order) {
        if (CommonUtils.getDistance(order.getX(),order.getY(),this.x,this.y) < MIN_DISTANCE &&
                !CommonUtils.outOfStock(menu,order.getOrder())){
            for (String name : order.getOrder().keySet()){
                menu.put(name,menu.get(name) - order.getOrder().get(name));
            }
            log.info("订餐成功，接受订单的分店是:{}",this);
            return true;
        }
        if (null == nextSubbranch){
            return false;
        }
        return nextSubbranch.handleOrder(order);
    }

    public Map<String,Integer> getMenu(){
        return Collections.unmodifiableMap(menu);
    }

    @Override
    public String toString() {
        return "麦当劳分店第" + number + "个";
    }
}
