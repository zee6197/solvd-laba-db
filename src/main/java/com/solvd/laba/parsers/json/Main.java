package com.solvd.laba.parsers.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.parsers.model.BuildingCompany;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        File jsonFile = new File("src/main/resources/parsers/json/buildingCompany.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            BuildingCompany buildingCompany = mapper.readValue(jsonFile, BuildingCompany.class);
            System.out.println(buildingCompany);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}