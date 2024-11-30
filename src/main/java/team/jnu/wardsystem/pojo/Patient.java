package team.jnu.wardsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
  private String name;
  private int id;
  private String gender;
  private int age;
  private String notes;
  private Date admmision_date;
  private String phone;
  private int bed_id;
  private int ward_id;
  private int nurse_id;
  private int doctor_id;
  private double paid_amount;
}
