package com.qing.niu.design.builder;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/28 15:23
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class FatBuilder implements DoppelgangerBuilder{

    private Doppelganger doppelganger;

    @Override
    public void createDoppelganger(String name){
        doppelganger = new Doppelganger(name);
    }

    @Override
    public Doppelganger getDoppelganger(){
        try {
            return doppelganger;
        }finally{
            doppelganger = null;
        }
    }

    @Override
    public void buildBody() {
        System.out.println("设置" + doppelganger.getName() + "的胖胖的身体");
        doppelganger.setBody("胖胖的身体");
    }

    @Override
    public void buildHead() {
        System.out.println("设置" + doppelganger.getName() + "的大大的头");
        doppelganger.setHead("大大的头");
    }

    @Override
    public void buildLeftArm() {
        System.out.println("设置" + doppelganger.getName() + "的胖胖的左胳膊");
        doppelganger.setLeftArm("胖胖的左胳膊");
    }

    @Override
    public void buildLeftHand() {
        System.out.println("设置" + doppelganger.getName() + "的胖胖的左手");
        doppelganger.setLeftHand("胖胖的左手");
    }

    @Override
    public void buildRightArm() {
        System.out.println("设置" + doppelganger.getName() + "的胖胖的右胳膊");
        doppelganger.setRightArm("胖胖的右胳膊");
    }

    @Override
    public void buildRightHand() {
        System.out.println("设置" + doppelganger.getName() + "的胖胖的右手");
        doppelganger.setRightHand("胖胖的右手");
    }

    @Override
    public void buildLeftLeg() {
        System.out.println("设置" + doppelganger.getName() + "的胖胖的左腿");
        doppelganger.setLeftLeg("胖胖的左腿");
    }

    @Override
    public void buildLeftFoot() {
        System.out.println("设置" + doppelganger.getName() + "的大大的左脚");
        doppelganger.setLeftFoot("大大的左脚");
    }

    @Override
    public void buildRightLeg() {
        System.out.println("设置" + doppelganger.getName() + "的胖胖的右腿");
        doppelganger.setRightLeg("胖胖的右腿");
    }

    @Override
    public void buildRightFoot() {
        System.out.println("设置" + doppelganger.getName() + "的大大的右脚");
        doppelganger.setRightFoot("大大的右脚");
    }
}
