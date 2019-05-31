package com.lin.store.entity;

import java.util.List;

/**
 * Created by lin on 2019/5/8.
 */

public class Store {
    /**
     */
    private Long hospitalId;

    /**
     */
    private String hospitalName;


    /**
     */
    private String avatar;

    private List<String > projectNames;

    private Byte type;
    private Double distance;
    private Double score;

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

    public List<String> getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(List<String> projectNames) {
        this.projectNames = projectNames;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Store{" +
                "hospitalId=" + hospitalId +
                ", hospitalName='" + hospitalName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", projectNames=" + projectNames +
                ", type=" + type +
                ", distance=" + distance +
                ", score=" + score +
                '}';
    }
}
