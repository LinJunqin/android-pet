package com.lin.person.entity;

import java.io.Serializable;

/**
 * Created by lin on 2019/5/14.
 */

public class PreOrder implements Serializable{
    /**
     */
    private Long hospitalId;

    /**
     */
    private String hospitalName;
    /**
     */
    private String avatar;

    /**
     */
    private String address;

    /**
     */
    /**
     */
    private String hospitalPhone;
    private Long preorderId;


    /**
     */
    private String arriveTime;

    /**
     */
    private Byte status;

    /**
     */
    private Long projectId;

    private String title;

    public PreOrder(Long hospitalId, String hospitalName, String avatar, String address, String hospitalPhone, Long preorderId, String arriveTime, Byte status, Long projectId, String title) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.avatar = avatar;
        this.address = address;
        this.hospitalPhone = hospitalPhone;
        this.preorderId = preorderId;
        this.arriveTime = arriveTime;
        this.status = status;
        this.projectId = projectId;
        this.title = title;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPreorderId() {
        return preorderId;
    }

    public void setPreorderId(Long preorderId) {
        this.preorderId = preorderId;
    }

    public String getHospitalPhone() {
        return hospitalPhone;
    }

    public void setHospitalPhone(String hospitalPhone) {
        this.hospitalPhone = hospitalPhone;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
