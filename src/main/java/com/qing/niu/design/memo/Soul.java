package com.qing.niu.design.memo;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/28 23:42
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class Soul {

    private List<String> storys;

    public void pullAllMemory(Person person){
        this.storys = person.getStoryList();
    }

    public void forceFixMemory(Person person){
        person.setStoryList(storys);
    }

}
