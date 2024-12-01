package team.jnu.wardsystem.ui;

import lombok.Getter;
import lombok.Setter;
import team.jnu.wardsystem.pojo.Nurse;
import team.jnu.wardsystem.pojo.Patient;
import team.jnu.wardsystem.pojo.Equipment;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

@Getter
@Setter
public class NurseUI extends JFrame implements ActionListener {
    private Nurse nurse; // 当前登录的护士
    private JPanel menuPanel;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // 菜单按钮
    private JButton personalInfoButton;
    private JButton patientInfoButton;
    private JButton equipmentInfoButton;

    // 个人信息组件
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField genderField;
    private JTextField positionField;
    private JTextField phoneField;
    private JTextField departmentField;
    private JButton editPasswordButton;
    private JButton editPhoneButton;

    // 病人信息组件
    private JTable patientTable;
    private DefaultTableModel patientTableModel;
    private JButton editPatientButton;

    // 设备信息组件
    private JTable equipmentTable;
    private DefaultTableModel equipmentTableModel;
    private JButton assignEquipmentButton;

    // 构造函数，传入护士信息
    public NurseUI(Nurse nurse) {
        super("尊敬的 " + nurse.getName() + " 护士，欢迎您！");
        this.nurse = nurse;
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // 居中显示

        // 初始化菜单和主面板
        initMenu();
        initMainPanel();
        setButtonColor(personalInfoButton); // 默认选中个人信息模块

        this.setVisible(true);
    }

    // 初始化左边的菜单栏
    private void initMenu() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        personalInfoButton = new JButton("个人信息");
        patientInfoButton = new JButton("所有病人信息");
        equipmentInfoButton = new JButton("所有设备信息");

        personalInfoButton.addActionListener(this);
        patientInfoButton.addActionListener(this);
        equipmentInfoButton.addActionListener(this);

        menuPanel.add(personalInfoButton);
        menuPanel.add(patientInfoButton);
        menuPanel.add(equipmentInfoButton);

        this.add(menuPanel, BorderLayout.WEST);
    }

    // 初始化右边的主面板
    private void initMainPanel() {
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // 初始化各个功能面板
        mainPanel.add(initPersonalInfoPanel(), "personalInfo");
        mainPanel.add(initPatientInfoPanel(), "patientInfo");
        mainPanel.add(initEquipmentInfoPanel(), "equipmentInfo");

        this.add(mainPanel, BorderLayout.CENTER);
    }

    // 初始化个人信息面板
    private JPanel initPersonalInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 标签和文本框
        JLabel usernameLabel = new JLabel("用户名:");
        usernameField = new JTextField(20);
        usernameField.setText(nurse.getUsername());
        usernameField.setEditable(false);

        JLabel passwordLabel = new JLabel("密码:");
        passwordField = new JPasswordField(20);
        passwordField.setText(nurse.getPassword());
        passwordField.setEditable(false);
        editPasswordButton = new JButton("修改");
        editPasswordButton.addActionListener(this);

        JLabel nameLabel = new JLabel("姓名:");
        nameField = new JTextField(20);
        nameField.setText(nurse.getName());
        nameField.setEditable(false);

        JLabel genderLabel = new JLabel("性别:");
        genderField = new JTextField(20);
        genderField.setText(nurse.getGender());
        genderField.setEditable(false);

        JLabel positionLabel = new JLabel("职位:");
        positionField = new JTextField(20);
        positionField.setText(nurse.getPosition());
        positionField.setEditable(false);

        JLabel phoneLabel = new JLabel("手机号:");
        phoneField = new JTextField(20);
        phoneField.setText(nurse.getPhone());
        phoneField.setEditable(false);
        editPhoneButton = new JButton("修改");
        editPhoneButton.addActionListener(this);

        // 添加到面板
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);
        gbc.gridx = 2;
        panel.add(editPasswordButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(genderLabel, gbc);
        gbc.gridx = 1;
        panel.add(genderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(positionLabel, gbc);
        gbc.gridx = 1;
        panel.add(positionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);
        gbc.gridx = 2;
        panel.add(editPhoneButton, gbc);

        return panel;
    }

    // 初始化病人信息面板
    private JPanel initPatientInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 表格模型
        String[] columns = {"姓名", "性别", "年龄", "病床号", "病房", "操作"};
        patientTableModel = new DefaultTableModel(columns, 0);
        patientTable = new JTable(patientTableModel);
        loadPatientData();

        // 设置表格行高
        patientTable.setRowHeight(30);

        // 添加修改病人信息按钮
        editPatientButton = new JButton("修改病人信息");
        editPatientButton.addActionListener(this);

        panel.add(new JScrollPane(patientTable), BorderLayout.CENTER);
        panel.add(editPatientButton, BorderLayout.SOUTH);

        return panel;
    }

    // 初始化设备信息面板
    private JPanel initEquipmentInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 表格模型
        String[] columns = {"设备编号", "设备类型", "使用病床", "设备状态", "操作"};
        equipmentTableModel = new DefaultTableModel(columns, 0);
        equipmentTable = new JTable(equipmentTableModel);
        loadEquipmentData();

        // 设置表格行高
        equipmentTable.setRowHeight(30);

        // 添加分配设备按钮
        assignEquipmentButton = new JButton("分配设备");
        assignEquipmentButton.addActionListener(this);

        panel.add(new JScrollPane(equipmentTable), BorderLayout.CENTER);
        panel.add(assignEquipmentButton, BorderLayout.SOUTH);

        return panel;
    }

    private void loadPatientData() {
        // 示例数据
        patientTableModel.addRow(new Object[]{"张三", "男", 25, 1, 1});
    }

    private void loadEquipmentData() {
        equipmentTableModel.addRow(new Object[]{1, "监护仪", 1, "未使用", "分配"});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == personalInfoButton) {
            cardLayout.show(mainPanel, "personalInfo");
            setButtonColor(personalInfoButton);
        } else if (source == patientInfoButton) {
            cardLayout.show(mainPanel, "patientInfo");
            setButtonColor(patientInfoButton);
        } else if (source == equipmentInfoButton) {
            cardLayout.show(mainPanel, "equipmentInfo");
            setButtonColor(equipmentInfoButton);
        } else if (source == editPasswordButton) {
            // 处理修改密码
            JPasswordField newPasswordField = new JPasswordField(20);
            int option = JOptionPane.showConfirmDialog(this, newPasswordField, "请输入新密码:", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String newPassword = new String(newPasswordField.getPassword());
                if (!newPassword.trim().isEmpty()) {
                    try {
                        nurse.setPassword(newPassword, nurse.getPassword()); // 假设 Nurse 类有 setPassword 方法
                        passwordField.setText(newPassword);
                        JOptionPane.showMessageDialog(this, "密码已更新");
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "密码不能为空");
                }
            }
        } else if (source == editPhoneButton) {
            // 处理修改手机号
            String newPhone = JOptionPane.showInputDialog(this, "请输入新手机号:");
            if (newPhone != null && !newPhone.trim().isEmpty()) {
                try {
                    nurse.setPhone(newPhone);
                    phoneField.setText(newPhone);
                    JOptionPane.showMessageDialog(this, "手机号已更新");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "手机号不能为空");
            }
        } else if (source == editPatientButton) {
            // 处理修改病人信息
            int selectedRow = patientTable.getSelectedRow();
            if (selectedRow >= 0) {
                String name = (String) patientTable.getValueAt(selectedRow, 0);
                String newName = JOptionPane.showInputDialog(this, "修改病人姓名", name);
                if (newName != null && !newName.trim().isEmpty()) {
                    patientTable.setValueAt(newName, selectedRow, 0);
                }
            }
        } else if (source == assignEquipmentButton) {
            // 处理设备分配
            int selectedRow = equipmentTable.getSelectedRow();
            if (selectedRow >= 0) {
                String equipmentId = equipmentTable.getValueAt(selectedRow, 0).toString();
                String[] patients = {"王大夫 - 101", "李小花 - 102", "张三 - 103"};

                String selectedPatient = (String) JOptionPane.showInputDialog(
                        this,
                        "选择一个病人使用设备：" + equipmentId,
                        "分配设备",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        patients,
                        patients[0]
                );

                if (selectedPatient != null) {
                    JOptionPane.showMessageDialog(this,
                            "设备 " + equipmentId + " 已分配给 " + selectedPatient,
                            "分配成功",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    // 设置按钮的高亮
    private void setButtonColor(JButton selectedButton) {
        personalInfoButton.setBackground(null);
        patientInfoButton.setBackground(null);
        equipmentInfoButton.setBackground(null);

        selectedButton.setBackground(Color.LIGHT_GRAY);
    }
}
