package com.dd.onlinegoodsms.Entity;

import lombok.Data;

import java.sql.Timestamp;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private Timestamp  createTime;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(int id, String username, String password, String role, Timestamp createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createTime = createTime;
    }

    public User(String username, String password, String role, Timestamp createTime) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getRole() {
        return role;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
}
