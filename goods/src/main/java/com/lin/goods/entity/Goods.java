package com.lin.goods.entity;

/**
 * Created by lin on 2019/5/8.
 */

public class Goods {
    private String avatar;
    private String name;
    private double price;
    private Long goodsId;
    public Goods(){

    }
    public Goods(String avatar, String name, double price, Long goodsId) {
        this.avatar = avatar;
        this.name = name;
        this.price = price;
        this.goodsId = goodsId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "goods{" +
                "avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", goodsId='" + goodsId + '\'' +
                '}';
    }
}
