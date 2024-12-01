package team.jnu.wardsystem.ui;
import team.jnu.wardsystem.pojo.Doctor;
import team.jnu.wardsystem.pojo.Patient;
import team.jnu.wardsystem.pojo.Equipment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
    //左边一排菜单栏
    //右边主界面
    //左边可以选择的功能有：查看个人信息，查看所属病人信息以及对应的病床信息，查看所有设备信息
    //查看个人信息，右边页面更新为医生的用户名，密码（星号），姓名，性别，职位，手机号，所属科室，其中手机号和密码右边有按钮，点击后可以修改信息
    /*
    查看病人所属信息，右边页面变成一个表格，里面是所属的全部病人的姓名，性别，年龄，病床号和对应病房
    右键单击表格中的病人的信息可以选择查看详情，详情里面有所有信息和备注
    右键单击表格中的病人床位一栏可以更改，可以弹出一个界面，界面里面有其他对应科室对应性别的空床和对应的病房号信息，可以双击选择（或者其他选择方式），更改完自动更新病房
    */
    /*
    查看所有设备信息，右边页面变成一个表格，里面是所有设备的信息，包括设备编号，设备类型，设备被哪个病床所用
    右键单击表格选中设备可以分配设备，会弹出一个选择框，里面有管理的所有病人的姓名和id，选择病人来使用
     */

public class DoctorUI extends JFrame implements ActionListener {
    private Doctor doctor; // 当前登录的医生
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

    // 设备信息组件
    private JTable equipmentTable;
    private DefaultTableModel equipmentTableModel;

    // 构造函数
    public DoctorUI(Doctor doctor) {
        super("医生界面");
        this.doctor = doctor;
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // 居中显示

        // 初始化菜单和主面板
        initMenu();
        initMainPanel();

        this.setVisible(true);
    }

    private void initMenu() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1, 10, 10));

        personalInfoButton = new JButton("查看个人信息");
        patientInfoButton = new JButton("查看所属病人信息");
        equipmentInfoButton = new JButton("查看所有设备信息");

        personalInfoButton.addActionListener(this);
        patientInfoButton.addActionListener(this);
        equipmentInfoButton.addActionListener(this);

        menuPanel.add(personalInfoButton);
        menuPanel.add(patientInfoButton);
        menuPanel.add(equipmentInfoButton);

        this.add(menuPanel, BorderLayout.WEST);
    }

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

    private JPanel initPersonalInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 标签和文本框
        JLabel usernameLabel = new JLabel("用户名:");
        usernameField = new JTextField(20);
        usernameField.setText(doctor.getUsername());
        usernameField.setEditable(false);

        JLabel passwordLabel = new JLabel("密码:");
        passwordField = new JPasswordField(20);
        passwordField.setText("******");
        passwordField.setEditable(false);
        editPasswordButton = new JButton("修改");
        editPasswordButton.addActionListener(this);

        JLabel nameLabel = new JLabel("姓名:");
        nameField = new JTextField(20);
        nameField.setText(doctor.getDoctorName());
        nameField.setEditable(false);

        JLabel genderLabel = new JLabel("性别:");
        genderField = new JTextField(20);
        genderField.setText(doctor.getGender());
        genderField.setEditable(false);

        JLabel positionLabel = new JLabel("职位:");
        positionField = new JTextField(20);
        positionField.setText(doctor.getPosition());
        positionField.setEditable(false);

        JLabel phoneLabel = new JLabel("手机号:");
        phoneField = new JTextField(20);
        phoneField.setText(doctor.getPhone());
        phoneField.setEditable(false);
        editPhoneButton = new JButton("修改");
        editPhoneButton.addActionListener(this);

        JLabel departmentLabel = new JLabel("所属科室:");
        departmentField = new JTextField(20);
        departmentField.setText(String.valueOf(doctor.getDepartmentId()));
        departmentField.setEditable(false);

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

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(departmentLabel, gbc);
        gbc.gridx = 1;
        panel.add(departmentField, gbc);

        return panel;
    }

    private JPanel initPatientInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 表格模型
        String[] columns = {"姓名", "性别", "年龄", "病床号", "病房"};
        patientTableModel = new DefaultTableModel(columns, 0);
        patientTable = new JTable(patientTableModel);
        loadPatientData();

        // 右键菜单
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem viewDetails = new JMenuItem("查看详情");
        JMenuItem changeBed = new JMenuItem("更改床位");
        viewDetails.addActionListener(this);
        changeBed.addActionListener(this);
        popupMenu.add(viewDetails);
        popupMenu.add(changeBed);

        patientTable.setComponentPopupMenu(popupMenu);

        panel.add(new JScrollPane(patientTable), BorderLayout.CENTER);

        return panel;
    }

    private JPanel initEquipmentInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 表格模型
        String[] columns = {"设备编号", "设备类型", "使用病床"};
        equipmentTableModel = new DefaultTableModel(columns, 0);
        equipmentTable = new JTable(equipmentTableModel);
        loadEquipmentData();

        // 右键菜单
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem assignEquipment = new JMenuItem("分配设备");
        assignEquipment.addActionListener(this);
        popupMenu.add(assignEquipment);

        equipmentTable.setComponentPopupMenu(popupMenu);

        panel.add(new JScrollPane(equipmentTable), BorderLayout.CENTER);

        return panel;
    }

    private void loadPatientData() {
        // TODO: 从数据库加载病人数据
    }

    private void loadEquipmentData() {
        // TODO: 从数据库加载设备数据
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == personalInfoButton) {
            cardLayout.show(mainPanel, "personalInfo");
        } else if(source == patientInfoButton) {
            cardLayout.show(mainPanel, "patientInfo");
        } else if(source == equipmentInfoButton) {
            cardLayout.show(mainPanel, "equipmentInfo");
        }
        // TODO: 处理其他按钮和菜单项的事件
    }
}