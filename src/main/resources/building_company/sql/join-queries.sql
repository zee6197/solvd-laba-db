
-- 5 statements with left, right, inner  joins (use aliases for columns and tables)

SELECT
  e.id AS employee_id,
  e.first_name AS first_name,
  e.last_name AS last_name,
  d.name AS department_name
FROM
  employees e
LEFT JOIN departments d ON e.department_id = d.id;


SELECT
  d.id AS department_id,
  d.name AS department_name,
  e.first_name AS employee_first_name,
  e.last_name AS employee_last_name
FROM
  departments d
RIGHT JOIN employees e ON d.id = e.department_id;


SELECT
  p.name AS project_name,
  p.start_date,
  p.end_date,
  bc.name AS building_company_name,
  c.name AS client_name
FROM
  projects p
INNER JOIN building_company bc ON p.building_company_id = bc.id
INNER JOIN clients c ON c.building_company_id = bc.id;


SELECT
  e.first_name AS employee_first_name,
  e.last_name AS employee_last_name,
  p.name AS project_name,
  p.budget
FROM
  employees e
INNER JOIN employee_projects ep ON e.id = ep.employee_id
INNER JOIN projects p ON ep.project_id = p.id
WHERE
  p.budget > 50000;


SELECT
  bc.name AS company_name,
  COUNT(p.id) AS total_projects
FROM
  building_company bc
LEFT JOIN projects p ON bc.id = p.building_company_id
GROUP BY
  bc.name;
