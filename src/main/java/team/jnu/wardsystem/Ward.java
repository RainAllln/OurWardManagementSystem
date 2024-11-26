package team.jnu.wardsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ward {
  private int ward_id;
  private int department_id;
  private double cost;
  private String ward_type;
}
