package team.jnu.wardsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipments {
  private int equipment_id;
  private String equipment_type;
  private int bed_id;
  private int ward_id;
}
