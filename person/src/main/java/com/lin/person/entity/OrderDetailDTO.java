package com.lin.person.entity;

public class OrderDetailDTO {
    /**
     */
    private Long orderId;

    /**
     */
    private String logisticsNum;

    /**
     */
    private String logisticsCompany;
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

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(Long orderId, String logisticsNum, String logisticsCompany, String province, String city, String county, String detail, String phone, String recevierName) {
        this.orderId = orderId;
        this.logisticsNum = logisticsNum;
        this.logisticsCompany = logisticsCompany;
        this.province = province;
        this.city = city;
        this.county = county;
        this.detail = detail;
        this.phone = phone;
        this.recevierName = recevierName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(String logisticsNum) {
        this.logisticsNum = logisticsNum;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

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
}
