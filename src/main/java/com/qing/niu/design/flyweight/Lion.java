package com.qing.niu.design.flyweight;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/31 23:45
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class Lion extends AbstractHero {

    @Override
    public String getName() {
        return "干将";
    }

    @Override
    public void initSkills() {
        skills[0] = "1技能";
        skills[1] = "2技能";
        skills[2] = "3技能";
        skills[3] = "4技能";
    }
}
