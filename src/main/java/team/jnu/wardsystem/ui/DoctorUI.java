package team.jnu.wardsystem.ui;

import team.jnu.wardsystem.pojo.Doctor;
import team.jnu.wardsystem.pojo.Patient;
import team.jnu.wardsystem.pojo.Equipment;
import team.jnu.wardsystem.pojo.Bed;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

// DoctorUI类实现医生界面，包括个人信息、病人信息和设备信息
public class DoctorUI extends JFrame implements ActionListener {
    private boolean patient_add =false;
    private boolean equipment_add=false;
    private boolean unpatient_add=false;
    private Doctor doctor; // 当前登录的医生
    private Bed bed;
    private JPanel menuPanel;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // 菜单按钮
    private JButton personalInfoButton;
    private JButton patientInfoButton;
    private JButton equipmentInfoButton;
    private JButton unassignedPatientButton;

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
    //查看所属科室详情的按钮
    private JButton departmentDetailButton;

    // 病人信息组件
    private JTable patientTable;
    private DefaultTableModel patientTableModel;

    // 设备信息组件
    private JTable equipmentTable;
    private DefaultTableModel equipmentTableModel;

    // 构造函数
    public DoctorUI(Doctor doctor) {
        super("尊敬的 " + doctor.getDoctor_name() + "，欢迎您！");
        this.doctor = doctor;
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
        menuPanel.setLayout(new GridLayout(4, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        personalInfoButton = new JButton("个人信息");
        patientInfoButton = new JButton("所有病人信息");
        equipmentInfoButton = new JButton("所有设备信息");
        unassignedPatientButton = new JButton("待诊病人");

        personalInfoButton.addActionListener(this);
        patientInfoButton.addActionListener(this);
        equipmentInfoButton.addActionListener(this);
        unassignedPatientButton.addActionListener(this);

        menuPanel.add(personalInfoButton);
        menuPanel.add(patientInfoButton);
        menuPanel.add(equipmentInfoButton);
        menuPanel.add(unassignedPatientButton);

        this.add(menuPanel, BorderLayout.WEST);
    }

    // 初始化右边的主面板
    private void initMainPanel() {
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // 初始化个人信息功能面板
        mainPanel.add(initPersonalInfoPanel(), "personalInfo");
        //mainPanel.add(initPatientInfoPanel(), "patientInfo");
        //mainPanel.add(initEquipmentInfoPanel(), "equipmentInfo");

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
        usernameField.setText(doctor.getUsername());
        usernameField.setEditable(false);

        JLabel passwordLabel = new JLabel("密码:");
        passwordField = new JPasswordField(20);
        passwordField.setText(doctor.getPassword());
        passwordField.setEditable(false);
        editPasswordButton = new JButton("修改");
        editPasswordButton.addActionListener(this);

        JLabel nameLabel = new JLabel("姓名:");
        nameField = new JTextField(20);
        nameField.setText(doctor.getDoctor_name());
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
        departmentField.setText(doctor.getDepartment_name());
        departmentField.setEditable(false);
        departmentDetailButton = new JButton("查看详情");
        departmentDetailButton.addActionListener(this );

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
        gbc.gridx = 2;
        panel.add(departmentDetailButton, gbc);


        return panel;
    }

    // 初始化病人信息面板
    private JPanel initPatientInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 表格模型
        String[] columns = {"姓名", "性别", "年龄", "病床号", "病房","电话","备注", "操作"};
        patientTableModel = new DefaultTableModel(columns, 0) {
            // 使表格不可编辑除了"操作"列
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7;
            }
        };
        patientTable = new JTable(patientTableModel);
        loadPatientData();

        // 设置单元格渲染器，使文字居中
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < patientTable.getColumnCount(); i++) {
            patientTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // 设置"操作"列的渲染器和编辑器
        patientTable.getColumn("操作").setCellRenderer(new ButtonRenderer("patient"));
        patientTable.getColumn("操作").setCellEditor(new ButtonEditor(new JCheckBox(), patientTable, patientTableModel, "patient"));

        // 设置表格行高
        patientTable.setRowHeight(30);

        panel.add(new JScrollPane(patientTable), BorderLayout.CENTER);

        return panel;
    }

    // 初始化设备信息面板
    private JPanel initEquipmentInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 表格模型
        String[] columns = {"设备编号", "设备类型", "使用病床","使用病房","操作"};
        equipmentTableModel = new DefaultTableModel(columns, 0) {
            // 使表格不可编辑除了"操作"列
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        equipmentTable = new JTable(equipmentTableModel);
        loadEquipmentData();

        // 设置单元格渲染器，使文字居中
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < equipmentTable.getColumnCount(); i++) {
            equipmentTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // 设置"操作"列的渲染器和编辑器
        equipmentTable.getColumn("操作").setCellRenderer(new ButtonRenderer("equipment"));
        equipmentTable.getColumn("操作").setCellEditor(new ButtonEditor(new JCheckBox(), equipmentTable, equipmentTableModel, "equipment"));

        // 设置表格行高
        equipmentTable.setRowHeight(30);

        panel.add(new JScrollPane(equipmentTable), BorderLayout.CENTER);

        return panel;
    }
    private JPanel initUnassignedPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model
        String[] columns = {"姓名", "性别", "年龄", "电话", "操作"};
        DefaultTableModel unassignedPatientTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Only "操作" column is editable
            }
        };
        JTable unassignedPatientTable = new JTable(unassignedPatientTableModel);
        loadUnassignedPatientData(unassignedPatientTableModel);

        // Set cell renderer and editor for "操作" column
        unassignedPatientTable.getColumn("操作").setCellRenderer(new ButtonRenderer("unassignedPatient"));
        unassignedPatientTable.getColumn("操作").setCellEditor(new ButtonEditor(new JCheckBox(), unassignedPatientTable, unassignedPatientTableModel, "unassignedPatient"));

        // Set row height
        unassignedPatientTable.setRowHeight(30);

        panel.add(new JScrollPane(unassignedPatientTable), BorderLayout.CENTER);

        return panel;
    }
    private void loadUnassignedPatientData(DefaultTableModel model) {
        if(doctor.getUnassignedPatientList()==null){
            doctor.searchUnassignedPatient();
        }
        List<Patient> unassignedPatients = doctor.getUnassignedPatientList();
        if(unassignedPatients==null){
            return;
        }
        for (Patient patient : unassignedPatients) {
            model.addRow(new Object[]{
                    patient.getPatient_name(),
                    patient.getGender(),
                    patient.getAge(),
                    patient.getPhone(),
            });
        }
    }
    private void loadPatientData() {
        // 将病人数据添加到表格
        if(doctor.getPatientList()==null){
            doctor.searchAllPatient(doctor.getDoctor_id());
        }
        List<Patient> patients=doctor.getPatientList();
        for (Patient patient : patients) {
            patientTableModel.addRow(new Object[]{
                    patient.getPatient_name(),
                    patient.getGender(),
                    patient.getAge(),
                    patient.getBed_id(),
                    patient.getWard_id(),
                    patient.getPhone(),
                    patient.getNotes()
            });
        }
    }

    private void loadEquipmentData() {
        // 将设备数据添加到表格
        if(doctor.getEquipmentList()==null){
            doctor.searchAllEquipment();
        }
        List<Equipment> equipments=doctor.getEquipmentList();
        for (Equipment equipment : equipments) {
            equipmentTableModel.addRow(new Object[]{
                    equipment.getEquipment_id(),
                    equipment.getEquipment_type(),
                    equipment.getBed_id(),
                    equipment.getWard_id()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == personalInfoButton) {
            cardLayout.show(mainPanel, "personalInfo");
            setButtonColor(personalInfoButton);
        } else if (source == patientInfoButton) {
            if(!patient_add){//点击才能加载数据
                mainPanel.add(initPatientInfoPanel(), "patientInfo");
                patient_add=true;
            }
            cardLayout.show(mainPanel, "patientInfo");
            setButtonColor(patientInfoButton);
        } else if (source == equipmentInfoButton) {
            if(!equipment_add){//点击才能加载数据
                mainPanel.add(initEquipmentInfoPanel(), "equipmentInfo");
                equipment_add=true;
            }
            cardLayout.show(mainPanel, "equipmentInfo");
            setButtonColor(equipmentInfoButton);
        } else if(source==unassignedPatientButton){
            if(!unpatient_add){//点击才能加载数据
                mainPanel.add(initUnassignedPatientPanel(), "unassignedPatient");
                unpatient_add=true;
            }
            cardLayout.show(mainPanel, "unassignedPatient");
            setButtonColor(unassignedPatientButton);
        }
        else if (source == editPasswordButton) {
            // 处理修改密码
            JPasswordField newPasswordField = new JPasswordField(20);
            int option = JOptionPane.showConfirmDialog(this, newPasswordField, "请输入新密码:", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String newPassword = new String(newPasswordField.getPassword());
                if (!newPassword.trim().isEmpty()) {
                    if(doctor.getPassword().equals(newPassword)){
                        JOptionPane.showMessageDialog(this, "新密码不能与旧密码相同");
                        return;
                    }
                    passwordField.setText(newPassword);
                    doctor.updatePassword(newPassword);
                    JOptionPane.showMessageDialog(this, "密码已更新");
                } else {
                    JOptionPane.showMessageDialog(this, "密码不能为空");
                }
            }
        } else if (source == editPhoneButton) {
            // 处理修改手机号
            String newPhone = JOptionPane.showInputDialog(this, "请输入新手机号:");
            if (newPhone != null && !newPhone.trim().isEmpty()) {
                if(doctor.getPhone().equals(newPhone)){
                    JOptionPane.showMessageDialog(this, "新手机号不能与旧手机号相同");
                    return;
                }
                phoneField.setText(newPhone);
                doctor.updatePhone(newPhone);
                JOptionPane.showMessageDialog(this, "手机号已更新");
            } else {
                JOptionPane.showMessageDialog(this, "手机号不能为空");
            }
        }else if(source==departmentDetailButton){
            //查看所属科室详情
            JOptionPane.showMessageDialog(this,doctor.getDepartmentDetail(doctor.getDepartment_id()));
        }
        // TODO: 处理其他按钮和菜单项的事件
    }

    // 设置菜单按钮的背景颜色，激活当前模块
    private void setButtonColor(JButton activeButton) {
        personalInfoButton.setBackground(null);
        patientInfoButton.setBackground(null);
        equipmentInfoButton.setBackground(null);
        unassignedPatientButton.setBackground(null);
        activeButton.setBackground(Color.orange);
    }

    // 自定义渲染器类，用于在 "操作" 列中渲染两个按钮
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        private JButton editButton = new JButton("编辑");
        private JButton deleteButton = new JButton("删除");
        private JButton assignButton = new JButton("分配");

        public ButtonRenderer(String type) {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            if(type.equals("unassignedPatient")){
                add(assignButton);
            }else {
                add(editButton);
                add(deleteButton);
            }
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // 自定义编辑器类，用于在 "操作" 列中处理按钮点击事件
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel = new JPanel();
        private JButton editButton = new JButton("编辑");
        private JButton deleteButton = new JButton("删除");
        private JButton assignButton = new JButton("分配");
        private JTable table;
        private DefaultTableModel model;
        private String type; // "patient" 或 "equipment" or "unassignedPatient"

        public ButtonEditor(JCheckBox checkBox, JTable table, DefaultTableModel model, String type) {
            this.table = table;
            this.model = model;
            this.type = type;
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            if(type.equals("unassignedPatient")){
                panel.add(assignButton);
            }else {
                panel.add(editButton);
                panel.add(deleteButton);
            }
            // 编辑按钮事件
            editButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    if (type.equals("patient")) {
                        editPatient(row);
                    } else if (type.equals("equipment")) {
                        editEquipment(row);
                    }
                }
                fireEditingStopped();
            });
            // 删除按钮事件
            deleteButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(DoctorUI.this, "确定要删除此条记录吗？", "确认删除", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {

                        if (type.equals("patient")) {
                            //根据病人姓名和病床号删除病人
                            doctor.deletePatient(Integer.parseInt(model.getValueAt(row, 3).toString()), Integer.parseInt(model.getValueAt(row, 4).toString()));
                        } else if (type.equals("equipment")) {
                            //根据设备编号删除设备
                            doctor.deleteEquipment(Integer.parseInt(model.getValueAt(row, 0).toString()));
                        }
                        model.removeRow(row);
                        JOptionPane.showMessageDialog(DoctorUI.this, "记录已删除");
                    }
                }
                fireEditingStopped();
            });
            // 分配按钮事件
            assignButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    assignBedAndWard(row);
                }
                fireEditingStopped();
            });
        }
        // 弹出分配病床和病房的对话框

        private void assignBedAndWard(int row) {

            JTextField bedField = new JTextField();
            JTextField wardField = new JTextField();

            JPanel assignPanel = new JPanel(new GridLayout(3, 2, 10, 10));
            assignPanel.add(new JLabel("病床号:"));
            assignPanel.add(bedField);
            assignPanel.add(new JLabel("病房:"));
            assignPanel.add(wardField);

            JTextArea unassignedPatientsArea = new JTextArea(5, 20);
            unassignedPatientsArea.setText(getUnassignedPatientsInfo());
            unassignedPatientsArea.setEditable(false);
            assignPanel.add(new JLabel("未分配病床:"));
            assignPanel.add(new JScrollPane(unassignedPatientsArea));

            int result = JOptionPane.showConfirmDialog(DoctorUI.this, assignPanel, "分配病床和病房", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                int bedId = Integer.parseInt(bedField.getText());
                int wardId = Integer.parseInt(wardField.getText());
                // 分配病床和病房
                doctor.assignBedAndWardToPatient(bedId, wardId);
                model.removeRow(row);
                patient_add=false;
                JOptionPane.showMessageDialog(DoctorUI.this, "病床和病房已分配");
            }
        }
        private String getUnassignedPatientsInfo() {
            StringBuilder info = new StringBuilder();
            if(doctor.getUnassignedBedList()==null){
                doctor.searchUnassignedBedList();
            }
            List<Bed> unassignedBed = doctor.getUnassignedBedList();
            int num=1;
            for (Bed bed : unassignedBed) {
                info.append("序号: ").append(num++)
                        .append(" 病床号: ").append(bed.getBed_id())
                        .append(" 病房号: ").append(bed.getWard_id())
                        .append("\n");
            }
            return info.toString();
        }
        // 弹出编辑病人信息的对话框
        // 修改 editPatient 方法中的类型转换
        private void editPatient(int row) {
            String bedNumber = model.getValueAt(row, 3).toString();
            String ward = model.getValueAt(row, 4).toString();
            String notes = (String)model.getValueAt(row, 6);
            JTextField bedField = new JTextField(bedNumber);
            JTextField wardField = new JTextField(ward);
            JTextField notesField = new JTextField(notes);

            JPanel editPanel = new JPanel(new GridLayout(3, 2, 10, 10));
            editPanel.add(new JLabel("病床号:"));
            editPanel.add(bedField);
            editPanel.add(new JLabel("病房:"));
            editPanel.add(wardField);
            editPanel.add(new JLabel("备注:"));
            editPanel.add(notesField);

            int result = JOptionPane.showConfirmDialog(DoctorUI.this, editPanel, "编辑病人信息", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if(!bedNumber.equals(bedField.getText())){//修改了信息要更新数据库
                    doctor.updatePatientBed(Integer.parseInt(bedNumber), Integer.parseInt(ward), Integer.parseInt(bedField.getText()));
                    model.setValueAt(bedField.getText(), row, 3);
                }
                if(!ward.equals(wardField.getText())){//修改了信息要更新数据库
                    doctor.updatePatientWard(Integer.parseInt(bedNumber), Integer.parseInt(ward), Integer.parseInt(wardField.getText()));
                    model.setValueAt(wardField.getText(), row, 4);
                }
                if(!notes.equals(notesField.getText())){//修改了信息要更新数据库
                    doctor.updatePatientNote(Integer.parseInt(bedNumber), Integer.parseInt(ward), notesField.getText());
                    model.setValueAt(notesField.getText(), row, 6);
                }
                JOptionPane.showMessageDialog(DoctorUI.this, "病人信息已更新");
            }
        }

        // 修改 editEquipment 方法中的类型转换
        private void editEquipment(int row) {
            int equipment_id = Integer.parseInt(model.getValueAt(row, 0).toString());
            String bedNumber = model.getValueAt(row, 2).toString();
            String ward = model.getValueAt(row, 3).toString();

            JTextField bedField = new JTextField(bedNumber);
            JTextField wardField = new JTextField(ward);

            JPanel editPanel = new JPanel(new GridLayout(2, 2, 10, 10));
            editPanel.add(new JLabel("使用病床:"));
            editPanel.add(bedField);
            editPanel.add(new JLabel("使用病房:"));
            editPanel.add(wardField);

            int result = JOptionPane.showConfirmDialog(DoctorUI.this, editPanel, "编辑设备信息", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                // 更新表格数据
                if(!bedNumber.equals(bedField.getText())||!ward.equals(wardField.getText())){//修改了信息要更新数据库
                    doctor.updateEquipment(equipment_id, Integer.parseInt(bedField.getText()), Integer.parseInt(wardField.getText()));
                    model.setValueAt(bedField.getText(), row, 2);
                    model.setValueAt(wardField.getText(), row, 3);
                    JOptionPane.showMessageDialog(DoctorUI.this, "设备信息已更新");
                }else{
                    JOptionPane.showMessageDialog(DoctorUI.this, "设备信息未修改");
                }
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }
}