package com.solvd.laba.service.impl;


import com.solvd.laba.domain.Project;
import com.solvd.laba.persistence.ProjectRepository;
import com.solvd.laba.persistence.impl.ProjectDAO;
import com.solvd.laba.service.ProjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private static final Logger LOGGER = LogManager.getLogger(ProjectServiceImpl.class);
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl() {
        this.projectRepository = new ProjectDAO();
    }

    @Override
    public void create(Project project) {
        LOGGER.info("Creating project");
        projectRepository.create(project);
    }

    @Override
    public Project retrieveById(Long id) {
        LOGGER.info("Retrieving project by id");
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> retrieveAll() {
        LOGGER.info("Retrieving all projects");
        return projectRepository.findAll();
    }

    @Override
    public void update(Project project) {
        LOGGER.info("Updating project");
        projectRepository.update(project);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting project with ID: {}", id);
        projectRepository.delete(id);
    }
}
