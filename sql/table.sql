-- 用户表
CREATE TABLE Users
(
    username VARCHAR(50) PRIMARY KEY,
    -- 用户名
    password VARCHAR(50) NOT NULL,
    -- 密码
    role VARCHAR(10) NOT NULL,
    -- 用户类型
    user_id INT NOT NULL,
    -- 编号（病人，医生，护士）
    CHECK (role IN ('病人', '医生', '护士'))
    -- 检查约束
);

-- 病人表
CREATE TABLE Patients
(
    patient_id INT PRIMARY KEY,
    -- 病人编号
    patient_name VARCHAR(50) NOT NULL,
    -- 病人姓名
    gender CHAR(3) NOT NULL,
    -- 性别
    age INT NOT NULL,
    -- 年龄
    notes TEXT,
    -- 备注
    phone VARCHAR(20),
    -- 手机号码
    admission_date DATE,
    -- 入院时间
    bed_id INT,
    -- 病床号
    ward_id INT,
    -- 病房号
    doctor_id INT,
    -- 主治医生编号
    paid_amount DECIMAL(10, 2),
    -- 已缴费用
    CHECK(gender IN ('男', '女'))
    -- 检查约束
);

-- 护士表
CREATE TABLE Nurses
(
    nurse_id INT PRIMARY KEY,
    -- 护士编号
    nurse_name VARCHAR(50) NOT NULL,
    -- 护士姓名
    gender CHAR(3) NOT NULL,
    -- 性别
    phone VARCHAR(20),
    -- 手机号码
    position VARCHAR(50),
    -- 职务
    department_id INT,
    -- 科室编号
    CHECK(gender IN ('男', '女'))
    -- 检查约束
);

-- 医生表
CREATE TABLE Doctors
(
    doctor_id INT PRIMARY KEY,
    -- 医生编号
    doctor_name VARCHAR(50) NOT NULL,
    -- 医生姓名
    gender CHAR(3) NOT NULL,
    -- 性别
    phone VARCHAR(20),
    -- 手机号码
    position VARCHAR(50),
    -- 职务
    department_id INT,
    -- 科室编号
    CHECK(gender IN ('男', '女'))
    -- 检查约束
);

-- 设备表
CREATE TABLE Equipments
(
    equipment_id INT PRIMARY KEY,
    -- 设备编号
    equipment_type VARCHAR(50) NOT NULL,
    -- 设备类型
    bed_id INT,
    -- 病床号
    ward_id INT
    -- 病房号
);

-- 病床表
CREATE TABLE Beds
(
    bed_id INT,
    -- 病床号
    ward_id INT,
    -- 病房号
    in_use BOOLEAN NOT NULL,
    -- 使用状态
    help BOOLEAN NOT NULL,
    -- 清洁状态
    nurse_id INT,
    -- 负责护士编号
    PRIMARY KEY (bed_id, ward_id)
    -- 主键: 病床号，病房号
);

-- 病房表
CREATE TABLE Wards
(
    ward_id INT PRIMARY KEY,
    -- 病房号
    department_id INT,
    -- 科室编号
    cost DECIMAL(10, 2),
    -- 单日费用
    ward_type CHAR(3),
    -- 病房类型
    CHECK(ward_type IN ('男', '女'))
    -- 检查约束
);

-- 科室表
CREATE TABLE Departments
(
    department_id INT PRIMARY KEY,
    -- 科室编号
    department_name VARCHAR(50) NOT NULL,
    -- 科室名称
    notes TEXT,
    -- 备注
    head_id INT,
    -- 科主任
    tel VARCHAR(20)
    -- 电话号码
);
