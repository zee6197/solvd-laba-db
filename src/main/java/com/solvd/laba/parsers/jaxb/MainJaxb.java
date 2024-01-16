package com.solvd.laba.parsers.jaxb;

import com.solvd.laba.parsers.model.BuildingCompany;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class MainJaxb {

    public static void main(String[] args) {

        File xmlFile = new File("src/main/resources/parsers/xml/buildingCompany.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(BuildingCompany.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            BuildingCompany buildingCompany = (BuildingCompany) unmarshaller.unmarshal(xmlFile);
            System.out.println(buildingCompany);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}