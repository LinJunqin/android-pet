package com.lin.person.entity;

public class AddressDTO {
    /**
     */
    private Long addressId;

    /**
     */
    private Long userId;

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
    private Byte isMain;

    /**
     */
    private String phone;

    /**
     */
    private String name;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Byte getIsMain() {
        return isMain;
    }

    public void setIsMain(Byte isMain) {
        this.isMain = isMain;
    }
}
