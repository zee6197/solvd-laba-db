package com.solvd.laba.domain;


import java.util.ArrayList;
import java.util.List;

public class BuildingCompany {
    private Long id;
    private String name;
    private String location;

    private List<Department> departments = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Equipment> equipment = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();


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

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {

        this.location = location;
    }

    public List<Department> getDepartment() {

        return departments;
    }

    public void setDepartment(List<Department> department) {

        this.departments = department;
    }

    public List<Client> getClients() {

        return clients;
    }

    public void setClients(List<Client> clients) {

        this.clients = clients;
    }

    public List<Equipment> getEquipment() {

        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {

        this.equipment = equipment;
    }

    public List<Project> getProjects() {

        return projects;
    }

    public void setProjects(List<Project> projects) {

        this.projects = projects;
    }

    @Override
    public String toString() {
        return "BuildingCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
