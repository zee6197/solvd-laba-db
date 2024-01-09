package com.solvd.laba.domain;

import java.util.Date;

public class Milestone {

    private Long id;
    private Date dueDate;
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "id=" + id +
                ", dueDate=" + dueDate +
                ", description='" + description + '\'' +
                '}';
    }
}
