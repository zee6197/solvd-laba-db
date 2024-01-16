package com.solvd.laba.persistence.mybatis;

import com.solvd.laba.domain.Equipment;
import com.solvd.laba.persistence.EquipmentRepository;
import com.solvd.laba.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EquipmentMyBatisDAO implements EquipmentRepository {

    @Override
    public void create(Equipment equipment, Long companyID) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            EquipmentRepository mapper = session.getMapper(EquipmentRepository.class);
            mapper.create(equipment, companyID);
        }
    }

    @Override
    public Equipment findById(Long id) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            EquipmentRepository mapper = session.getMapper(EquipmentRepository.class);
            return mapper.findById(id);
        }
    }

    @Override
    public List<Equipment> findAll() {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            EquipmentRepository mapper = session.getMapper(EquipmentRepository.class);
            return mapper.findAll();
        }
    }

    @Override
    public void update(Equipment equipment) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            EquipmentRepository mapper = session.getMapper(EquipmentRepository.class);
            mapper.update(equipment);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = PersistenceConfig.getSessionFactory().openSession(true)) {
            EquipmentRepository mapper = session.getMapper(EquipmentRepository.class);
            mapper.delete(id);
        }
    }
}
