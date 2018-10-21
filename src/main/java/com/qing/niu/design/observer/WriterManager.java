package com.qing.niu.design.observer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/21 17:49
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class WriterManager {

    private Map<String,Writer> map = new HashMap<>();

    public void addWriter(Writer writer){
        map.put(writer.getName(),writer);
    }

    public Writer getWriter(String name){
        return map.get(name);
    }

    public static WriterManager getInstance(){
        return WriterManagerInstance.writerManager;
    }

    private static class WriterManagerInstance{
        private static WriterManager writerManager = new WriterManager();
    }
}
