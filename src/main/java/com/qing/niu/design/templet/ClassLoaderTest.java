package com.qing.niu.design.templet;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/25
 */
@Slf4j
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception{
        Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("com.qing.niu.design.templet.ClassLoaderTest");
        Object entity = clazz.newInstance();
        log.info("{}",ClassLoader.getSystemClassLoader());
        log.info("{}",entity instanceof ClassLoaderTest);

        ClassLoader classLoader = new MyClassLoader();
        Class<?> myClazz = classLoader.loadClass("com.qing.niu.design.templet.ClassLoaderTest");
        Object myEntity = myClazz.newInstance();
        log.info("{}",classLoader);
        log.info("{}",myEntity instanceof ClassLoaderTest);
    }

}
