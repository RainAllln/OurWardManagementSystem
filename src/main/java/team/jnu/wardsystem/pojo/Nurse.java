package team.jnu.wardsystem.pojo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import team.jnu.wardsystem.mapper.*;
import static team.jnu.wardsystem.pojo.Doctor.searchDoctorById;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Nurse extends User {
  // Getter 和 Setter 方法
  private String nurse_name;
  private int nurse_id;
  private String gender;
  private String phone;
  private String position;
  private int department_id;
  private List<Patient> patientList;
  private List<Equipment> equipmentList;
  private String department_name;
  private List<Bed> bedList;

  private static final String resource; // 静态变量，所有对象公用一个变量
  private static final InputStream inputStream;
  private static final SqlSessionFactory sqlSessionFactory;

  static {
    try {
      resource = "mybatis-config.xml";
      inputStream = Resources.getResourceAsStream(resource);

      String url = "jdbc:postgresql://113.45.207.38:26000/ward";
      String DBusername = "nurse";
      String DBpassword = "Gauss123";

      // Configure the data source with decrypted values
      PooledDataSource dataSource = new PooledDataSource();
      dataSource.setDriver("org.postgresql.Driver");
      dataSource.setUrl(url);
      dataSource.setUsername(DBusername);
      dataSource.setPassword(DBpassword);

      sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      sqlSessionFactory.getConfiguration().setEnvironment(new org.apache.ibatis.mapping.Environment("development",
              sqlSessionFactory.getConfiguration().getEnvironment().getTransactionFactory(), dataSource));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // 构造函数
  public Nurse(String nurse_name, int nurse_id, String gender, String phone, String position, int department_id) {
    // 数据库对应数据的构造函数
    this.nurse_name = nurse_name;
    this.nurse_id = nurse_id;
    this.gender = gender;
    this.phone = phone;
    this.position = position;
    this.department_id = department_id;
    searchdepartment_name(department_id);
  }

  public Nurse(int nurse_id, String username, String password) {
    // 构造函数
    this.nurse_id = nurse_id;
    this.username = username;
    this.password = password;
  }

  public static Nurse searchNurseById(int nurse_id) {
    // 根据护士id查询护士信息
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    NurseMapper nurseMapper = sqlSession.getMapper(NurseMapper.class); // 获取mapper接口
    Nurse nurse = nurseMapper.searchNurseById(nurse_id); // 获取护士列表
    sqlSession.close(); // 关闭连接
    nurse.setDepartment_name(nurse.searchdepartment_name(nurse.getDepartment_id()));
    return nurse;
  }

  public String searchdepartment_name(int department_id) {
    // 根据department_id查询department_name
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    NurseMapper nurseMapper = sqlSession.getMapper(NurseMapper.class); // 获取mapper接口
    String department_name = nurseMapper.searchDepartmentName(department_id); // 获取病人列表
    sqlSession.close(); // 关闭连接
    return department_name;
  }

  public void searchAllPatient(int bed_id,int ward_id) {
    // 查询所有病人,并且将信息存入patientList
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class); // 获取mapper接口
    if(patientMapper.searchPatient(bed_id, ward_id)!=null) {
      System.out.println(bed_id+" "+ward_id);
      patientList.add(patientMapper.searchPatient(bed_id, ward_id)); // 获取病人列表
    }
    sqlSession.close(); // 关闭连接
  }

  public void searchAllEquipment() {
    // 查询所有设备,并且将信息存入equipmentList
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class); // 获取mapper接口
    equipmentList = equipmentMapper.searchAllEquipment(); // 获取病人列表
    sqlSession.close(); // 关闭连接
  }

  public void searchAllBed(int nurse_id) {
    // 查询所有病床,并且将信息存入bedList
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    BedMapper bedMapper = sqlSession.getMapper(BedMapper.class); // 获取mapper接口
    bedList = bedMapper.searchAllBed(nurse_id); // 获取病床列表
    sqlSession.close(); // 关闭连接
  }

  public void updatePhone(String phone) {
    // 更新电话号码
    this.phone = phone;
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    NurseMapper nurseMapper = sqlSession.getMapper(NurseMapper.class); // 获取mapper接口
    nurseMapper.updatePhone(nurse_id, phone);
    sqlSession.commit(); // 提交
    sqlSession.close(); // 关闭连接
  }

  public String updateBedstatus(int bed_id, int ward_id, boolean b) {
    // 更新病床状态
    for (Bed bed : bedList) {
      if (bed.getBed_id() == bed_id && bed.getWard_id() == ward_id) {
        // bed.setClean();
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
        BedMapper bedMapper = sqlSession.getMapper(BedMapper.class); // 获取mapper接口
        int issue = bedMapper.updateBedstatus(bed_id, ward_id, b);
        sqlSession.commit(); // 提交
        sqlSession.close(); // 关闭连接
        if (issue > 0) {
          return "更新成功";
        } else
          return "更新失败";
      }
    }
    return "未找到对应病床";
  }

  public String getAllBedInfo() {
    StringBuilder info = new StringBuilder();
    if (patientList == null){
      searchAllBed(nurse_id);
      List<Bed> beds = bedList;
      for (Bed bed : beds) {
        searchAllPatient(bed.getBed_id(),bed.getWard_id());//用病床号和病房号查找病人
      }
    }
    for (Patient patient : patientList) {
      info.append("病人姓名：").append(patient.getPatient_name()).append("\t").append("床号：").append(patient.getBed_id())
          .append("\t").append("病房号：").append(patient.getWard_id()).append("\n");
    }
    return info.toString();
  }

  public void assignEquipmentToPatient(int equipmentId, int bedId, int wardId) {
    // 分配设备给病人
    for (Equipment equipment : equipmentList) {
      if (equipment.getEquipment_id() == equipmentId) {
        equipment.setBed_id(bedId);
        equipment.setWard_id(wardId);
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
        EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
        equipmentMapper.updateEquipment(equipmentId, bedId, wardId);
        sqlSession.commit(); // 提交
        sqlSession.close(); // 关闭连接
      }
    }
  }

  public String updatePatientNote(int bed_id, int ward_id, String notes) {
    // 更新病人备注信息
    for (Patient patient : patientList) {
      if (patient.getBed_id() == bed_id && patient.getWard_id() == ward_id) {
        patient.setNotes(notes);
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
        PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class);
        int issue = patientMapper.updatePatientNote(bed_id, ward_id, notes);
        sqlSession.commit(); // 提交
        sqlSession.close(); // 关闭连接
        if (issue > 0) {
          return "更新成功";
        } else
          return "更新失败";
      }
    }
    return "未找到对应病人";
  }

  public String getDepartmentDetail(int department_id) {
    // 获取科室详细信息
    SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
    DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class); // 获取mapper接口
    Department department = departmentMapper.getDepartmentDetail(department_id); // 获取科室列表
    sqlSession.close(); // 关闭连接
    // 将所有信息拼接成字符串返回
    String departmentDetail = "科室名称：" + department.getDepartment_name() + "\n" + "科室主任："
        + searchDoctorById(department.getHead_id()).getDoctor_name() + "\n" + "科室电话：" + department.getTel() + "\n"
        + "科室备注：" + department.getNotes();
    return departmentDetail;
  }

  public Boolean findBedHelp(int bed_id, int ward_id) {
    searchAllBed(nurse_id);
    // 查找病床状态
    for (Bed bed : bedList) {
      if (bed.getBed_id() == bed_id && bed.getWard_id() == ward_id) {
        return bed.isHelp();
      }
    }
    return true;// true表示已清洁即已帮助
  }

  public Boolean findBedUse(int bed_id, int ward_id) {
    searchAllBed(nurse_id);
    for (Bed bed : bedList) {
      if (bed.getBed_id() == bed_id && bed.getWard_id() == ward_id) {
        return bed.isIn_use();
      }
    }
    return false;
  }
}
