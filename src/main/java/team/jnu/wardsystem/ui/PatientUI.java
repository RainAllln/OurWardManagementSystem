package team.jnu.wardsystem.ui;

import team.jnu.wardsystem.pojo.*;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PatientUI extends JFrame implements ActionListener {
    private Patient patient;        //病人

    //画布组件
    private JPanel mainPanel;
    private JPanel menuPanel;
    private CardLayout cardLayout;

    //菜单按钮
    private JButton personalInfoButton;
    private JButton paymentButton;
    private JButton bedInfoButton;

    //个人信息组件
    private JButton editPasswordButton;
    private JButton editPhoneButton;
    private JButton departmentDetailsButton;
    private JButton doctorDetailsButton;

    //缴费组件
    private JButton payButton;
    private JButton totalPaymentButton;

    //病床信息组件
    private JButton cleanRequestButton;
    private JButton nurseDetailsButton;
    private JTable equipmentTable;
    private DefaultTableModel equipmentTableModel;

    public PatientUI(Patient patient) {
        this.patient = patient;
        setTitle("Patient UI");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createMenuPanel();  //菜单栏创建
        createMainPanel();  //主面板创建
        createBackGroundPanel();  //背景面板创建

        setVisible(true);
    }

    private void createBackGroundPanel() {
        // 创建背景板
        BackgroundPanel backgroundPanel = new BackgroundPanel("../picture/login.jpg");
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);
        backgroundPanel.add(menuPanel, BorderLayout.WEST);

        // 设置背景板为内容面板
        setContentPane(backgroundPanel);
    }

    //左边菜单栏创建
    private void createMenuPanel() {
        menuPanel = new JPanel(new GridLayout(3, 1));
        personalInfoButton = new JButton("查询个人信息");
        paymentButton = new JButton("缴费");
        bedInfoButton = new JButton("查看病床信息");

        personalInfoButton.addActionListener(this);
        paymentButton.addActionListener(this);
        bedInfoButton.addActionListener(this);

        menuPanel.add(personalInfoButton);
        menuPanel.add(paymentButton);
        menuPanel.add(bedInfoButton);

        this.add(menuPanel, BorderLayout.WEST);
    }
    //右边主界面创建
    private void createMainPanel() {
        // Create the main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createInitialPanel(), "Initial");

        this.add(mainPanel, BorderLayout.CENTER);
    }

    //一开始的初始图片
    private JPanel createInitialPanel() {
        //加载一张图片和文字“欢迎来带病床管理系统”到初始界面上
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("../picture/login.jpg"));
        label.setText("欢迎来带病床管理系统");
        panel.add(label);
        return panel;
    }

    //个人信息模块
    private JPanel createPersonalInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("用户名:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(patient.getUsername()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("密码:"), gbc);
        gbc.gridx = 1;
        StringBuilder star_password = new StringBuilder();
        for(int i = 0; i < patient.getPassword().length(); i++){
            star_password.append("*");
        }
        panel.add(new JLabel(star_password.toString()), gbc);
        gbc.gridx = 2;
        editPasswordButton = new JButton("编辑");
        editPasswordButton.addActionListener(this);
        panel.add(editPasswordButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("手机号:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(patient.getPhone()), gbc);
        gbc.gridx = 2;
        editPhoneButton = new JButton("编辑");
        editPhoneButton.addActionListener(this);
        panel.add(editPhoneButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("性别:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(patient.getGender()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("年龄:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(String.valueOf(patient.getAge())), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("备注:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(patient.getNotes()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("入院时间:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(patient.getAdmission_date().toString()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("床号:"), gbc);
        gbc.gridx = 1;
        if(patient.getBed_id() != 0) {
            panel.add(new JLabel(String.valueOf(patient.getBed_id())), gbc);
        }else {
            panel.add(new JLabel("未分配床位"), gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("病房号:"), gbc);
        gbc.gridx = 1;
        if (patient.getWard_id() != 0) {
            panel.add(new JLabel(String.valueOf(patient.getWard_id())), gbc);
        } else {
            panel.add(new JLabel("未分配病房"), gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(new JLabel("医生名:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(patient.getManagingDoctorName()), gbc);
        gbc.gridx = 2;
        doctorDetailsButton = new JButton("详情");
        doctorDetailsButton.addActionListener(this);
        panel.add(doctorDetailsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(new JLabel("科室名:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(patient.getDepartmentName()), gbc);
        gbc.gridx = 2;
        departmentDetailsButton = new JButton("详情");
        departmentDetailsButton.addActionListener(this);
        panel.add(departmentDetailsButton, gbc);

        return panel;
    }

    //缴费模块
    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("已缴费用:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(String.valueOf(patient.getPaid_amount())), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("待缴费用:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(String.valueOf(patient.getUnpaid_amount())), gbc); // Replace with actual unpaid amount

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("总费用:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(String.valueOf(patient.getTotal_amount())), gbc);
        gbc.gridx = 2;
        totalPaymentButton = new JButton("详情");
        totalPaymentButton.addActionListener(this);
        panel.add(totalPaymentButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        payButton = new JButton("缴费");
        payButton.addActionListener(this);
        panel.add(payButton, gbc);

        return panel;
    }

    //查询病床模块
    private JPanel createBedInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        infoPanel.add(new JLabel("病床号:"), gbc);
        gbc.gridx = 1;
        infoPanel.add(new JLabel(String.valueOf(patient.getBed_id())), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        infoPanel.add(new JLabel("病房号:"), gbc);
        gbc.gridx = 3;
        infoPanel.add(new JLabel(String.valueOf(patient.getWard_id())), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(new JLabel("管床护士名:"), gbc);
        gbc.gridx = 1;
        infoPanel.add(new JLabel(patient.getManagingNurseName()), gbc); // Replace with actual nurse name
        gbc.gridx = 2;
        nurseDetailsButton = new JButton("查看详情");
        nurseDetailsButton.addActionListener(this);
        infoPanel.add(nurseDetailsButton, gbc);
        gbc.gridx = 3;
        cleanRequestButton = new JButton("请求帮助");
        cleanRequestButton.addActionListener(this);
        infoPanel.add(cleanRequestButton, gbc);

        panel.add(infoPanel, BorderLayout.NORTH);

        panel.add(setEquipmentTable(), BorderLayout.CENTER);

        return panel;
    }

    private void setButtonColor(JButton activeBtn){
        personalInfoButton.setBackground(null);
        paymentButton.setBackground(null);
        bedInfoButton.setBackground(null);
        activeBtn.setBackground(Color.ORANGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //处理按钮事件监听器
        Object btn = e.getSource();
        if(btn == personalInfoButton) {
            patient.searchPersonalInfo();
            mainPanel.add(createPersonalInfoPanel(), "PersonalInfo");
            cardLayout.show(mainPanel, "PersonalInfo");
            setButtonColor(personalInfoButton);
        }else if(btn == paymentButton) {
            if(!patient.calculateUnpaidAmount()){
                JOptionPane.showMessageDialog(this, "您还未办理住院", "提示", JOptionPane.WARNING_MESSAGE);
            }else{
                mainPanel.add(createPaymentPanel(), "Payment");
                cardLayout.show(mainPanel, "Payment");
                setButtonColor(paymentButton);
            }
        }else if(btn == bedInfoButton) {
            if(!patient.searchBedInfo()){
                JOptionPane.showMessageDialog(this, "您还未办理住院", "提示", JOptionPane.WARNING_MESSAGE);
            }else{
                mainPanel.add(createBedInfoPanel(), "BedInfo");
                cardLayout.show(mainPanel, "BedInfo");
                setButtonColor(bedInfoButton);
            }
        }else if(btn == doctorDetailsButton){
            if (patient.getDoctor_id() != 0) {
                Doctor doctor = patient.getDoctor();
                String doctorDetails = "医生姓名: " + doctor.getDoctor_name() + "\n" +
                        "医生编号: " + doctor.getDoctor_id() + "\n" +
                        "性别: " + doctor.getGender() + "\n" +
                        "电话: " + doctor.getPhone();
                JOptionPane.showMessageDialog(this, doctorDetails, "医生详情", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "您还未分配医生", "提示", JOptionPane.WARNING_MESSAGE);
            }
        }else if(btn == departmentDetailsButton){
            if (patient.getDoctor_id() != 0) {
                Department department = patient.getDepartment();
                String departmentDetails = "科室名称: " + department.getDepartment_name() + "\n" +
                        "科室编号: " + department.getDepartment_id() + "\n" +
                        "科室主任: " + department.getHead_name() + "\n" +
                        "电话: " + department.getTel() + "\n" +
                        "备注: " + department.getNotes();
                JOptionPane.showMessageDialog(this, departmentDetails, "科室详情", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "您还未分配医生", "提示", JOptionPane.WARNING_MESSAGE);
            }
        }else if(btn == editPhoneButton){
            String newPhone = JOptionPane.showInputDialog(this, "请输入新的手机号:");
            if(newPhone != null) {
                if(newPhone.equals(patient.getPhone())) {
                    JOptionPane.showMessageDialog(this, "输入的手机号与原先的手机号相同", "错误", JOptionPane.ERROR_MESSAGE);
                } else if(newPhone.length() != 11) {
                    JOptionPane.showMessageDialog(this, "输入的手机号不正确", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(this, "确定要更改手机号吗?", "确认", JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION) {
                        patient.setPhone(newPhone);
                        patient.updatePhone(newPhone);
                        JOptionPane.showMessageDialog(this, "手机号已成功更改", "成功", JOptionPane.INFORMATION_MESSAGE);
                        personalInfoButton.doClick();   //自动更新个人信息
                    }
                }
            }
        }else if(btn == editPasswordButton){
            editPassword();
        }else if(btn == nurseDetailsButton){
            if (patient.getNurse_id() != 0) {
                Nurse nurse = patient.getNurse();
                String NurseDetails = "护士姓名: " + nurse.getNurse_name() + "\n" +
                        "性别: " + nurse.getGender() + "\n" +
                        "电话: " + nurse.getPhone();
                JOptionPane.showMessageDialog(this, NurseDetails, "护士详情", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "您还未分配病床。没有管床护士", "提示", JOptionPane.WARNING_MESSAGE);
            }
        }else if(btn == cleanRequestButton){
            if (patient.sendNeedHelp()) {
                JOptionPane.showMessageDialog(this, "请求已发送", "提示", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "请求发送失败,请再试", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editPassword(){
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel oldPasswordLabel = new JLabel("原密码:");
        JPasswordField oldPasswordField = new JPasswordField();
        JLabel newPasswordLabel = new JLabel("新密码:");
        JPasswordField newPasswordField = new JPasswordField();
        JLabel confirmPasswordLabel = new JLabel("确认新密码:");
        JPasswordField confirmPasswordField = new JPasswordField();

        panel.add(oldPasswordLabel);
        panel.add(oldPasswordField);
        panel.add(newPasswordLabel);
        panel.add(newPasswordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);

        int result = JOptionPane.showConfirmDialog(this, panel, "更改密码", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String oldPassword = new String(oldPasswordField.getPassword());
            String oldPassword_md5 = User.getMD5Str(oldPassword);
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!oldPassword_md5.equals(patient.getPassword())) {
                JOptionPane.showMessageDialog(this, "原密码不正确", "错误", JOptionPane.ERROR_MESSAGE);
            } else if (newPassword.equals(oldPassword)) {
                JOptionPane.showMessageDialog(this, "新密码不能与原密码相同", "错误", JOptionPane.ERROR_MESSAGE);
            } else if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "新密码与确认新密码不一致", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                int confirm = JOptionPane.showConfirmDialog(this, "确定要更改密码吗?", "确认", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    patient.setPassword(newPassword);
                    patient.updatePassword(newPassword);
                    JOptionPane.showMessageDialog(this, "密码已成功更改", "成功", JOptionPane.INFORMATION_MESSAGE);
                    personalInfoButton.doClick();   //自动更新个人信息
                }
            }
        }
    }

    private JScrollPane setEquipmentTable(){
        //设置设备表格
        String[] columnNames = {"设备编号", "设备类型"};
        equipmentTableModel = new DefaultTableModel(columnNames, 0);
        JTable equipmentTable = new JTable(equipmentTableModel);
        List<Equipment> equipmentList = patient.getEquipmentList();
        for(Equipment equipment : equipmentList){
            equipmentTableModel.addRow(new Object[]{
                    equipment.getEquipment_id(),
                    equipment.getEquipment_type()
            });
        }
        return new JScrollPane(equipmentTable);
    }
    //背景图片
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String fileName) {
            try {
                backgroundImage = new ImageIcon(fileName).getImage();
            } catch (Exception e) {
                System.out.println("背景图片加载失败: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
