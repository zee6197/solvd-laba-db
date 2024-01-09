package com.solvd.laba.domain;

public class Equipment {

    private Long id;
    private String name;
    private String equipmentType;
    private BuildingCompany buildingCompany;


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

    public String getEquipmentType() {

        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {

        this.equipmentType = equipmentType;
    }

    public BuildingCompany getBuildingCompany() {
        return buildingCompany;
    }

    public void setBuildingCompany(BuildingCompany buildingCompany) {
        this.buildingCompany = buildingCompany;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                '}';
    }
}
