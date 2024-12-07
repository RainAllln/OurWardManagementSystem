package team.jnu.wardsystem.pojo;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import team.jnu.wardsystem.mapper.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Patient extends User {
  private String patient_name;
  private int patient_id;
  private String gender;
  private int age;
  private String notes;
  private Date admission_date;
  private String phone;
  private int bed_id;
  private int ward_id;
  private int nurse_id;
  private int doctor_id;
  private double paid_amount;
  private int day_length; // 住院时长
  private double total_amount; // 总费用
  private double unpaid_amount; // 未支付金额
  private Doctor doctor; // 主治医师
  private Nurse nurse; // 管床护士
  private Department department; // 科室
  private Ward ward; // 病房信息
  private Bed bed; // 床位信息

  public Patient(int patient_id, String username, String password) {
    // 登录时创建对象只需要id和用户名密码
    this.patient_id = patient_id;
    this.username = username;
    this.password = password;
  }

  public Patient(String newUsername, String newPassword, int newAge, String newGender, String newName,
      String newPhone) {
    // 注册时只需要那么多信息
    username = newUsername;
    password = newPassword;
    age = newAge;
    gender = newGender;
    patient_name = newName;
    patient_id = getMaxId() + 1;
    setUser_id(patient_id);
    phone = newPhone;
    // 获取系统时间并转化为Java.sql.Date
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date date = new java.util.Date(System.currentTimeMillis());
    admission_date = java.sql.Date.valueOf(sdf.format(date));
  }

  public int getMaxId() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class);
    return patientMapper.getMaxPatientID();
  }

  public void insertPatient() throws RuntimeException {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class); // 获取mapper接口
    patientMapper.insertPatient(this);
    sqlSession.commit();
    sqlSession.close(); // 关闭连接
  }

  public Boolean searchPersonalInfo() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class); // 获取mapper接口
    Patient finded_patient = patientMapper.searchPatientById(patient_id);
    // 病人每次点击个人信息时都会更新信息，因为医生可以让病人出院，所以每次点击都要更新
    if (finded_patient != null) {
      patient_name = finded_patient.getPatient_name();
      gender = finded_patient.getGender();
      age = finded_patient.getAge();
      notes = finded_patient.getNotes();
      admission_date = finded_patient.getAdmission_date();
      bed_id = finded_patient.getBed_id();
      ward_id = finded_patient.getWard_id();
      nurse_id = finded_patient.getNurse_id();
      doctor_id = finded_patient.getDoctor_id();
      phone = finded_patient.getPhone();
      paid_amount = finded_patient.getPaid_amount();
      return true;
    } else {
      return false;
    }

  }

  public String getManagingDoctorName() {
    if (getManagingDoctor()) {
      return doctor.getDoctor_name();
    } else {
      return "仍未分配医生";
    }
  }

  public boolean getManagingDoctor() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class); // 获取mapper接口
    if (doctor_id == 0)
      return false;
    if (doctor == null)
      doctor = doctorMapper.searchDoctorById(doctor_id);
    sqlSession.close(); // 关闭连接
    return doctor != null;
  }

  public boolean getManagingNurse() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    NurseMapper nurseMapper = sqlSession.getMapper(NurseMapper.class); // 获取mapper接口
    if (nurse_id == 0)
      return false;
    if (nurse == null)
      nurse = nurseMapper.searchNurseById(nurse_id);
    sqlSession.close();
    return nurse != null;
  }

  public boolean getDepartmentDetails() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class); // 获取mapper接口
    DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
    if (doctor_id == 0)
      return false; // 未分配医生
    if (department == null)
      department = departmentMapper.getDepartmentDetail(doctor.getDepartment_id());
    if (department.getHead_id() != 0) {
      Doctor Head = doctorMapper.searchDoctorById(department.getHead_id());
      department.setHead_name(Head.getDoctor_name());
    } else {
      department.setHead_name("暂无");
    }
    sqlSession.close(); // 关闭连接
    return department != null;
  }

  public String getDepartmentName() {
    if (getDepartmentDetails()) {
      return department.getDepartment_name();
    } else {
      return "并没有分配科室";
    }
  }

  public boolean getWardInfo() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    WardMapper wardMapper = sqlSession.getMapper(WardMapper.class); // 获取mapper接口
    if (ward == null)
      ward = wardMapper.searchWardById(ward_id);
    sqlSession.close(); // 关闭连接
    return ward != null;
  }

  public boolean calculateUnpaidAmount() {
    // 计算未支付金额
    if (!getWardInfo()) {
      return false; // 说明没有入住病房
    }
    if (unpaid_amount != 0) {
      return true; // 已经计算过了，不需要再链接数据库
    }
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class); // 获取mapper接口
    day_length = (int) ((System.currentTimeMillis() - admission_date.getTime()) / (1000 * 60 * 60 * 24));
    paid_amount = patientMapper.getPaidAmount(patient_id);
    total_amount = day_length * ward.getCost();
    unpaid_amount = total_amount - paid_amount;
    sqlSession.close(); // 关闭连接
    return true;
  }

  public boolean searchBedInfo() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    BedMapper bedMapper = sqlSession.getMapper(BedMapper.class); // 获取mapper接口
    bed = bedMapper.searchBedById(bed_id, ward_id);
    sqlSession.close(); // 关闭连接
    return bed != null;
  }

  public static Patient searchPatientById(int user_id) {
    // 用于登录时寻找患者信息
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class); // 获取mapper接口
    Patient patient = patientMapper.searchPatientById(user_id);
    sqlSession.close(); // 关闭连接
    return patient;
  }

  public String getManagingNurseName() {
    if (getManagingNurse()) {
      return nurse.getNurse_name();
    } else {
      return "数据库连接错误";
    }
  }

  // 病人
  public boolean isCheck_out(Date time) {
    // 确认病人是否缴费足够可出院
    if (calculateUnpaidAmount()) {
      if (unpaid_amount > 0) {
        return false;
      }
    }
    return true;
  }

  public List<Equipment> getEquipmentList() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class); // 获取mapper接口
    List<Equipment> equipmentList = equipmentMapper.searchEquipmentList(bed_id, ward_id); // 获取equipmentList
    bed.setEquipmentList(equipmentList);
    sqlSession.close(); // 关闭连接
    return bed.getEquipmentList();
  }

  public void updatePhone(String newPhone) {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class); // 获取mapper接口
    patientMapper.updatePatientPhone(patient_id, newPhone);
    sqlSession.commit(); // 提交
    sqlSession.close(); // 关闭连接
  }

  public boolean sendNeedHelp() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    BedMapper bedMapper = sqlSession.getMapper(BedMapper.class); // 获取mapper接口
    int updateline = bedMapper.updateBedstatus(bed_id, ward_id, false);
    if (updateline > 0) {
      sqlSession.commit(); // 提交
      sqlSession.close(); // 关闭连接
      return true;
    } else {
      sqlSession.close(); // 关闭连接
      return false;
    }
  }

  public String getWardFee() {
    return String.valueOf(ward.getCost());
  }

  public boolean pay(int fee) {
    // 病人缴费
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class); // 获取mapper接口
    if (fee > unpaid_amount) {
      return false;
    } else {
      paid_amount += fee;
      unpaid_amount -= fee;
      patientMapper.updatePaidAmount(patient_id, paid_amount);
      sqlSession.commit(); // 提交
      sqlSession.close(); // 关闭连接
      return true;
    }
  }
}
