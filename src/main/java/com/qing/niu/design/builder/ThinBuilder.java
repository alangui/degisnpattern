package com.qing.niu.design.builder;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/28 14:59
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class ThinBuilder implements DoppelgangerBuilder{

    private Doppelganger doppelganger;

    @Override
    public void createDoppelganger(String name) {
        doppelganger = new Doppelganger(name);
    }

    @Override
    public Doppelganger getDoppelganger(){
        try {
            return doppelganger;
        } finally {
            doppelganger = null;
        }
    }

    @Override
    public void buildBody() {
        System.out.println("设置" + doppelganger.getName() + "的瘦瘦的身体");
        doppelganger.setBody("瘦瘦的身体");
    }

    @Override
    public void buildHead() {
        System.out.println("设置" + doppelganger.getName() + "的小小的头");
        doppelganger.setHead("小小的头");
    }

    @Override
    public void buildLeftArm() {
        System.out.println("设置" + doppelganger.getName() + "的瘦瘦的左胳膊");
        doppelganger.setLeftArm("瘦瘦的左胳膊");
    }

    @Override
    public void buildLeftHand() {
        System.out.println("设置" + doppelganger.getName() + "的瘦瘦的左手");
        doppelganger.setLeftHand("瘦瘦的左手");
    }

    @Override
    public void buildRightArm() {
        System.out.println("设置" + doppelganger.getName() + "的瘦瘦的右胳膊");
        doppelganger.setRightArm("瘦瘦的右胳膊");
    }

    @Override
    public void buildRightHand() {
        System.out.println("设置" + doppelganger.getName() + "的瘦瘦的右手");
        doppelganger.setRightHand("瘦瘦的右手");
    }

    @Override
    public void buildLeftLeg() {
        System.out.println("设置" + doppelganger.getName() + "的瘦瘦的左腿");
        doppelganger.setLeftLeg("瘦瘦的左腿");
    }

    @Override
    public void buildLeftFoot() {
        System.out.println("设置" + doppelganger.getName() + "的小小的左脚");
        doppelganger.setLeftFoot("小小的左脚");
    }

    @Override
    public void buildRightLeg() {
        System.out.println("设置" + doppelganger.getName() + "的瘦瘦的右腿");
        doppelganger.setRightLeg("瘦瘦的右腿");
    }

    @Override
    public void buildRightFoot() {
        System.out.println("设置" + doppelganger.getName() + "的小小的右脚");
        doppelganger.setRightFoot("小小的右脚");
    }
}
