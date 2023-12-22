
-- 1 big statement to join all tables in the database 
-- (use at least 5 table relaited to each other (exclude many-to-many connection table), 
-- use aliases for this one and choose only necessary fields from tables)

SELECT 
    p.id AS project_id,
    p.name AS project_name,
    p.start_date,
    p.end_date,
    p.budget,
    bc.name AS building_company_name,
    bc.location AS building_company_location,
    c.name AS client_name,
    c.industry AS client_industry,
    i.amount AS invoice_amount,
    i.issue_date AS invoice_issue_date,
    inc.description AS incident_description
FROM 
    projects p
JOIN building_company bc ON p.building_company_id = bc.id
JOIN clients c ON c.building_company_id = bc.id
JOIN invoices i ON p.invoice_id = i.id
LEFT JOIN incidents inc ON p.incident_id = inc.id;
