package com.solvd.laba.service;

import com.solvd.laba.domain.Equipment;
import java.util.List;

public interface EquipmentService {

    void create(Equipment equipment, Long companyID);
    Equipment retrieveById(Long id);
    List<Equipment> retrieveAll();
    void update(Equipment equipment);
    void delete(Long id);
}