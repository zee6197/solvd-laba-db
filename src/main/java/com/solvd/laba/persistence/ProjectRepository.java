package com.solvd.laba.persistence;

import com.solvd.laba.domain.Project;

import java.util.List;

public interface ProjectRepository {

    void create(Project project);
    Project findById(Long id);
    List<Project> findAll();
    void update(Project project);
    void delete(Long id);
}