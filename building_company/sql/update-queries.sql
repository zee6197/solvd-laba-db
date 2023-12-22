
-- 10 statements for updating

UPDATE employees
SET salary = 60000.00
WHERE id = 1;


UPDATE clients
SET contact_info = '123-555-6789'
WHERE id = 1;


UPDATE departments
SET name = 'Research and Development'
WHERE id = 2;


UPDATE projects
SET budget = 120000.00, end_date = '2023-12-31'
WHERE id = 1;


UPDATE equipment
SET equipment_type = 'Medium'
WHERE id = 1;


UPDATE invoices
SET amount = 75000.00, issue_date = '2023-08-01'
WHERE id = 1;


UPDATE incidents
SET description = 'Updated description of the incident.'
WHERE id = 1;


UPDATE milestones
SET due_date = '2023-09-30'
WHERE id = 1;


UPDATE materials
SET quantity = 50, unit_price = 150.00
WHERE id = 1;


UPDATE vendors
SET contact_info = 'new-contact-info@example.com', rating = 4
WHERE id = 1;