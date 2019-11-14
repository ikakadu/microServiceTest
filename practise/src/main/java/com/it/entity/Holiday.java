package com.it.entity;

public class Holiday {
    private int id;
    private String localDate;
    private String region;
    private String state;

    @Override
    public String toString() {
        return "Holiday{" +
                "id=" + id +
                ", localDate='" + localDate + '\'' +
                ", region='" + region + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
