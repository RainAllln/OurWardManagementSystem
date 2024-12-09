-- Patients 表外键
ALTER TABLE Patients
ADD CONSTRAINT fk_patients_bed
FOREIGN KEY (bed_id, ward_id) REFERENCES Beds(bed_id, ward_id);

ALTER TABLE Patients
ADD CONSTRAINT fk_patients_ward
FOREIGN KEY (ward_id) REFERENCES Wards(ward_id);

ALTER TABLE Patients
ADD CONSTRAINT fk_patients_doctor
FOREIGN KEY (doctor_id) REFERENCES Doctors(doctor_id);

-- Nurses 表外键
ALTER TABLE Nurses
ADD CONSTRAINT fk_nurses_department
FOREIGN KEY (department_id) REFERENCES Departments(department_id);

-- Doctors 表外键
ALTER TABLE Doctors
ADD CONSTRAINT fk_doctors_department
FOREIGN KEY (department_id) REFERENCES Departments(department_id);

-- Departments 表外键
ALTER TABLE Departments
ADD CONSTRAINT fk_departments_head
FOREIGN KEY (head_id) REFERENCES Doctors(doctor_id);

-- Equipments 表外键
ALTER TABLE Equipments
ADD CONSTRAINT fk_equipments_bed
FOREIGN KEY (bed_id, ward_id) REFERENCES Beds(bed_id, ward_id);

ALTER TABLE Equipments
ADD CONSTRAINT fk_equipments_ward
FOREIGN KEY (ward_id) REFERENCES Wards(ward_id);

-- Beds 表外键
ALTER TABLE Beds
ADD CONSTRAINT fk_beds_ward
FOREIGN KEY (ward_id) REFERENCES Wards(ward_id);

ALTER TABLE Beds
ADD CONSTRAINT fk_beds_nurse
FOREIGN KEY (nurse_id) REFERENCES Nurses(nurse_id);

-- Wards 表外键
ALTER TABLE Wards
ADD CONSTRAINT fk_wards_department
FOREIGN KEY (department_id) REFERENCES Departments(department_id);