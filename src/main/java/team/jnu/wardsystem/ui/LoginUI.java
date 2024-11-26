package team.jnu.wardsystem.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUI {
    // 构造函数
    public static void LoginFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("登录界面");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // 居中显示

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
        JTextField userText = new JTextField(20); // 设置输入框宽度
        gbc.gridx = 1; // 第1列
        gbc.gridy = 0; // 第0行
        panel.add(userText, gbc);

        // 添加密码标签
        JLabel passwordLabel = new JLabel("密码:");
        gbc.gridx = 0; // 第0列
        gbc.gridy = 1; // 第1行
        panel.add(passwordLabel, gbc);

        // 添加密码输入框
        JPasswordField passwordText = new JPasswordField(20); // 设置输入框宽度
        gbc.gridx = 1; // 第1列
        gbc.gridy = 1; // 第1行
        panel.add(passwordText, gbc);

        // 创建登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小
        gbc.gridx = 0; // 第0列
        gbc.gridy = 2; // 第2行
        gbc.gridwidth = 2; // 按钮跨越两列
        panel.add(loginButton, gbc);

        // 登录按钮的点击事件
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                // 这里可以添加验证逻辑，比如检查用户名和密码是否正确
                if ("admin".equals(username) && "password123".equals(password)) {
                    JOptionPane.showMessageDialog(null, "登录成功！", "信息", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 将面板添加到窗口
        frame.add(panel);

        // 设置可见
        frame.setVisible(true);
    }

}


