
-- 5 alter table (must be add column, update column, delete column)

ALTER TABLE employees
ADD COLUMN phone_number VARCHAR(15);


ALTER TABLE clients
ADD COLUMN email VARCHAR(45),
ADD COLUMN phone VARCHAR(15);


ALTER TABLE clients
DROP COLUMN contact_info;


ALTER TABLE departments
MODIFY COLUMN name VARCHAR(45);


ALTER TABLE projects
ADD COLUMN status VARCHAR(15) DEFAULT 'Pending';


ALTER TABLE equipment
DROP COLUMN equipment_type;