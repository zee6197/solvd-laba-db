package com.solvd.laba.persistence.mybatis;

import com.solvd.laba.persistence.PersistenceConfig;
import com.solvd.laba.domain.Department;
import com.solvd.laba.persistence.DepartmentRepository;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class DepartmentMyBatisDAO implements DepartmentRepository {

    @Override
    public void create(Department department) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            departmentRepository.create(department);
        }
    }

    @Override
    public Department findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            return departmentRepository.findById(id);
        }
    }

    @Override
    public List<Department> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            return departmentRepository.findAll();
        }
    }

    @Override
    public void update(Department department) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            departmentRepository.update(department);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            departmentRepository.delete(id);
        }
    }
}
