package com.qing.niu.design.bridge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/28 14:12
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Soul {

    protected Appearance appearance;

    protected Skills skills;

    public void show(){
        appearance.show();
    }

    public void release(){
        skills.releaseSkills();
    }

    public abstract void doAllLikePeople();
}
