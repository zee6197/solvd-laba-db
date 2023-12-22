

DROP DATABASE IF EXISTS building_company;

CREATE DATABASE IF NOT EXISTS building_company;

USE building_company;

CREATE TABLE IF NOT EXISTS credentials (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  login VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX login_UNIQUE (login ASC)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS building_company (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  location VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name_UNIQUE (name ASC),
  UNIQUE INDEX location_UNIQUE (location ASC)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS departments (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(15) NOT NULL,
  building_company_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_departments_building_company1_idx (building_company_id ASC),
  CONSTRAINT fk_departments_building_company1
    FOREIGN KEY (building_company_id)
    REFERENCES building_company (id)
    ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS employees (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  hire_date TIMESTAMP NOT NULL,
  salary DOUBLE UNSIGNED NOT NULL,
  credential_id BIGINT UNSIGNED NOT NULL,
  department_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_employees_credentials_idx (credential_id ASC),
  INDEX fk_employees_departments1_idx (department_id ASC),
  CONSTRAINT fk_employees_credentials
    FOREIGN KEY (credential_id)
    REFERENCES credentials (id)
    ON DELETE CASCADE,
  CONSTRAINT fk_employees_departments1
    FOREIGN KEY (department_id)
    REFERENCES departments (id)
    ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS invoices (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  amount DOUBLE NOT NULL,
  issue_date TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS incidents (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  date TIMESTAMP NOT NULL,
  description VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS milestones (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  due_date TIMESTAMP NOT NULL,
  description VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS projects (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  budget DOUBLE NOT NULL,
  invoice_id BIGINT UNSIGNED NOT NULL,  
  incident_id BIGINT UNSIGNED NOT NULL, 
  building_company_id BIGINT UNSIGNED NOT NULL,
  milestone_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_projects_invoices1_idx (invoice_id ASC),
  INDEX fk_projects_incidents1_idx (incident_id ASC),
  INDEX fk_projects_building_company1_idx (building_company_id ASC),
  INDEX fk_projects_milestones1_idx (milestone_id ASC),
  CONSTRAINT fk_projects_invoices1
    FOREIGN KEY (invoice_id)
    REFERENCES invoices (id),
  CONSTRAINT fk_projects_incidents1
    FOREIGN KEY (incident_id)
    REFERENCES incidents (id),
  CONSTRAINT fk_projects_building_company1
    FOREIGN KEY (building_company_id)
    REFERENCES building_company (id),
  CONSTRAINT fk_projects_milestones1
    FOREIGN KEY (milestone_id)
    REFERENCES milestones (id)
) ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS vendors (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  contact_info VARCHAR(45) NOT NULL,
  rating TINYINT NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS materials (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  quantity BIGINT UNSIGNED NOT NULL,
  unit_price DOUBLE NOT NULL,
  vendor_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_materials_vendors1_idx (vendor_id ASC),
  CONSTRAINT fk_materials_vendors1
    FOREIGN KEY (vendor_id)
    REFERENCES vendors (id)
    ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS clients (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  contact_info VARCHAR(45) NOT NULL,
  industry VARCHAR(45) NOT NULL,
  building_company_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_clients_building_company1_idx (building_company_id ASC),
  CONSTRAINT fk_clients_building_company1
    FOREIGN KEY (building_company_id)
    REFERENCES building_company (id)
    ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS employee_projects (
  employee_id BIGINT UNSIGNED NOT NULL,
  project_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (employee_id, project_id),
  INDEX fk_employees_has_projects_projects1_idx (project_id ASC),
  INDEX fk_employees_has_projects_employees1_idx (employee_id ASC),
  CONSTRAINT fk_employees_has_projects_employees1
    FOREIGN KEY (employee_id)
    REFERENCES employees (id)
    ON DELETE CASCADE,
  CONSTRAINT fk_employees_has_projects_projects1
    FOREIGN KEY (project_id)
    REFERENCES projects (id)
    ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS equipment (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  equipment_type VARCHAR(45) NOT NULL,
  building_company_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_equipment_building_company1_idx (building_company_id ASC),
  CONSTRAINT fk_equipment_building_company1
    FOREIGN KEY (building_company_id)
    REFERENCES building_company (id)
    ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS project_materials (
  material_id BIGINT UNSIGNED NOT NULL,
  project_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (material_id, project_id),
  INDEX fk_materials_has_projects_projects1_idx (project_id ASC),
  INDEX fk_materials_has_projects_materials1_idx (material_id ASC),
  CONSTRAINT fk_materials_has_projects_materials1
    FOREIGN KEY (material_id)
    REFERENCES materials (id)
    ON DELETE CASCADE,
  CONSTRAINT fk_materials_has_projects_projects1
    FOREIGN KEY (project_id)
    REFERENCES projects (id)
    ON DELETE CASCADE
) ENGINE = InnoDB;
