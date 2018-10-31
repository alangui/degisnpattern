package com.qing.niu.design.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/31 23:53
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class HeroManager {

    private static HeroManager heroManager = new HeroManager();

    private Map<String,AbstractHero> heroMap;

    private HeroManager(){
        heroMap = new HashMap<String,AbstractHero>();
    }

    public static HeroManager getInstance(){
        return heroManager;
    }

    public AbstractHero getHero(String name){
        AbstractHero hero = heroMap.get(name);
        if (null == hero){
            if ("干将".equals(name)){
                hero = new Lion();
                heroMap.put(name,hero);
            }else if ("小巧".equals(name)){
                hero = new Sf();
                heroMap.put(name,hero);
            }
        }
        return hero;
    }
}
