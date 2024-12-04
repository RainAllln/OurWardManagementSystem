package team.jnu.wardsystem.ui;

import lombok.Getter;
import team.jnu.wardsystem.pojo.Doctor;
import team.jnu.wardsystem.pojo.Patient;
import team.jnu.wardsystem.pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginUI extends JFrame implements ActionListener {
    private JTextField userText;         // 用户名输入框
    private JPasswordField passwordText; // 密码输入框
    private JButton loginButton;         // 登录按钮
    private JButton registerButton;      // 注册按钮

    @Getter
    private User user;                   // 对应的用户

    // 构造函数
    public LoginUI() {
        super("登录界面");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // 居中显示
        user = new User();
        LoginFrame();
    }

    public void LoginFrame() {
        // 创建自定义背景面板
        BackgroundPanel panel = new BackgroundPanel("../picture/login.jpg"); // 使用类路径资源
        panel.setLayout(new GridBagLayout()); // 使用GridBagLayout以灵活安排控件
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // 设置边距

        // 创建一个带标题的边框面板
        JPanel borderedPanel = new JPanel(new GridBagLayout());
        GridBagConstraints innerGbc = new GridBagConstraints();
        innerGbc.fill = GridBagConstraints.HORIZONTAL;
        innerGbc.insets = new Insets(10, 10, 10, 20); // 设置边距

        // 添加用户名标签
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setForeground(Color.BLACK); // 设置文字颜色以适应背景
        innerGbc.gridx = 0; // 第0列
        innerGbc.gridy = 0; // 第0行
        borderedPanel.add(userLabel, innerGbc);

        // 添加用户名输入框
        userText = new JTextField(20); // 设置输入框宽度
        innerGbc.gridx = 1; // 第1列
        innerGbc.gridy = 0; // 第0行
        borderedPanel.add(userText, innerGbc);

        // 添加密码标签
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setForeground(Color.black); // 设置文字颜色以适应背景
        innerGbc.gridx = 0; // 第0列
        innerGbc.gridy = 1; // 第1行
        borderedPanel.add(passwordLabel, innerGbc);

        // 添加密码输入框
        passwordText = new JPasswordField(20); // 设置输入框宽度
        innerGbc.gridx = 1; // 第1列
        innerGbc.gridy = 1; // 第1行
        borderedPanel.add(passwordText, innerGbc);

        // 创建登录按钮
        loginButton = new JButton("登录");
        loginButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小
        innerGbc.gridx = 0; // 第0列
        innerGbc.gridy = 2; // 第2行
        innerGbc.gridwidth = 2; // 按钮跨越两列
        borderedPanel.add(loginButton, innerGbc);

        // 创建注册按钮
        registerButton = new JButton("注册");
        registerButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小
        innerGbc.gridx = 0; // 第0列
        innerGbc.gridy = 3; // 第3行
        innerGbc.gridwidth = 2; // 按钮跨越两列
        borderedPanel.add(registerButton, innerGbc);

        // 登录按钮的点击事件
        loginButton.addActionListener(this);
        // 注册按钮的点击事件
        registerButton.addActionListener(this);

        // 将带边框的面板添加到背景面板
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(borderedPanel, gbc);

        // 将背景面板添加到窗口
        this.setContentPane(panel);

        // 设置可见
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn == loginButton) {
            // 登录
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            // 判断用户名密码是否匹配
            String LoginMsg = user.Login(username, password); // 如果登录成功，那么user对象中就保存了用户名，密码，id等信息,并且获取登录信息
            if (LoginMsg.equals("登录成功")) {
                JOptionPane.showMessageDialog(this, LoginMsg);
                this.dispose(); // 关闭登录界面
                // 可以在这里打开主界面，例如
                if(user.getUser_id() / 10000 == 1) {
                    // 病人
                } else if(user.getUser_id() / 10000 == 2) {
                    // 医生
                    new DoctorUI(new Doctor(user.getUser_id(), username, password));
                } else if(user.getUser_id() / 10000 == 3) {
                    // 护士
                }

            } else {
                JOptionPane.showMessageDialog(this, LoginMsg);
            }
        } else if (btn == registerButton) {
            // 注册
            JTextField newUserText = new JTextField();
            JPasswordField newPasswordText = new JPasswordField();
            JTextField newNameText = new JTextField();
            JTextField newGenderText = new JTextField();
            JTextField newAgeText = new JTextField();
            JTextField newPhoneText = new JTextField();
            Object[] message = {
                    "用户名:", newUserText,
                    "密码:", newPasswordText,
                    "真实姓名",newNameText,
                    "性别",newGenderText,
                    "年龄",newAgeText,
                    "手机号",newPhoneText
            };
            int option = JOptionPane.showConfirmDialog(this, message, "注册", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String newUsername = newUserText.getText();
                String newPassword = new String(newPasswordText.getPassword());
                String newName = newNameText.getText();
                String newGender = newGenderText.getText();
                int newAge = Integer.parseInt(newAgeText.getText());
                String newPhone = newPhoneText.getText();
                // 创建新用户和病人对象
                Patient patient = new Patient(newUsername, newPassword, newAge, newGender, newName, newPhone);
                User newUser = new User(newUsername,newPassword,"病人",patient.getUser_id());
                // 插入新用户和新病人到数据库中
                patient.insertPatient();
                newUser.register();
                System.out.println(newUser.getUser_id());
                System.out.println(newUser.getPassword());
                System.out.println(newUser.getUsername());
                JOptionPane.showMessageDialog(this, "注册成功");
            }
        }
    }

    // 自定义面板类，用于绘制背景图片
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String fileName) {
            // 使用类路径资源加载图片
            try {
                backgroundImage = new ImageIcon(getClass().getResource(fileName)).getImage();
            } catch (Exception e) {
                System.out.println("背景图片加载失败: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 绘制背景图片
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}