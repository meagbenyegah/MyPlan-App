package com.example.myplan.pojo;

import java.io.Serializable;
import java.util.Date;

public class Plan implements Serializable {
    private String title;
    private String description;
    private String date;
    private String time;
    private String recurring;

    public Plan() {
    }

    public Plan(String title, String description, String date, String time, String recurring) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.recurring = recurring;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRecurring() {
        return recurring;
    }

    public void setRecurring(String recurring) {
        this.recurring = recurring;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
