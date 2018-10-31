package com.qing.niu.design.flyweight;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/31 23:50
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class Role {

    private AbstractHero hero;

    @Getter
    @Setter
    private long hp;

    @Getter
    @Setter
    private long mp;

    public Role(AbstractHero hero){
        super();
        this.hero = hero;
    }

    public void release(int index){
        hero.release(index);
    }

    public void commonAttack(){
        hero.commonAttack();
    }
}
