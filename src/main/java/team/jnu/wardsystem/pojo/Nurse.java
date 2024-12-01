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
public class Nurse {
  // Getter 和 Setter 方法
  @Getter
  private String username;
  @Getter
  private String password;
  @Getter
  private String name;
  @Getter
  private String gender;
  @Getter
  private String position;
  @Getter
  private String phone;
  private String department;

  protected static final String resource;   //静态变量，所有对象公用一个变量
  protected static final InputStream inputStream;
  protected static final SqlSessionFactory sqlSessionFactory;

  static{
    try {
      resource = "mybatis-config.xml";
      inputStream = Resources.getResourceAsStream(resource);
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  // 构造函数
  public Nurse(String username, String password, String name, String gender, String position, String phone) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.gender = gender;
    this.position = position;
    this.phone = phone;
  }

  public void setPassword(String password, String oldPassword) {
    if (password != null && !password.trim().isEmpty()) {
      if (!password.equals(oldPassword)) {
        this.password = password;
      } else {
        throw new IllegalArgumentException("新密码不能与旧密码相同");
      }
    } else {
      throw new IllegalArgumentException("密码不能为空");
    }
  }

  public void setPhone(String phone) {
    if (phone != null && !phone.trim().isEmpty()) {
      this.phone = phone;
    } else {
      throw new IllegalArgumentException("手机号不能为空");
    }
  }

}

