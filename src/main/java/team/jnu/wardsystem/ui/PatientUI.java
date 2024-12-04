package team.jnu.wardsystem.ui;

import team.jnu.wardsystem.pojo.Doctor;
import team.jnu.wardsystem.pojo.Nurse;
import team.jnu.wardsystem.pojo.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PatientUI extends JFrame implements ActionListener {
    private Patient patient;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    //菜单按钮
    private JButton personalInfoButton;
    private JButton paymentButton;
    private JButton bedInfoButton;

    //个人信息组件
    private JTextField passwordField;
    private JButton editPasswordButton;
    private JTextField phoneField;
    private JButton editPhoneButton;
    private JButton departmentDetailsButton;
    private JButton doctorDetailsButton;

    //缴费组件
    private JButton payButton;

    //病床信息组件
    private JButton cleanRequestButton;
    private JButton nurseDetailsButton;

    public PatientUI(Patient patient) {
        this.patient = patient;
        setTitle("Patient UI");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add panels to the main panel
        mainPanel.add(createPersonalInfoPanel(), "PersonalInfo");
        mainPanel.add(createPaymentPanel(), "Payment");
        mainPanel.add(createBedInfoPanel(), "BedInfo");

        // Create the menu panel
        JPanel menuPanel = new JPanel(new GridLayout(3, 1));
        personalInfoButton = new JButton("查询个人信息");
        paymentButton = new JButton("缴费");
        bedInfoButton = new JButton("查看病床信息");

        personalInfoButton.addActionListener(e -> cardLayout.show(mainPanel, "PersonalInfo"));
        paymentButton.addActionListener(e -> cardLayout.show(mainPanel, "Payment"));
        bedInfoButton.addActionListener(e -> cardLayout.show(mainPanel, "BedInfo"));

        menuPanel.add(personalInfoButton);
        menuPanel.add(paymentButton);
        menuPanel.add(bedInfoButton);

        // Add the menu panel and main panel to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(menuPanel, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);


        // Create the background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("../picture/login.jpg");
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(menuPanel, BorderLayout.WEST);
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);

        // Set the background panel as the content pane
        setContentPane(backgroundPanel);

        setVisible(true);
    }

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
        passwordField = new JTextField(patient.getPassword(), 15);
        panel.add(passwordField, gbc);
        gbc.gridx = 2;
        editPasswordButton = new JButton("编辑");
        panel.add(editPasswordButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("手机号:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(patient.getPhone(), 15);
        panel.add(phoneField, gbc);
        gbc.gridx = 2;
        editPhoneButton = new JButton("编辑");
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
        //panel.add(new JLabel(patient.getAdmission_date().toString()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("床号:"), gbc);
        gbc.gridx = 1;
        //panel.add(new JLabel(String.valueOf(patient.getBed_id())), gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("病房号:"), gbc);
        gbc.gridx = 1;
        //panel.add(new JLabel(String.valueOf(patient.getWard_id())), gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(new JLabel("科室名:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel("科室名"), gbc); // Replace with actual department name
        gbc.gridx = 2;
        departmentDetailsButton = new JButton("详情");
        panel.add(departmentDetailsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(new JLabel("医生名:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel("医生名"), gbc); // Replace with actual doctor name
        gbc.gridx = 2;
        doctorDetailsButton = new JButton("详情");
        panel.add(doctorDetailsButton, gbc);

        return panel;
    }

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
        panel.add(new JLabel("代缴费用:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel("代缴费用"), gbc); // Replace with actual unpaid amount

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("总费用:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel("总费用"), gbc); // Replace with actual total amount

        gbc.gridx = 0;
        gbc.gridy = 3;
        payButton = new JButton("缴费");
        panel.add(payButton, gbc);

        return panel;
    }

    private JPanel createBedInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        infoPanel.add(new JLabel("病床类型:"), gbc);
        gbc.gridx = 1;
        infoPanel.add(new JLabel("病床类型"), gbc); // Replace with actual bed type

        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(new JLabel("病床是否干净:"), gbc);
        gbc.gridx = 1;
        infoPanel.add(new JLabel("病床是否干净"), gbc); // Replace with actual cleanliness status
        gbc.gridx = 2;
        cleanRequestButton = new JButton("请求清理");
        infoPanel.add(cleanRequestButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        infoPanel.add(new JLabel("管床护士名:"), gbc);
        gbc.gridx = 1;
        infoPanel.add(new JLabel("护士名"), gbc); // Replace with actual nurse name
        gbc.gridx = 2;
        nurseDetailsButton = new JButton("查看详情");
        infoPanel.add(nurseDetailsButton, gbc);

        panel.add(infoPanel, BorderLayout.NORTH);

        // Add table for bed equipment
        String[] columnNames = {"设备名称", "设备状态"};
        Object[][] data = {
                {"设备1", "状态1"},
                {"设备2", "状态2"},
                // Add more equipment data
        };
        JTable equipmentTable = new JTable(data, columnNames);
        panel.add(new JScrollPane(equipmentTable), BorderLayout.CENTER);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button actions if needed
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
