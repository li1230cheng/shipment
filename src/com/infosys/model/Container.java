package com.infosys.model;

import java.math.BigDecimal;
import java.util.List;

public class Container {
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 比例
     */
    private BigDecimal ratio;
    /**
     * 子节点
     */
    private List<Container> childNode;

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public List<Container> getChildNode() {
        return childNode;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public void setChildNode(List<Container> childNode) {
        this.childNode = childNode;
    }

    private Container(){

    }

    public static Container newInstance(Integer quantity,BigDecimal ratio) {

        Container container = new Container();
        container.quantity=quantity;
        container.ratio=ratio;

        return container;
    }





}

