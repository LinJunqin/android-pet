package com.lin.login.entity;


import com.lin.baselib.entity.User;
import com.lin.baselib.mapper.DTO2VO;

/**
 * Created by lin on 2018/9/22.
 */

public class UserDTO implements DTO2VO<User> {
    /**
     */
    private Long userId;

    /**
     */
    private String phone;

    /**
     */
    private String username;

    /**
     */
    private Boolean gender;

    /**
     */
    private String avatar;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public User transform() {
        return null;

    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
