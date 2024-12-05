package team.jnu.wardsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import team.jnu.wardsystem.mapper.PatientMapper;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Data
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

  public Patient(int patient_id,String username, String password){
    //登录时创建对象只需要id和用户名密码
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
}
