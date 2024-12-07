package team.jnu.wardsystem.pojo;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class Bed {
  private int bed_id;
  private int ward_id;
  private boolean in_use;
  private boolean clean;
  private int nurse_id;
  private List<Equipment> equipmentList;
}