package team.jnu.wardsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import team.jnu.wardsystem.mapper.BedMapper;
import team.jnu.wardsystem.mapper.DoctorMapper;
import team.jnu.wardsystem.mapper.NurseMapper;
import team.jnu.wardsystem.mapper.PatientMapper;

import javax.print.Doc;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
  private int day_length; //住院时长
  private double total_amount; //总费用
  private double unpaid_amount; //未支付金额
  private Doctor doctor;      //主治医师
  private Nurse nurse;    //管床护士
  private Department department;  //科室
  private Ward ward;  //病房信息
  private Bed bed;    //床位信息

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
    if(patient_name == null) {
      //如果没有患者姓名，说明缓存中并没有患者信息，需要从数据库中获取
      Patient finded_patient = patientMapper.searchPatientById(patient_id);
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
    }else{
        //如果有患者姓名，说明缓存中有患者信息，直接返回
        return true;
    }

  }

  public String getManagingDoctorName() {
    if(doctor == null){
      doctor = getManagingDoctor();
    }
    return (doctor == null) ? "" : doctor.getDoctor_name();
  }

  public Doctor getManagingDoctor() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);    // 获取mapper接口
    Doctor finded_doctor =  doctorMapper.searchDoctorById(doctor_id);
    sqlSession.close(); // 关闭连接
    return finded_doctor;
  }

  public boolean getManagingNurse() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    NurseMapper nurseMapper = sqlSession.getMapper(NurseMapper.class); // 获取mapper接口
    nurse = new Nurse();
    nurse = nurseMapper.searchNurseById(nurse_id);
    sqlSession.close();
    return nurse != null;
  }

  public boolean calculateUnpaidAmount() {
    // 计算未支付金额
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class); // 获取mapper接口
    day_length = (int) ((System.currentTimeMillis() - admission_date.getTime()) / (1000 * 60 * 60 * 24));
    paid_amount = patientMapper.getPaidAmount(patient_id);
    total_amount = day_length * ward.getCost();
    unpaid_amount = total_amount - paid_amount;
    sqlSession.close(); // 关闭连接
    return true;
  }

  public void searchBedInfo() {
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    BedMapper bedMapper = sqlSession.getMapper(BedMapper.class); // 获取mapper接口
    bed = bedMapper.searchBedById(bed_id, ward_id);
  }

  public static Patient searchPatientById(int user_id){
    //用于登录时寻找患者信息
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class); // 获取mapper接口
    Patient patient = patientMapper.searchPatientById(user_id);
    sqlSession.close(); // 关闭连接
    return patient;
  }

  public String getManagingNurseName() {
    if(nurse == null){
      getManagingNurse();
    }
    return (nurse == null) ? "" : nurse.getNurse_name();
  }
  //病人
  public boolean isCheck_out(Date time) {
    return true;
  }
}
