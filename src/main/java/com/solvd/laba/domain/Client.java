package com.solvd.laba.domain;


public class Client {
    private Long id;
    private String name;
    private String contactInfo;
    private String industry;


    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getContactInfo() {

        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {

        this.contactInfo = contactInfo;
    }

    public String getIndustry() {

        return industry;
    }

    public void setIndustry(String industry) {

        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", industry='" + industry + '\'' +
                '}';
    }
}
