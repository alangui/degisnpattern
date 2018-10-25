package com.qing.niu.design.templet;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/25
 */
@Slf4j
public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException{
        String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
        log.info("加载的类文件:{}",fileName);
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        if (null == inputStream){
            log.info("class文件未发现");
            return super.loadClass(name);
        }
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return defineClass(name,bytes,0,bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
}
