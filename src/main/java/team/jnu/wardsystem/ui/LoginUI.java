package team.jnu.wardsystem.ui;

import team.jnu.wardsystem.pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUI extends JFrame implements ActionListener{
    private JTextField userText;    //用户名输入框
    private JPasswordField passwordText;    //密码输入框
    private JButton loginButton;    //登录按钮
    private User user;   //对应的用户

    // 构造函数
    public LoginUI() {
        super("登录界面");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // 居中显示
        user = new User();
        LoginFrame();
    }

    public void LoginFrame() {


        // 创建面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // 使用GridBagLayout以灵活安排控件
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // 设置边距

        // 添加用户名标签
        JLabel userLabel = new JLabel("用户名:");
        gbc.gridx = 0; // 第0列
        gbc.gridy = 0; // 第0行
        panel.add(userLabel, gbc);

        // 添加用户名输入框
        userText = new JTextField(20); // 设置输入框宽度
        gbc.gridx = 1; // 第1列
        gbc.gridy = 0; // 第0行
        panel.add(userText, gbc);

        // 添加密码标签
        JLabel passwordLabel = new JLabel("密码:");
        gbc.gridx = 0; // 第0列
        gbc.gridy = 1; // 第1行
        panel.add(passwordLabel, gbc);

        // 添加密码输入框
        passwordText = new JPasswordField(20); // 设置输入框宽度
        gbc.gridx = 1; // 第1列
        gbc.gridy = 1; // 第1行
        panel.add(passwordText, gbc);

        // 创建登录按钮
        loginButton = new JButton("登录");
        loginButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小
        gbc.gridx = 0; // 第0列
        gbc.gridy = 2; // 第2行
        gbc.gridwidth = 2; // 按钮跨越两列
        panel.add(loginButton, gbc);

        // 登录按钮的点击事件
        loginButton.addActionListener(this);

        // 将面板添加到窗口
        this.add(panel);

        // 设置可见
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if(btn == loginButton) {
            //登录
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            //判断用户名密码是否匹配
            String LoginMsg = user.Login(username, password); //如果登录成功，那么user对象中就保存了用户名，密码，id等信息,并且获取登录信息
            if(LoginMsg.equals("登录成功")) {
                JOptionPane.showMessageDialog(this, LoginMsg);
                this.dispose();    //关闭登录界面
            } else {
                JOptionPane.showMessageDialog(this, LoginMsg);
            }
        }
    }
}


