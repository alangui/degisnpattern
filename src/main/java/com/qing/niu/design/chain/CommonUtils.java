package com.qing.niu.design.chain;


import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/11/1
 */
public class CommonUtils {

    private CommonUtils(){}

    public static double getDistance(int x1, int y1, int x2, int y2){
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static boolean outOfStock(Map<String,Integer> menu, Map<String,Integer> order){
        if (order == null || order.size() == 0){
            return false;
        }
        if (menu == null || menu.size() ==0){
            return true;
        }
        for (String name : order.keySet()){
            if (menu.get(name) - order.get(name) < 0){
                return true;
            }
        }
        return false;
    }
}
