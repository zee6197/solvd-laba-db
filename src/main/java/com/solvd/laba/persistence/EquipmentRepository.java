package com.solvd.laba.persistence;

import com.solvd.laba.domain.Equipment;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface EquipmentRepository {

    void create(@Param("equipment") Equipment equipment, @Param("companyID") Long companyID);
    Equipment findById(Long id);
    List<Equipment> findAll();
    void update(Equipment equipment);
    void delete(Long id);

}
