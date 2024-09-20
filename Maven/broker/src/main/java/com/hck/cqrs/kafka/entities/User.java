package com.hck.cqrs.kafka.entities;

public class User {
    private int user_id;
    private String code;

    public User(int user_id, String code) {
        this.user_id = user_id;
        this.code = code;
    }

    public void setUserId(int id) {
        this.user_id = id;
    }

    public int getId() {
        return this.user_id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", code='" + code + '\'' +
                '}';
    }
}
