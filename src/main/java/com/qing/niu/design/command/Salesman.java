package com.qing.niu.design.command;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/26
 */
@Slf4j
public class Salesman {

    private String name;

    private ProductManager productManager;

    public Salesman(String name) {
        super();
        this.name = name;
    }

    public Salesman(String name, ProductManager productManager) {
        super();
        this.name = name;
        this.productManager = productManager;
    }

    public void putDemand(){
        log.info( "业务员" + name + "提出新需求");
        productManager.receive(new Demand(productManager.chooseProgrammer()));
    }

    public void putBug(){
        log.info( "业务员" + name + "提出bug");
        productManager.receive(new Bug(productManager.chooseProgrammer()));
    }

    public void putProblem(){
        log.info( "业务员" + name + "提出线上问题");
        productManager.receive(new Problem(productManager.chooseProgrammer()));
    }

    public String getName() {
        return name;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
}
