package com.solvd.laba.persistence;

import com.solvd.laba.domain.Equipment;

import java.util.List;

public interface EquipmentRepository {

    void create(Equipment equipment);
    Equipment findById(Long id);
    List<Equipment> findAll();
    void update(Equipment equipment);
    void delete(Long id);
}