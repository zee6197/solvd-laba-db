package com.solvd.laba.service.impl;

import com.solvd.laba.domain.BuildingCompany;
import com.solvd.laba.domain.Client;
import com.solvd.laba.domain.Department;
import com.solvd.laba.domain.Equipment;
import com.solvd.laba.persistence.BuildingCompanyRepository;
import com.solvd.laba.persistence.ClientRepository;
import com.solvd.laba.persistence.DepartmentRepository;
import com.solvd.laba.persistence.EquipmentRepository;
import com.solvd.laba.persistence.impl.BuildingCompanyDAO;
import com.solvd.laba.persistence.impl.ClientDAO;
import com.solvd.laba.persistence.impl.DepartmentDAO;
import com.solvd.laba.persistence.impl.EquipmentDAO;
import com.solvd.laba.service.BuildingCompanyService;
import com.solvd.laba.service.ClientService;
import com.solvd.laba.service.DepartmentService;
import com.solvd.laba.service.EquipmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BuildingCompanyServiceImpl implements BuildingCompanyService {
    private static final Logger LOGGER = LogManager.getLogger(BuildingCompanyServiceImpl.class);
    private final BuildingCompanyRepository buildingCompanyRepository;
    private final DepartmentService departmentService;
    private final ClientService clientService;
    private final EquipmentService equipmentService;

    public BuildingCompanyServiceImpl() {
        this.buildingCompanyRepository = new BuildingCompanyDAO();
        departmentService = new DepartmentServiceImpl();
        clientService = new ClientServiceImpl();
        equipmentService = new EquipmentServiceImpl();
    }


    @Override
    public void create(BuildingCompany buildingCompany) {
        LOGGER.info("Creating building company");
        buildingCompanyRepository.create(buildingCompany);

        if (!buildingCompany.getClients().isEmpty()) {
            buildingCompany.getClients().forEach(client -> {
                clientService.create(client, buildingCompany.getId());
            });
        }

        if (!buildingCompany.getEquipment().isEmpty()) {
            buildingCompany.getEquipment().forEach(equipment -> {
                equipmentService.create(equipment, buildingCompany.getId());
            });
        }

        if (!buildingCompany.getDepartment().isEmpty()) {
            buildingCompany.getDepartment().forEach(department -> {
                departmentService.create(department, buildingCompany.getId());
            });
        }


    }

    @Override
    public BuildingCompany retrieveById(Long id) {
        LOGGER.info("Retrieving building company by id");
        return buildingCompanyRepository.findById(id);
    }

    @Override
    public List<BuildingCompany> retrieveAll() {
        LOGGER.info("Retrieving all building companies");
        return buildingCompanyRepository.findAll();
    }

    @Override
    public void update(BuildingCompany buildingCompany) {
        LOGGER.info("Updating building company");
        buildingCompanyRepository.update(buildingCompany);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting building company with ID: {}", id);
        buildingCompanyRepository.delete(id);
    }

    @Override
    public BuildingCompany retrieveWithDepartments(Long id) {
        LOGGER.info("Retrieving building company with departments by id");
        BuildingCompany company = buildingCompanyRepository.findById(id);
        return company;
    }

    @Override
    public BuildingCompany retrieveWithClients(Long id) {
        LOGGER.info("Retrieving building company with clients by id");
        BuildingCompany company = buildingCompanyRepository.findById(id);
        return company;
    }

    @Override
    public BuildingCompany retrieveWithEquipment(Long id) {
        LOGGER.info("Retrieving building company with equipment by id");
        BuildingCompany company = buildingCompanyRepository.findById(id);
        return company;
    }
}