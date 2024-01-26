package com.solvd.laba.persistence.mybatis;

import com.solvd.laba.domain.BuildingCompany;
import com.solvd.laba.persistence.BuildingCompanyRepository;
import com.solvd.laba.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class BuildingCompanyMyBatisDAO implements BuildingCompanyRepository {

    @Override
    public void create(BuildingCompany buildingCompany) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BuildingCompanyRepository buildingCompanyDAO = sqlSession.getMapper(BuildingCompanyRepository.class);
            buildingCompanyDAO.create(buildingCompany);
        }
    }

    @Override
    public BuildingCompany findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BuildingCompanyRepository buildingCompanyDAO = sqlSession.getMapper(BuildingCompanyRepository.class);
            return buildingCompanyDAO.findById(id);
        }
    }

    @Override
    public List<BuildingCompany> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BuildingCompanyRepository buildingCompanyDAO = sqlSession.getMapper(BuildingCompanyRepository.class);
            return buildingCompanyDAO.findAll();
        }
    }

    @Override
    public void update(BuildingCompany buildingCompany) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BuildingCompanyRepository buildingCompanyDAO = sqlSession.getMapper(BuildingCompanyRepository.class);
            buildingCompanyDAO.update(buildingCompany);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BuildingCompanyRepository buildingCompanyDAO = sqlSession.getMapper(BuildingCompanyRepository.class);
            buildingCompanyDAO.delete(id);
        }
    }
}
