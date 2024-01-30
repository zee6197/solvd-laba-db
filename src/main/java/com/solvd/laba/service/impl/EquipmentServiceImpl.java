package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Equipment;
import com.solvd.laba.persistence.EquipmentRepository;
import com.solvd.laba.persistence.impl.EquipmentDAO;
import com.solvd.laba.persistence.mybatis.EquipmentMyBatisDAO;
import com.solvd.laba.service.EquipmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EquipmentServiceImpl implements EquipmentService {
    private static final Logger LOGGER = LogManager.getLogger(EquipmentServiceImpl.class);

    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl() {

        //  JDBC implementation
        this.equipmentRepository = new EquipmentMyBatisDAO();

    }

    @Override
    public void create(Equipment equipment, Long companyID) {
        LOGGER.info("Creating equipment");
        equipmentRepository.create(equipment, companyID);
    }

    @Override
    public Equipment retrieveById(Long id) {
        LOGGER.info("Retrieving equipment by id");
        return equipmentRepository.findById(id);
    }

    @Override
    public List<Equipment> retrieveAll() {
        LOGGER.info("Retrieving all equipment");
        return equipmentRepository.findAll();
    }

    @Override
    public void update(Equipment equipment) {
        LOGGER.info("Updating equipment");
        equipmentRepository.update(equipment);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting equipment with ID: {}", id);
        equipmentRepository.delete(id);
    }
}