package com.lin.store.entity;

import java.math.BigDecimal;

/**
 * Created by lin on 2019/5/14.
 */

public class ProjectDTO {
    /**
     */
    private Long projectId;
    /**
     */
    private String title;
    /**
     */
    private BigDecimal value;
    /**
     */
    private Long hospitalId;

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public ProjectDTO() {
    }

    public ProjectDTO(Long projectId, String title, BigDecimal value) {
        this.projectId = projectId;
        this.title = title;
        this.value = value;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "projectId=" + projectId +
                ", title='" + title + '\'' +
                ", value=" + value +
                '}';
    }
}
