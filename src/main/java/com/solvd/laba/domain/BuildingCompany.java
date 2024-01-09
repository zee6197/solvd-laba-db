package com.solvd.laba.domain;


import java.util.List;

public class BuildingCompany {
    private Long id;
    private String name;
    private String location;
    private List<Department> department;
    private List<Client> clients;
    private List<Equipment> equipment;
    private List<Project> projects;


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

        return department;
    }

    public void setDepartment(List<Department> department) {

        this.department = department;
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
