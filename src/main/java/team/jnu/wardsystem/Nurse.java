package team.jnu.wardsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nurse {
  private String nurse_name;
  private int nurse_id;
  private String gender;
  private String phone;
  private String position;
  private int department_id;
}
