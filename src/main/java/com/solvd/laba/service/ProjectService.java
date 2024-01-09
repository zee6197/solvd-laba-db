package com.solvd.laba.service;

import com.solvd.laba.domain.Project;
import java.util.List;

public interface ProjectService {

    void create(Project project);
    Project retrieveById(Long id);
    List<Project> retrieveAll();
    void update(Project project);
    void delete(Long id);
}