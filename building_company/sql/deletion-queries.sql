
-- 10 statements for deletions

DELETE FROM employees
WHERE first_name = 'John' AND last_name = 'Doe';


DELETE FROM clients
WHERE name = 'XYZ Inc';


DELETE FROM departments
WHERE name = 'Sales';


DELETE FROM projects
WHERE name = 'Project Bridge';


DELETE FROM equipment
WHERE name = 'Jackhammer';


DELETE FROM invoices
WHERE amount = 75000.00;


DELETE FROM incidents
WHERE description = 'Updated description of the incident.';


DELETE FROM milestones
WHERE due_date = '2023-09-30';


DELETE FROM materials
WHERE quantity = 50;


DELETE FROM equipment
WHERE equipment_type = 'Electrical';


DELETE FROM invoices
WHERE issue_date = '2023-08-01';