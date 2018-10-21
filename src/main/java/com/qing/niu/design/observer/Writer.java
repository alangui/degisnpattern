package com.qing.niu.design.observer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Observable;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/21 17:43
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Getter
@Setter
@Slf4j
public class Writer extends Observable{

    private String name;

    private String lastNovel;

    public Writer(String name){
        super();
        this.name = name;
        WriterManager.getInstance().addWriter(this);
    }

    public void addNovel(String bookName){
        log.info("{}发布了新书《{}》",this.name,bookName);
        this.lastNovel = bookName;
        setChanged();
        notifyObservers();
    }

}
