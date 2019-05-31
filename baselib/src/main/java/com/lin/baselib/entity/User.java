package com.lin.baselib.entity;

/**
 * Created by lin on 2019/5/22.
 */

public class User {
    private static User user;
    public static User getUser(){
        return user;
    }
    public static void init(Long userId, String phone, String username, Boolean gender, String avatar){
        if(user==null){
            synchronized(User.class){
                if(user==null){
                    user = new User(userId,phone,username,gender,avatar);
                }
            }
        }
    }
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

    private User(Long userId, String phone, String username, Boolean gender, String avatar) {
        this.userId = userId;
        this.phone = phone;
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

}
