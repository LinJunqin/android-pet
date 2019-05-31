package com.lin.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GoodsDetailDTO implements Serializable{

    /**
     */
    private Long goodsId;


    /**
     */
    private String name;
    /**
     */
    private BigDecimal price;

    /**
     */
    private List<String> avatar;
    /**
     */
    private Short sum;
    /**
     */
    private String detailImage;
    /**
     */
    private Long storeId;

    public GoodsDetailDTO(Long goodsId, String name, BigDecimal price, Short sum, String detailImage, Long storeId) {
        this.goodsId = goodsId;
        this.name = name;
        this.price = price;
        this.sum = sum;
        this.detailImage = detailImage;
        this.storeId = storeId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getAvatar() {
        return avatar;
    }

    public void setAvatar(List<String> avatar) {
        this.avatar = avatar;
    }

    public Short getSum() {
        return sum;
    }

    public void setSum(Short sum) {
        this.sum = sum;
    }

    public String getDetailImage() {
        return detailImage;
    }

    public void setDetailImage(String detailImage) {
        this.detailImage = detailImage;
    }
}
