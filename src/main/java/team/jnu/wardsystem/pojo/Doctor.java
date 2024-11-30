package team.jnu.wardsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User{
  private String doctor_name;
  private int doctor_id;
  private String gender;
  private String phone;
  private String position;
  private int department_id;
}
