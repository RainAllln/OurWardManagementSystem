-- 需要在postgres数据库中执行
CREATE ROLE patient PASSWORD 'Gauss123' LOGIN;
CREATE ROLE doctor PASSWORD 'Gauss123' LOGIN;
CREATE ROLE nurse PASSWORD 'Gauss123' LOGIN;

-- 设置角色权限
GRANT ALL PRIVILEGES ON TABLE Users TO patient, doctor, nurse;
GRANT ALL PRIVILEGES ON TABLE Patients TO patient;
GRANT SELECT ON TABLE Doctors TO patient;
GRANT SELECT ON TABLE Nurses TO patient;
GRANT SELECT ON TABLE Equipments TO patient;
GRANT SELECT, UPDATE(help) ON TABLE Beds TO patient;
GRANT SELECT ON TABLE Wards TO patient;
GRANT SELECT ON TABLE Departments TO patient;
GRANT patient TO doctor, nurse;
GRANT ALL PRIVILEGES ON TABLE Doctors TO doctor;
GRANT ALL PRIVILEGES ON TABLE Nurses TO nurse;
GRANT ALL PRIVILEGES ON TABLE Patients TO doctor, nurse;
GRANT ALL PRIVILEGES ON TABLE Equipments TO doctor, nurse;
GRANT ALL PRIVILEGES ON TABLE Beds TO doctor, nurse;