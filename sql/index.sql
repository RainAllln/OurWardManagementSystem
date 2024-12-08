-- 对 Patients 表按 doctor_id 创建索引并进行聚类
CREATE INDEX Patients_index ON Patients(doctor_id);
CLUSTER Patients USING Patients_index;

-- 对 Equipments 表按 equipment_type 创建索引并进行聚类
CREATE INDEX Equipments_index ON Equipments(equipment_type);
CLUSTER Equipments USING Equipments_index;

-- 对 Wards 表按 department_id 创建索引并进行聚类
CREATE INDEX Wards_index ON Wards(department_id);
CLUSTER Wards USING Wards_index;

-- 对 Beds 表按 ward_id 创建索引并进行聚类
CREATE INDEX Beds_index ON Beds(ward_id);
CLUSTER Beds USING Beds_index;