package team.jnu.wardsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nurse extends User{
  private String nurse_name;
  private int nurse_id;
  private String gender;
  private String phone;
  private String position;
  private int department_id;
}
