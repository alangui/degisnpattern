package com.qing.niu.design.memo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/28 23:33
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@ToString
public class Person {

    @Getter
    @Setter
    private String name;

    private List<String> storyList;

    public Person(String name){
        this.name = name;
        storyList = new ArrayList<>();
    }

    public void addStory(String history){
        this.storyList.add(history);
    }

    public List<String> getStoryList(){
        List<String> list = new ArrayList<>(storyList);
        return Collections.unmodifiableList(list);
    }

    public void setStoryList(List<String> storys){
        storyList = new ArrayList<>(storys);
    }
}
