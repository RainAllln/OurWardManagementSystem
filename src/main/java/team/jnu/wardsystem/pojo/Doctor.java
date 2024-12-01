package team.jnu.wardsystem.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
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
  private static String resource;   //静态变量，所有对象共享
  private static InputStream inputStream;
  private static SqlSessionFactory sqlSessionFactory;

  static {  //静态初始化块
    try {
      resource = "mybatis-config.xml";
      inputStream = Resources.getResourceAsStream(resource);
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Doctor(String doctor_name, int doctor_id, String gender, String phone, String position, int department_id) {
    //数据库对应数据的构造函数
    this.doctor_name = doctor_name;
    this.doctor_id = doctor_id;
    this.gender = gender;
    this.phone = phone;
    this.position = position;
    this.department_id = department_id;
  }

  //只是根据功能创建的函数，函数返回值类型和参数列表可以根据需求自行更改
  public void searchAllPatient(){
    //查询所有病人,并且将信息存入patientList
  }

  public void searchAllEquipment(){
    //查询所有设备,并且将信息存入equipmentList
  }

  public Patient searchPatientById(){
    //根据病人id查询详细的病人信息（包括备注信息）
      return new Patient();
  }

  public void updatePhone(){
    //更新电话号码
  }

  public void updatePatientNote(){
    //更新病人备注信息
  }

  public void updatePatientBed(){
    //分配病人床位
  }

  public void searchEmptyBed(){
    //查询本科室的对应病人性别的空床位
  }

  public void updateEquipment(){
    //分配设备
  }

}
