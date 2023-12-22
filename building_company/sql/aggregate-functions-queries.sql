
-- 7 statements with aggregate functions + group by + having

SELECT
  d.name AS department_name,
  COUNT(e.id) AS number_of_employees
FROM
  departments d
JOIN employees e ON d.id = e.department_id
GROUP BY
  d.name
HAVING
  COUNT(e.id) > 5;


SELECT
  d.name AS department_name,
  AVG(e.salary) AS average_salary
FROM
  departments d
JOIN employees e ON d.id = e.department_id
GROUP BY
  d.name
HAVING
  AVG(e.salary) > 50000;


SELECT
  bc.name AS company_name,
  SUM(p.budget) AS total_budget
FROM
  building_company bc
JOIN projects p ON bc.id = p.building_company_id
GROUP BY
  bc.name
HAVING
  SUM(p.budget) > 1000000;


SELECT
  c.name AS client_name,
  MAX(i.amount) AS max_invoice_amount
FROM
  clients c
JOIN projects p ON c.building_company_id = p.building_company_id
JOIN invoices i ON p.id = i.invoice_id
GROUP BY
  c.name
HAVING
  MAX(i.amount) > 10000;


SELECT
  COUNT(p.id) AS total_projects_with_incidents
FROM
  projects p
JOIN incidents i ON p.incident_id = i.id
GROUP BY
  p.building_company_id
HAVING
  COUNT(i.id) >= 1;


SELECT
  p.name AS project_name,
  SUM(m.quantity) AS total_quantity
FROM
  projects p
JOIN project_uses_materials pum ON p.id = pum.project_id
JOIN materials m ON pum.material_id = m.id
GROUP BY
  p.name
HAVING
  SUM(m.quantity) > 100;


SELECT
  AVG(v.rating) AS average_vendor_rating,
  COUNT(m.id) AS materials_supplied
FROM
  vendors v
JOIN materials m ON v.id = m.vendor_id
GROUP BY
  v.id
HAVING
  COUNT(m.id) > 10;


-- 7 statements with aggregate functions + group by without having

SELECT
  d.name AS department_name,
  COUNT(e.id) AS number_of_employees
FROM
  departments d
LEFT JOIN employees e ON d.id = e.department_id
GROUP BY
  d.name;


SELECT
  d.name AS department_name,
  AVG(e.salary) AS average_salary
FROM
  departments d
LEFT JOIN employees e ON d.id = e.department_id
GROUP BY
  d.name;


SELECT
  bc.name AS company_name,
  SUM(p.budget) AS total_budget
FROM
  building_company bc
LEFT JOIN projects p ON bc.id = p.building_company_id
GROUP BY
  bc.name;


SELECT
  c.name AS client_name,
  MAX(i.amount) AS max_invoice_amount
FROM
  clients c
JOIN projects p ON c.building_company_id = p.building_company_id
JOIN invoices i ON p.invoice_id = i.id
GROUP BY
  c.name;


SELECT
  bc.name AS company_name,
  COUNT(p.id) AS total_projects_with_incidents
FROM
  building_company bc
LEFT JOIN projects p ON bc.id = p.building_company_id
LEFT JOIN incidents i ON p.incident_id = i.id
GROUP BY
  bc.name;


SELECT
  p.name AS project_name,
  SUM(m.quantity) AS total_quantity
FROM
  projects p
LEFT JOIN project_uses_materials pum ON p.id = pum.project_id
LEFT JOIN materials m ON pum.material_id = m.id
GROUP BY
  p.name;


SELECT
  v.name AS vendor_name,
  AVG(v.rating) AS average_rating
FROM
  vendors v
LEFT JOIN materials m ON v.id = m.vendor_id
GROUP BY
  v.name;
