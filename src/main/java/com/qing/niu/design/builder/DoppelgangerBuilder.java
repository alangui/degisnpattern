package com.qing.niu.design.builder;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/28 14:55
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public interface DoppelgangerBuilder {

    void createDoppelganger(String name);

    void buildBody();

    void buildHead();

    void buildLeftArm();

    void buildLeftHand();

    void buildRightArm();

    void buildRightHand();

    void buildLeftLeg();

    void buildLeftFoot();

    void buildRightLeg();

    void buildRightFoot();

    Doppelganger getDoppelganger();

}
