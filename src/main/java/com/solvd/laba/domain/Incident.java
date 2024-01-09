package com.solvd.laba.domain;

import java.util.Date;

public class Incident {

    private Long id;
    private Date date;
    private String description;



    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Date getDate() {

        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
