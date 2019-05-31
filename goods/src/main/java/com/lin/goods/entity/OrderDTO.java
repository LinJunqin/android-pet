package com.lin.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDTO implements Serializable{
    /**
     */
    private Long userId;

    /**
     */
    private Long storeId;

    /**
     */
    private Long goodsId;

    /**
     */
    private Byte sum;

    /**
     */
    private BigDecimal account;

    /**
     */
    private String province;

    /**
     */
    private String city;

    /**
     */
    private String county;

    /**
     */
    private String detail;

    /**
     */
    private String phone;

    /**
     */
    private String recevierName;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRecevierName() {
        return recevierName;
    }

    public void setRecevierName(String recevierName) {
        this.recevierName = recevierName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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
}
