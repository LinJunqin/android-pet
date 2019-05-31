package com.lin.person.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class CommentDTO implements Serializable{
    /**
     */
    private Long preorderId;

    /**
     */
    private BigDecimal score;

    /**
     */
    private String content;

    /**
     */
    /**
     */
    private Long hospitalId;

    /**
     */
    private Long userId;

    public Long getPreorderId() {
        return preorderId;
    }

    public void setPreorderId(Long preorderId) {
        this.preorderId = preorderId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "preorderId=" + preorderId +
                ", score=" + score +
                ", content='" + content + '\'' +
                ", hospitalId=" + hospitalId +
                ", userId=" + userId +
                '}';
    }
}
