package com.qing.niu.design.builder;

import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/28 14:48
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Doppelganger implements Serializable{
    private static final long serialVersionUID = 266207495279187711L;

    private String name;

    private String body;

    private String head;

    private String leftArm;

    private String leftHand;

    private String rightArm;

    private String rightHand;

    private String leftFoot;

    private String leftLeg;

    private String rightFoot;

    private String rightLeg;

    public Doppelganger(String name) {
        this.name = name;
    }
}
