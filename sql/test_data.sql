INSERT INTO Patients (patient_id, patient_name, gender, age, notes, phone, admission_date, bed_id, ward_id, doctor_id, paid_amount) VALUES
(10001, '张三', '男', 45, '无特殊病史', '13800000001', '2024-11-01', 101, 1, 20001, 0),
(10002, '李四', '女', 36, '轻微过敏', '13800000002', '2024-11-02', 102, 2, 20002, 0),
(10003, '王五', '男', 55, '糖尿病', '13800000003', '2024-11-03', 103, 3, 20003, 0),
(10004, '赵六', '女', 29, '无特殊病史', '13800000004', '2024-11-04',104, 4, 20004, 0),
(10005, '孙七', '男', 60, '心脏病', '13800000005', '2024-11-05', 105, 5, 20005, 0),
(10006, '陈爽', '女', 40, '高血压', '13800000006', '2024-11-06', 101, 6, 20006, 0),
(10007, '刘江', '女', 50, '无特殊病史', '13800000007', '2024-11-07', 102, 7, 20007, 0),
(10008, '马军', '女', 65, '老年性痴呆', '13800000008', '2024-11-08', 103, 8, 20008, 0),
(10009, '钱毅', '女', 34, '轻度抑郁', '13800000009', '2024-11-09', 104, 9, 20009, 0),
(10010, '何凡', '男', 28, '无特殊病史', '13800000010', '2024-11-10', 105, 10, 20010, 0);
INSERT INTO Nurses (nurse_id, nurse_name, gender, phone, position, department_id) VALUES
(30001, '张护士', '女', '13800000001', '护士长', 1),
(30002, '李护士', '女', '13800000002', '护士', 1),
(30003, '王护士', '女', '13800000003', '护士长', 2),
(30004, '赵护士', '女', '13800000004', '护士', 2),
(30005, '孙护士', '女', '13800000005', '护士长', 3),
(30006, '陈护士', '女', '13800000006', '护士', 3),
(30007, '刘护士', '女', '13800000007', '护士长', 4),
(30008, '马护士', '女', '13800000008', '护士', 4),
(30009, '钱护士', '女', '13800000009', '护士长', 5),
(30010, '何护士', '女', '13800000010', '护士', 5);
INSERT INTO Doctors (doctor_id, doctor_name, gender, phone, position, department_id) VALUES
(20001, '张医生', '男', '13900000001', '主任医师', 1),
(20002, '李医生', '女', '13900000002', '主治医生', 1),
(20003, '王医生', '男', '13900000003', '主任医师', 2),
(20004, '赵医生', '女', '13900000004', '主治医生', 2),
(20005, '孙医生', '男', '13900000005', '主任医师', 3),
(20006, '陈医生', '女', '13900000006', '主任医师', 3),
(20007, '刘医生', '男', '13900000007', '主任医师', 4),
(20008, '马医生', '女', '13900000008', '主任医师', 4),
(20009, '钱医生', '男', '13900000009', '主任医师', 5),
(20010, '何医生', '女', '13900000010', '主治医生', 5);
INSERT INTO Equipments (equipment_id, equipment_type, bed_id, ward_id) VALUES
(1, '心电图机', 101, 1),
(2, '血压计', 102, 2),
(3, '氧气瓶', 103, 3),
(4, '呼吸机', 104, 4),
(5, '体温计', 105, 5),
(6, '血糖仪', 101, 6),
(7, '监护仪', 102, 7),
(8, 'CT扫描仪', 103, 8),
(9, 'X光机', 104, 9),
(10, '超声波设备', 105, 10);
INSERT INTO Beds (bed_id, ward_id, in_use, help, nurse_id) VALUES
(101, 1, TRUE, TRUE, 30001),
(102, 1, FALSE, TRUE, 30001),
(103, 1, FALSE, TRUE, 30001),
(104, 1, FALSE, TRUE, 30001),
(105, 1, FALSE, TRUE, 30001),
(101, 2, FALSE, TRUE, 30001),
(102, 2, TRUE, TRUE, 30001),
(103, 2, FALSE, TRUE, 30001),
(104, 2, FALSE, TRUE, 30001),
(105, 2, FALSE, TRUE, 30001),
(101, 3, FALSE, TRUE, 30003),
(102, 3, FALSE, TRUE, 30003),
(103, 3, TRUE, TRUE, 30003),
(104, 3, FALSE, TRUE, 30003),
(105, 3, FALSE, TRUE, 30003),
(101, 4, FALSE, TRUE, 30003),
(102, 4, FALSE, TRUE, 30003),
(103, 4, FALSE, TRUE, 30003),
(104, 4, TRUE, TRUE, 30003),
(105, 4, FALSE, TRUE, 30003),
(101, 5, FALSE, TRUE, 30005),
(102, 5, FALSE, TRUE, 30005),
(103, 5, FALSE, TRUE, 30005),
(104, 5, FALSE, TRUE, 30005),
(105, 5, TRUE, TRUE, 30005),
(101, 6, TRUE, TRUE, 30005),
(102, 6, FALSE, TRUE, 30005),
(103, 6, FALSE, TRUE, 30005),
(104, 6, FALSE, TRUE, 30005),
(105, 6, FALSE, TRUE, 30005),
(101, 7, FALSE, TRUE, 30007),
(102, 7, TRUE, TRUE, 30007),
(103, 7, FALSE, TRUE, 30007),
(104, 7, FALSE, TRUE, 30007),
(105, 7, FALSE, TRUE, 30007),
(101, 8, FALSE, TRUE, 30007),
(102, 8, FALSE, TRUE, 30007),
(103, 8, TRUE, TRUE, 30007),
(104, 8, FALSE, TRUE, 30007),
(105, 8, FALSE, TRUE, 30007),
(101, 9, FALSE, TRUE, 30009),
(102, 9, FALSE, TRUE, 30009),
(103, 9, FALSE, TRUE, 30009),
(104, 9, TRUE, TRUE, 30009),
(105, 9, FALSE, TRUE, 30009),
(101, 10, FALSE, TRUE, 30009),
(102, 10, FALSE, TRUE, 30009),
(103, 10, FALSE, TRUE, 30009),
(104, 10, FALSE, TRUE, 30009),
(105, 10, TRUE, TRUE, 30009);
INSERT INTO Wards (ward_id, department_id, cost, ward_type) VALUES
(1, 1, 500.00, '男'),
(2, 1, 500.00, '女'),
(3, 2, 500.00, '男'),
(4, 2, 500.00, '女'),
(5, 3, 500.00, '男'),
(6, 3, 500.00, '女'),
(7, 4, 500.00, '女'),
(8, 4, 500.00, '女'),
(9, 5, 500.00, '男'),
(10, 5, 500.00, '女');
INSERT INTO Departments (department_id, department_name, notes, head_id, tel) VALUES
(1, '内科', '一般内科疾病治疗', 20001, '020-12345678'),
(2, '外科', '包括各种外科手术', 20003, '020-12345679'),
(3, '儿科', '专门治疗儿童疾病', 20005, '020-12345680'),
(4, '妇科', '女性疾病治疗', 20007, '020-12345681'),
(5, '眼科', '眼睛相关疾病治疗', 20009, '020-12345682');
INSERT INTO Users (username, password, role, user_id) VALUES
('zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '病人', 10001),
('lisi', 'e10adc3949ba59abbe56e057f20f883e', '病人', 10002),
('wangwu', 'e10adc3949ba59abbe56e057f20f883e', '病人', 10003),
('zhaoliu', 'e10adc3949ba59abbe56e057f20f883e', '病人', 10004),
('sunqi', 'e10adc3949ba59abbe56e057f20f883e', '病人', 10005),
('chenshuang', 'e10adc3949ba59abbe56e057f20f883e', '病人', 10006),
('liujiang', 'e10adc3949ba59abbe56e057f20f883e', '病人', 10007),
('majun', 'e10adc3949ba59abbe56e057f20f883e', '病人', 10008),
('qianyi', 'e10adc3949ba59abbe56e057f20f883e', '病人', 10009),
('hefan', 'e10adc3949ba59abbe56e057f20f883e', '病人', 10010),
('zhanghs', 'e10adc3949ba59abbe56e057f20f883e', '护士', 30001),
('lihs', 'e10adc3949ba59abbe56e057f20f883e', '护士', 30002),
('wanghs', 'e10adc3949ba59abbe56e057f20f883e', '护士', 30003),
('zhaohs', 'e10adc3949ba59abbe56e057f20f883e', '护士', 30004),
('sunhs', 'e10adc3949ba59abbe56e057f20f883e', '护士', 30005),
('chenhs', 'e10adc3949ba59abbe56e057f20f883e', '护士', 30006),
('liuhs', 'e10adc3949ba59abbe56e057f20f883e', '护士', 30007),
('mahs', 'e10adc3949ba59abbe56e057f20f883e', '护士', 30008),
('qianhs', 'e10adc3949ba59abbe56e057f20f883e', '护士', 30009),
('hehs', 'e10adc3949ba59abbe56e057f20f883e', '护士', 30010),
('zhangys', 'e10adc3949ba59abbe56e057f20f883e', '医生', 20001),
('liys', 'e10adc3949ba59abbe56e057f20f883e', '医生', 20002),
('wangys', 'e10adc3949ba59abbe56e057f20f883e', '医生', 20003),
('zhaoys', 'e10adc3949ba59abbe56e057f20f883e', '医生', 20004),
('sunys', 'e10adc3949ba59abbe56e057f20f883e', '医生', 20005),
('chenys', 'e10adc3949ba59abbe56e057f20f883e', '医生', 20006),
('liuys', 'e10adc3949ba59abbe56e057f20f883e', '医生', 20007),
('mays', 'e10adc3949ba59abbe56e057f20f883e', '医生', 20008),
('qianys', 'e10adc3949ba59abbe56e057f20f883e', '医生', 20009),
('heys', 'e10adc3949ba59abbe56e057f20f883e', '医生', 20010);