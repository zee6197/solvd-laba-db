package com.solvd.laba.parsers.model;

import com.solvd.laba.domain.Project;
import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "buildingCompany")
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildingCompany {
    @XmlAttribute(name = "id")
    private Long id;
    private String name;
    private String location;

    @XmlElementWrapper(name = "departments")
    @XmlElement(name = "department")
    private List<Department> department;

    @XmlElementWrapper(name = "clients")
    @XmlElement(name = "client")
    private List<Client> clients;

    @XmlElementWrapper(name = "equipment")
    @XmlElement(name = "equipmentItem")
    private List<Equipment> equipment;

    @XmlElementWrapper(name = "projects")
    @XmlElement(name = "project")
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
