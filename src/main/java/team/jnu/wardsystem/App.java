package team.jnu.wardsystem;

import team.jnu.wardsystem.pojo.Doctor;
import team.jnu.wardsystem.ui.DoctorUI;
import team.jnu.wardsystem.ui.LoginUI;

public class App {
  public static void main(String[] args) {
    //new LoginUI();
    Doctor doctor_test=new Doctor("肖爷",101,"男","12345678912","T1-227",2);
    new DoctorUI(doctor_test);
  }
}
