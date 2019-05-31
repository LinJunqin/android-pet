package com.lin.person.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by lin on 2019/5/13.
 */

public class Order implements Serializable{
    /**
     */
    private Long goodsId;


    /**
     */
    private String name;
    /**
     */
    private String avatar;
    /**
     */
    private Long orderId;

    /**
     */
    private Byte sum;

    /**
     */
    private BigDecimal account;
    /**
     */
    private Byte status;

    public Order() {
    }

    public Order(Long goodsId, String name, String avatar, Long orderId, Byte sum, BigDecimal account, Byte status) {
        this.goodsId = goodsId;
        this.name = name;
        this.avatar = avatar;
        this.orderId = orderId;
        this.sum = sum;
        this.account = account;
        this.status = status;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Byte getSum() {
        return sum;
    }

    public void setSum(Byte sum) {
        this.sum = sum;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

}
