package team.jnu.wardsystem;

import team.jnu.wardsystem.pojo.EncryptionUtil;
import team.jnu.wardsystem.ui.LoginUI;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class App {
  public static void main(String[] args) {
    LoginUI loginUI = new LoginUI();
  }
}
//设备取消分配
//判断输入的是不是正确的