package com.lin.person.entity;

public class UserDTO {
    /**
     */
    private Long userId;

    /**
     */
    private String username;

    /**
     */
    private Boolean gender;

    /**
     */
    private String avatar;
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

    public UserDTO(Long userId, String username, Boolean gender, String avatar) {
        this.userId = userId;
        this.username = username;
        this.gender = gender;
        this.avatar = avatar;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
