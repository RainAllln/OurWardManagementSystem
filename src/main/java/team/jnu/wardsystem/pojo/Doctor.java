package team.jnu.wardsystem.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import team.jnu.wardsystem.mapper.EquipmentMapper;
import team.jnu.wardsystem.mapper.DoctorMapper;
import team.jnu.wardsystem.mapper.PatientMapper;
import java.util.List;

@Data
@NoArgsConstructor
public class Doctor extends User{
  private String doctor_name;
  private int doctor_id;
  private String gender;
  private String phone;
  private String position;
  private int department_id;
  private List<Patient> patientList;
  private List<Equipment> equipmentList;
  private String department_name;
  public Doctor(String doctor_name, int doctor_id, String gender, String phone, String position, int department_id) {
    //数据库对应数据的构造函数
    this.doctor_name = doctor_name;
    this.doctor_id = doctor_id;
    this.gender = gender;
    this.phone = phone;
    this.position = position;
    this.department_id = department_id;
    searchdepartment_name(department_id);
  }
  public static Doctor searchDoctorById(int doctor_id){
    //根据医生id查询医生信息
    SqlSession sqlSession = sqlSessionFactory.openSession();     //打开链接
    DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class); //获取mapper接口
    Doctor doctor= doctorMapper.searchDoctorById(doctor_id); // 获取病人列表
    sqlSession.close(); //关闭连接
    doctor.setDepartment_name(doctor.searchdepartment_name(doctor.getDepartment_id()));
    return doctor;
  }
  private  String searchdepartment_name(int department_id){
    //根据科室id查询科室名称
    SqlSession sqlSession = sqlSessionFactory.openSession();     //打开链接
    DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class); //获取mapper接口
    String department_name= doctorMapper.searchDepartmentName(department_id); // 获取病人列表
    sqlSession.close(); //关闭连接
    return department_name;
  }
  //只是根据功能创建的函数，函数返回值类型和参数列表可以根据需求自行更改
  public void searchAllPatient(int doctor_id){
    //查询所有病人,并且将信息存入patientList
    SqlSession sqlSession = sqlSessionFactory.openSession();     //打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class); //获取mapper接口
    patientList = patientMapper.selectAllPatients(doctor_id); // 获取病人列表
    sqlSession.close(); //关闭连接
  }

  public void searchAllEquipment(){
    //查询所有设备,并且将信息存入equipmentList
    SqlSession sqlSession = sqlSessionFactory.openSession();     //打开链接
    EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class); //获取mapper接口
    equipmentList= equipmentMapper.searchAllEquipment(); // 获取病人列表
    sqlSession.close(); //关闭连接
  }

  public Patient searchPatientById(int patient_id){
    //根据病人id查询详细的病人信息（包括备注信息）
    SqlSession sqlSession = sqlSessionFactory.openSession();     //打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class);
    Patient patient= patientMapper.searchPatientById(patient_id); // 获取病人列表
    sqlSession.close(); //关闭连接
    return patient;
  }

  public void updatePhone(String phone){
    //更新电话号码
    this.phone=phone;
    SqlSession sqlSession = sqlSessionFactory.openSession();     //打开链接
    DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class); //获取mapper接口
    doctorMapper.updatePhone(doctor_id,phone);
    sqlSession.commit(); //提交
    sqlSession.close(); //关闭连接
  }

  public String updatePatientNote(int bed_id,int ward_id,String notes) {
    //更新病人备注信息
    for (Patient patient : patientList) {
      if (patient.getBed_id() == bed_id && patient.getWard_id() == ward_id) {
        patient.setNotes(notes);
        SqlSession sqlSession = sqlSessionFactory.openSession();     //打开链接
        PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class);
        int issue = patientMapper.updatePatientNote(bed_id, ward_id, notes);
        sqlSession.commit(); //提交
        sqlSession.close(); //关闭连接
        if (issue > 0) {
          return "更新成功";
        }
        else return "更新失败";
      }
    }
    return "未找到对应病人";
  }
  public void updatePatientBed(int patient_id){
    //分配病人床位
  }

  public void searchEmptyBed(){
    //查询本科室的对应病人性别的空床位
  }

  public void updateEquipment(){
    //分配设备
  }

  public String getDoctorName() {
    return doctor_name;
  }

  public int getDepartmentId() {
    return department_id;
  }
}
