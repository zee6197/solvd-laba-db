package com.solvd.laba.persistence;

import com.solvd.laba.domain.BuildingCompany;

import java.util.List;

public interface BuildingCompanyRepository {

    void create(BuildingCompany buildingCompany);
    BuildingCompany findById(Long id);
    List<BuildingCompany> findAll();
    void update(BuildingCompany buildingCompany);
    void delete(Long id);

}