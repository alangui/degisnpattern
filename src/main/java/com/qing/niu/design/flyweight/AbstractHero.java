package com.qing.niu.design.flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/31 23:36
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public abstract class AbstractHero {

    protected String name;

    protected final String[] skills = new String[4];

    public AbstractHero(){
        this.name = getName();
        initSkills();
        checkSkills();
    }

    private void checkSkills(){
        for (int i = 0; i < skills.length; i++){
            if (skills[i] == null){
                throw new NullPointerException();
            }
        }
    }

    public void release(int index){
        if (index < 0){
            index = 0;
        }else if (index > 3){
            index = 3;
        }
        log.info("{}释放{}",name,skills[index]);
    }

    public void commonAttack(){
        log.info("{}发起普通攻击",name);
    }

    public abstract String getName();

    public abstract void initSkills();
}
