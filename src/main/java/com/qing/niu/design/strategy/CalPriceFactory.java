package com.qing.niu.design.strategy;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/24
 */
public class CalPriceFactory {

    private static final String CAL_PRICE_PACKAGE = "com.qing.niu.design.strategy";

    private ClassLoader classLoader = getClass().getClassLoader();

    private List<Class<? extends CalPrice>> calPriceList;

    public CalPrice createCalPrice(Customer customer){
        for (Class<? extends CalPrice> clazz : calPriceList){
            TotalValidRegion totalValidRegion = handleAnnotation(clazz);
            if (customer.getTotalAmount() >= totalValidRegion.min() && customer.getTotalAmount() < totalValidRegion.max()){
                try {
                    return clazz.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("策略获得失败");
                }
            }
        }
        throw new RuntimeException("策略获得失败");
    }

    private TotalValidRegion handleAnnotation(Class<? extends CalPrice> clazz){
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        if (null == annotations || annotations.length ==0){
            return null;
        }
        for (int i = 0; i < annotations.length; i++){
            if (annotations[i] instanceof TotalValidRegion){
                return (TotalValidRegion) annotations[i];
            }
        }
        return null;
    }

    private CalPriceFactory(){
        init();
    }

    private void init(){
        calPriceList = new ArrayList<>();
        File[] resources = getResources();
        Class<CalPrice> calPriceClass = null;
        try {
            calPriceClass = (Class<CalPrice>) classLoader.loadClass(CalPrice.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("未找到策略接口");
        }
        for (int i = 0; i < resources.length; i++){
            try {
                Class<?> clazz = classLoader.loadClass(CAL_PRICE_PACKAGE + "." + resources[i].getName().replace(".class",""));
                if (CalPrice.class.isAssignableFrom(clazz) && clazz != calPriceClass){
                    calPriceList.add((Class<? extends CalPrice>) clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private File[] getResources(){
        try {
            File file = new File(classLoader.getResource(CAL_PRICE_PACKAGE.replace(".","/")).toURI());
            return file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.getName().endsWith(".class")){
                        return true;
                    }
                    return false;
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException("未找到策略资源");
        }
    }

    public static CalPriceFactory getInstance(){
        return CalPriceFactoryInstance.instance;
    }

    private static class CalPriceFactoryInstance{
        private static CalPriceFactory instance = new CalPriceFactory();
    }
}
