package com.solvd.laba.service;

import com.solvd.laba.domain.BuildingCompany;
import java.util.List;

public interface BuildingCompanyService {

    void create(BuildingCompany buildingCompany);
    BuildingCompany retrieveById(Long id);
    List<BuildingCompany> retrieveAll();
    void update(BuildingCompany buildingCompany);
    void delete(Long id);

    BuildingCompany retrieveWithDepartments(Long id);
    BuildingCompany retrieveWithClients(Long id);
    BuildingCompany retrieveWithEquipment(Long id);

}
