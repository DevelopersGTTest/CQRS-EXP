package com.hck.cqrs.kafka.entities;

import java.time.OffsetDateTime;

public class Payment {
    private User user;
    private String action;
    private long amount;
    private OffsetDateTime timeStamp;

    public Payment(User user, String action, long amount, OffsetDateTime timeStamp) {
        this.user = user;
        this.action = action;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public OffsetDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(OffsetDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "user=" + user +
                ", action='" + action + '\'' +
                ", amount=" + amount +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
