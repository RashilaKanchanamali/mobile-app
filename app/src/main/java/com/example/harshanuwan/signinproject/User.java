package com.example.harshanuwan.signinproject;

import java.util.Arrays;

public class User {
    public String user_id;
    public String user_name;
    public String user_img;

    public User(){

    }
    public User(String user_id, String user_name, String user_img) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_img = user_img;

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }
}
