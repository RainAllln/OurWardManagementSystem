package team.jnu.wardsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
  private int department_id;
  private String department_name;
  private String notes;
  private int head_id;
  private String tel;
}
