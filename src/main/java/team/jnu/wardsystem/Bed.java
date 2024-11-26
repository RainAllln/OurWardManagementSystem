package team.jnu.wardsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bed {
  private int bed_id;
  private int ward_id;
  private boolean in_use;
  private boolean clean;
  private int nurse_id;

}
