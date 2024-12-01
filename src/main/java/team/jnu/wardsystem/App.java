package team.jnu.wardsystem;

import team.jnu.wardsystem.pojo.Doctor;
import team.jnu.wardsystem.pojo.User;
import team.jnu.wardsystem.ui.DoctorUI;
import team.jnu.wardsystem.ui.LoginUI;

public class App {
  public static void main(String[] args) {
    LoginUI loginUI = new LoginUI();
    User user = loginUI.getUser();
    //用户id一共5位数,如果用户id为10000说明是病人，20000为医生，30000为护士
//    if(user.getUser_id()/10000 == 1){
//      //病人
//      new PatientUI((Package)user);
//    }else if(user.getUser_id()/10000 == 2){
//      //医生
//      new DoctorUI((Doctor)user);
//    }else if(user.getUser_id()/10000 == 3){
//      //护士
//      new NurseUI((Nurse)user);
//    }
    Doctor doctor_test=new Doctor("肖爷",101,"男","12345678912","T1-227",2);
    new DoctorUI(doctor_test);
  }
}
