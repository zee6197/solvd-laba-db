package com.solvd.laba.persistence;

import com.solvd.laba.domain.Department;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface DepartmentRepository {

    void create(@Param("department") Department department, @Param("companyID") Long companyID);
    Department findById(Long id);
    List<Department> findAll();
    void update(Department department);
    void delete(Long id);

}
