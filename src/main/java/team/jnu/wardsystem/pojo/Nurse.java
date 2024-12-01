package team.jnu.wardsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import team.jnu.wardsystem.mapper.NurseMapper;
import team.jnu.wardsystem.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Data
@NoArgsConstructor
public class Nurse extends User{
  // Getter 和 Setter 方法
  private String nurse_name;
  private int nurse_id;
  private String gender;
  private String phone;
  private String position;
  private int department_id;
  private List<Patient> patientList;
  private List<Equipment> equipmentList;

  // 构造函数
  public Nurse(String nurse_name, int nurse_id, String gender, String phone, String position, int department_id) {
    //数据库对应数据的构造函数
    this.nurse_name = nurse_name;
    this.nurse_id = nurse_id;
    this.gender = gender;
    this.phone = phone;
    this.position = position;
    this.department_id = department_id;
  }

  public void setPassword(String password, String oldPassword) {
    if (password != null && !password.trim().isEmpty()) {
      if (!password.equals(oldPassword)) {
        setPassword(password);
      } else {
        throw new IllegalArgumentException("新密码不能与旧密码相同");
      }
    } else {
      throw new IllegalArgumentException("密码不能为空");
    }
  }

//  public void setPhone(String phone) {
//    if (phone != null && !phone.trim().isEmpty()) {
//      this.phone = phone;
//    } else {
//      throw new IllegalArgumentException("手机号不能为空");
//    }
//  }

}

