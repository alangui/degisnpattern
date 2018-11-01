package com.qing.niu.design.chain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/11/1
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Order implements Serializable{
    private static final long serialVersionUID = -7859830632166253422L;

    private int x;

    private int y;

    private Map<String,Integer> order;
}
