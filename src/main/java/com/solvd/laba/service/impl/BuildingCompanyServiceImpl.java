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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BuildingCompanyServiceImpl implements BuildingCompanyService {
    private static final Logger LOGGER = LogManager.getLogger(BuildingCompanyServiceImpl.class);
    private final BuildingCompanyRepository buildingCompanyRepository;
    private final DepartmentRepository departmentRepository;
    private final ClientRepository clientRepository;
    private final EquipmentRepository equipmentRepository;

    public BuildingCompanyServiceImpl() {
        this.buildingCompanyRepository = new BuildingCompanyDAO();
        this.departmentRepository = new DepartmentDAO();
        this.clientRepository = new ClientDAO();
        this.equipmentRepository = new EquipmentDAO();
    }


    @Override
    public void create(BuildingCompany buildingCompany) {
        LOGGER.info("Creating building company");
        buildingCompanyRepository.create(buildingCompany);
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
        if (company != null) {
            List<Department> departments = (List<Department>) departmentRepository.findById(id);
            company.setDepartment(departments);
        }
        return company;
    }

    @Override
    public BuildingCompany retrieveWithClients(Long id) {
        LOGGER.info("Retrieving building company with clients by id");
        BuildingCompany company = buildingCompanyRepository.findById(id);
        if (company != null) {
            List<Client> clients = (List<Client>) clientRepository.findById(id);
            company.setClients(clients);
        }
        return company;
    }

    @Override
    public BuildingCompany retrieveWithEquipment(Long id) {
        LOGGER.info("Retrieving building company with equipment by id");
        BuildingCompany company = buildingCompanyRepository.findById(id);
        if (company != null) {
            List<Equipment> equipmentList = (List<Equipment>) equipmentRepository.findById(id);
            company.setEquipment(equipmentList);
        }
        return company;
    }
}
