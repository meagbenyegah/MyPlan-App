package com.example.myplan.pojo;

import java.io.Serializable;
import java.util.Date;

public class Plan implements Serializable {
    private String title;
    private String description;
    private Date taskDate;
    private String time;
    private boolean recurring;

    public Plan() {
    }

    public Plan(String title, String description, Date taskDate, String time, boolean recurring) {
        this.title = title;
        this.description = description;
        this.taskDate = taskDate;
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

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }
}
