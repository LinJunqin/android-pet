package com.lin.goods.entity;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author linjunqin
 * @date 2019-05-23
 */
public class Address implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = -7741361543051694368L;

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
    private Date createTime;

    /** 
     */ 
    private Date modifiedTime;

    /** 
     */ 
    private Byte isMain;

    /** 
     */ 
    private String phone;

    /** 
     */ 
    private String name;

    /** 
     * 构造查询条件
     */
    public Address(Long addressId, Long userId, String province, String city, String county, String detail, Date createTime, Date modifiedTime, Byte isMain, String phone, String name) {
        this.addressId = addressId;
        this.userId = userId;
        this.province = province;
        this.city = city;
        this.county = county;
        this.detail = detail;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.isMain = isMain;
        this.phone = phone;
        this.name = name;
    }

    /** 
     * 构造查询条件
     */
    public Address() {
        super();
    }

    /** 
     * 获取 address.address_id
     * @return address.address_id
     */
    public Long getAddressId() {
        return addressId;
    }

    /** 
     * 设置 address.address_id
     * @param addressId address.address_id
     */
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    /** 
     * 获取 address.user_id
     * @return address.user_id
     */
    public Long getUserId() {
        return userId;
    }

    /** 
     * 设置 address.user_id
     * @param userId address.user_id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /** 
     * 获取 address.province
     * @return address.province
     */
    public String getProvince() {
        return province;
    }

    /** 
     * 设置 address.province
     * @param province address.province
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /** 
     * 获取 address.city
     * @return address.city
     */
    public String getCity() {
        return city;
    }

    /** 
     * 设置 address.city
     * @param city address.city
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /** 
     * 获取 address.county
     * @return address.county
     */
    public String getCounty() {
        return county;
    }

    /** 
     * 设置 address.county
     * @param county address.county
     */
    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    /** 
     * 获取 address.detail
     * @return address.detail
     */
    public String getDetail() {
        return detail;
    }

    /** 
     * 设置 address.detail
     * @param detail address.detail
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /** 
     * 获取 address.create_time
     * @return address.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /** 
     * 设置 address.create_time
     * @param createTime address.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
     * 获取 address.modified_time
     * @return address.modified_time
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /** 
     * 设置 address.modified_time
     * @param modifiedTime address.modified_time
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /** 
     * 获取 address.is_main
     * @return address.is_main
     */
    public Byte getIsMain() {
        return isMain;
    }

    /** 
     * 设置 address.is_main
     * @param isMain address.is_main
     */
    public void setIsMain(Byte isMain) {
        this.isMain = isMain;
    }

    /** 
     * 获取 address.phone
     * @return address.phone
     */
    public String getPhone() {
        return phone;
    }

    /** 
     * 设置 address.phone
     * @param phone address.phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /** 
     * 获取 address.name
     * @return address.name
     */
    public String getName() {
        return name;
    }

    /** 
     * 设置 address.name
     * @param name address.name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", addressId=").append(addressId);
        sb.append(", userId=").append(userId);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", county=").append(county);
        sb.append(", detail=").append(detail);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", isMain=").append(isMain);
        sb.append(", phone=").append(phone);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}