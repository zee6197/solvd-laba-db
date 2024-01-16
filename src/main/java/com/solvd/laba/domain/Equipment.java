package com.solvd.laba.domain;

public class Equipment {

    private Long id;
    private String name;
    private String equipmentType;


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

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                '}';
    }
}
