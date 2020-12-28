package com.znzz.springboot_security.entity;

import lombok.Data;


public class Users {
    private int uid;
    private String username;
    private String password;

    public int getId() {
        return uid;
    }

    public void setId(int id) {
        this.uid = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
