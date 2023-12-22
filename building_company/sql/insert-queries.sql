
-- 10 statements for insertion (single and batch insertions)

INSERT INTO employees (first_name, last_name, hire_date, salary, credential_id, department_id)
VALUES ('John', 'Doe', '2023-01-01 08:30:00', 55000.00, 1, 2);


INSERT INTO employees (first_name, last_name, hire_date, salary, credential_id, department_id)
VALUES ('Alice', 'Smith', '2023-02-15 09:00:00', 62000.00, 2, 3),
       ('Bob', 'Brown', '2023-03-10 10:00:00', 58000.00, 3, 2),
       ('Carol', 'Johnson', '2023-04-05 08:45:00', 60000.00, 4, 1);


INSERT INTO departments (name, building_company_id)
VALUES ('IT', 1);


INSERT INTO departments (name, building_company_id)
VALUES ('HR', 1),
       ('Sales', 2),
       ('Finance', 1);


INSERT INTO projects (name, start_date, end_date, budget, building_company_id)
VALUES ('Project bridge', '2023-05-01', '2023-10-01', 100000.00, 1);


INSERT INTO projects (name, start_date, end_date, budget, building_company_id)
VALUES ('Project tower', '2023-06-01', '2023-11-01', 150000.00, 2),
       ('Project road', '2023-07-01', '2023-12-01', 200000.00, 1);


INSERT INTO equipment (name, equipment_type, building_company_id)
VALUES ('Excavator', 'Heavy', 1);


INSERT INTO equipment (name, equipment_type, building_company_id)
VALUES ('Crane', 'Heavy', 1),
       ('Jackhammer', 'Light', 2);

INSERT INTO clients (name, contact_info, industry, building_company_id)
VALUES ('ABC Corp', '123-456-7890', 'Construction', 1);


INSERT INTO clients (name, contact_info, industry, building_company_id)
VALUES ('XYZ Inc', '987-654-3210', 'Retail', 1),
       ('Acme LLC', '555-123-4567', 'Wholesale', 2);
