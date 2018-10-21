package com.qing.niu.design.observer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/21 17:42
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
@Setter
@Getter
public class Reader implements Observer{

    private String name;

    public Reader(String name){
        this.name = name;
    }

    public void subscribe(String writerName){
        WriterManager.getInstance().getWriter(writerName).addObserver(this);
    }

    public void unSubcribe(String writerName){
        WriterManager.getInstance().getWriter(writerName).deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Writer){
            Writer writer = (Writer) o;
            log.info("{}知道了{}发布了新书《{}》",name,writer.getName(),writer.getLastNovel());
        }
    }
}
