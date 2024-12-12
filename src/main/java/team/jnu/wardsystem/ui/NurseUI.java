package team.jnu.wardsystem.ui;

import lombok.Getter;
import lombok.Setter;
import team.jnu.wardsystem.pojo.Bed;
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

    private boolean patient_add = false;
    private boolean equipment_add = false;
    private boolean bed_add = false;

    // 菜单按钮
    private JButton personalInfoButton;
    private JButton patientInfoButton;
    private JButton equipmentInfoButton;
    private JButton bedInfoButton;
    private JButton logoutButton; // 退出登录按钮

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
    private JButton departmentDetailButton; // 科室详情按钮

    // 病人信息组件
    private JTable patientTable;
    private DefaultTableModel patientTableModel;
    private JButton editPatientButton;

    // 设备信息组件
    private JTable equipmentTable;
    private DefaultTableModel equipmentTableModel;
    private JButton assignEquipmentButton;

    // 病床信息组件
    private JTable bedTable;
    private DefaultTableModel bedTableModel;
    private JButton assignBedButton;

    // 构造函数，传入护士信息
    public NurseUI(Nurse nurse) {
        super("尊敬的 " + nurse.getNurse_name() + " 护士，欢迎您！");

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
        menuPanel.setLayout(new GridLayout(4, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        personalInfoButton = new JButton("个人信息");
        patientInfoButton = new JButton("病人信息");
        equipmentInfoButton = new JButton("设备信息");
        bedInfoButton = new JButton("床位信息");

        personalInfoButton.addActionListener(this);
        patientInfoButton.addActionListener(this);
        equipmentInfoButton.addActionListener(this);
        bedInfoButton.addActionListener(this);

        menuPanel.add(personalInfoButton);
        menuPanel.add(patientInfoButton);
        menuPanel.add(equipmentInfoButton);
        menuPanel.add(bedInfoButton);

        this.add(menuPanel, BorderLayout.WEST);
    }

    // 初始化右边的主面板
    private void initMainPanel() {
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // 初始化各个功能面板
        mainPanel.add(initPersonalInfoPanel(), "personalInfo");
        // mainPanel.add(initPatientInfoPanel(), "patientInfo");
        // mainPanel.add(initEquipmentInfoPanel(), "equipmentInfo");
        // mainPanel.add(initBedInfoPanel(), "bedInfo");

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel initBedInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 表格模型
        String[] columns = { "病床号", "病房号", "帮助状态", "操作" };
        bedTableModel = new DefaultTableModel(columns, 0) {
            // 使表格不可编辑除了"操作"列
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };
        bedTable = new JTable(bedTableModel);
        loadBedData();

        // 设置单元格渲染器，使文字居中
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < bedTable.getColumnCount(); i++) {
            bedTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // 设置"操作"列的渲染器和编辑器
        bedTable.getColumn("操作").setCellRenderer(new ButtonRenderer("bed"));
        bedTable.getColumn("操作").setCellEditor(new ButtonEditor(new JCheckBox(), bedTable, bedTableModel, "bed"));

        // 设置表格行高
        bedTable.setRowHeight(30);

        panel.add(new JScrollPane(bedTable), BorderLayout.CENTER);

        return panel;
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
        nameField.setText(nurse.getNurse_name());
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

        JLabel departmentLabel = new JLabel("所属科室:");
        departmentField = new JTextField(20);
        departmentField.setText(nurse.getDepartment_name());
        departmentField.setEditable(false);
        departmentDetailButton = new JButton("查看详情");
        departmentDetailButton.addActionListener(this);

        // Add the logout button
        logoutButton = new JButton("退出登录");
        logoutButton.setBackground(Color.red);
        logoutButton.setForeground(Color.white);
        logoutButton.addActionListener(this);

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

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(logoutButton, gbc);

        return panel;
    }

    // 初始化病人信息面板
    private JPanel initPatientInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 表格模型
        String[] columns = { "姓名", "性别", "年龄", "病床号", "病房", "电话", "备注", "操作" };
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
        patientTable.getColumn("操作")
                .setCellEditor(new ButtonEditor(new JCheckBox(), patientTable, patientTableModel, "patient"));

        // 设置表格行高
        patientTable.setRowHeight(30);

        panel.add(new JScrollPane(patientTable), BorderLayout.CENTER);

        return panel;
    }

    // 初始化设备信息面板
    private JPanel initEquipmentInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 表格模型
        String[] columns = { "设备编号", "设备类型", "使用病床", "使用病房", "操作" };
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
        equipmentTable.getColumn("操作")
                .setCellEditor(new ButtonEditor(new JCheckBox(), equipmentTable, equipmentTableModel, "equipment"));

        // 设置表格行高
        equipmentTable.setRowHeight(30);

        panel.add(new JScrollPane(equipmentTable), BorderLayout.CENTER);

        return panel;
    }

    private void loadPatientData() {
        if (nurse.getPatientList() == null) {
            nurse.searchAllBed(nurse.getNurse_id());
            List<Bed> beds = nurse.getBedList();
            for (Bed bed : beds) {
                // System.out.println(bed.getBed_id()+" "+bed.getWard_id());
                nurse.searchAllPatient(bed.getBed_id(), bed.getWard_id());// 用病床号和病房号查找病人
            }
        }
        List<Patient> patients = nurse.getPatientList();
        for (Patient patient : patients) {
            patientTableModel.addRow(new Object[] { patient.getPatient_name(), patient.getGender(), patient.getAge(),
                    patient.getBed_id(), patient.getWard_id(), patient.getPhone(), patient.getNotes() });
        }
    }

    private void loadEquipmentData() {
        // 将设备数据添加到表格
        if (nurse.getEquipmentList() == null) {
            nurse.searchAllEquipment();
        }
        List<Equipment> equipments = nurse.getEquipmentList();
        for (Equipment equipment : equipments) {
            equipmentTableModel.addRow(new Object[] { equipment.getEquipment_id(), equipment.getEquipment_type(),
                    equipment.getBed_id(), equipment.getWard_id() });
        }
    }

    private void loadBedData() {
        if (nurse.getBedList() == null) {
            nurse.searchAllBed(nurse.getNurse_id());
        }
        List<Bed> beds = nurse.getBedList();
        for (Bed bed : beds) {
            if (bed.isIn_use()) {
                bedTableModel.addRow(new Object[] { bed.getBed_id(), bed.getWard_id(), bed.isHelp() ? "已帮助" : "未帮助" });
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == personalInfoButton) {
            cardLayout.show(mainPanel, "personalInfo");
            setButtonColor(personalInfoButton);
        } else if (source == patientInfoButton) {
            if (!patient_add) {// 点击才能加载数据
                mainPanel.add(initPatientInfoPanel(), "patientInfo");
                patient_add = true;
            }
            cardLayout.show(mainPanel, "patientInfo");
            setButtonColor(patientInfoButton);
        } else if (source == equipmentInfoButton) {
            if (!equipment_add) {// 点击才能加载数据
                mainPanel.add(initEquipmentInfoPanel(), "equipmentInfo");
                equipment_add = true;
            }
            cardLayout.show(mainPanel, "equipmentInfo");
            setButtonColor(equipmentInfoButton);
        } else if (source == bedInfoButton) {
            if (!bed_add) {// 点击才能加载数据
                mainPanel.add(initBedInfoPanel(), "bedInfo");
                bed_add = true;
            }
            cardLayout.show(mainPanel, "bedInfo");
            setButtonColor(bedInfoButton);
        } else if (source == editPasswordButton) {
            // 处理修改密码
            JPasswordField newPasswordField = new JPasswordField(20);
            int option = JOptionPane.showConfirmDialog(this, newPasswordField, "请输入新密码:", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String newPassword = new String(newPasswordField.getPassword());
                if (!newPassword.trim().isEmpty()) {
                    if (nurse.getPassword().equals(newPassword)) {
                        JOptionPane.showMessageDialog(this, "新密码不能与旧密码相同");
                        return;
                    }
                    passwordField.setText(newPassword);
                    nurse.updatePassword(newPassword);
                    JOptionPane.showMessageDialog(this, "密码已更新");
                } else {
                    JOptionPane.showMessageDialog(this, "密码不能为空");
                }
            }
        } else if (source == editPhoneButton) {
            // 处理修改手机号
            String newPhone = JOptionPane.showInputDialog(this, "请输入新手机号:");
            if (newPhone != null && !newPhone.trim().isEmpty()) {
                if (nurse.getPhone().equals(newPhone)) {
                    JOptionPane.showMessageDialog(this, "新手机号不能与旧手机号相同");
                    return;
                }
                phoneField.setText(newPhone);
                nurse.updatePhone(newPhone);
                JOptionPane.showMessageDialog(this, "手机号已更新");
            } else {
                JOptionPane.showMessageDialog(this, "手机号不能为空");
            }
        } else if (source == departmentDetailButton) {
            JOptionPane.showMessageDialog(this, nurse.getDepartmentDetail(nurse.getDepartment_id()));
        } else if (source == logoutButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "确定要退出登录吗？", "确认退出", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                new LoginUI();
            }
        }
    }

    // 设置按钮的高亮
    private void setButtonColor(JButton selectedButton) {
        personalInfoButton.setBackground(null);
        patientInfoButton.setBackground(null);
        equipmentInfoButton.setBackground(null);
        bedInfoButton.setBackground(null);

        selectedButton.setBackground(Color.LIGHT_GRAY);
    }

    class ButtonRenderer extends JPanel implements TableCellRenderer {
        private JButton editButton = new JButton("编辑");
        // private JButton deleteButton = new JButton("删除");
        private JButton assignButton = new JButton("分配");
        // private JButton cleanButton = new JButton("清理");
        private JButton helpButton = new JButton("帮助");
        private String type;

        public ButtonRenderer(String type) {
            this.type = type;
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            if (type.equals("bed")) {
                add(helpButton);
            } else if (type.equals("equipment")) {
                add(assignButton);
            } else if (type.equals("patient")) {
                add(editButton);
            }
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            // 重置按钮状态
            helpButton.setBackground(UIManager.getColor("Button.background"));
            helpButton.setForeground(Color.BLACK);
            helpButton.setToolTipText(null); // 清除之前的工具提示

            // 确保类型是 "bed"
            if ("bed".equals(type)) {
                // 获取 bed_id 和 ward_id
                if (table.getValueAt(row, 0) != null && table.getValueAt(row, 1) != null) {
                    String bedId = table.getValueAt(row, 0).toString();
                    String wardId = table.getValueAt(row, 1).toString();
                    boolean helpStatus = nurse.findBedHelp(Integer.parseInt(bedId), Integer.parseInt(wardId));
                    boolean useStatus = nurse.findBedUse(Integer.parseInt(bedId), Integer.parseInt(wardId));

                    // 设置按钮外观和工具提示
                    if (!helpStatus && useStatus) { // 床被使用且未清理
                        helpButton.setBackground(Color.RED);
                        helpButton.setForeground(Color.WHITE);
                    }
                }
            }
            // 设置选中状态背景
            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getBackground());
            }

            return this; // 返回渲染的组件
        }

    }

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel = new JPanel();
        private JButton editButton = new JButton("编辑");
        private JButton helpButton = new JButton("帮助");
        // private JButton deleteButton = new JButton("删除");
        private JButton assignButton = new JButton("分配");
        // private JButton cleanButton = new JButton("清理");
        // private JTable table;
        private DefaultTableModel model;
        // private String type; // "patient" 或 "equipment" or "unassignedPatient"

        public ButtonEditor(JCheckBox checkBox, JTable table, DefaultTableModel model, String type) {
            // this.table = table;
            this.model = model;
            // this.type = type;
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            if (type.equals("bed")) {
                panel.add(helpButton);
            } else if (type.equals("equipment")) {
                panel.add(assignButton);
            } else if (type.equals("patient")) {
                panel.add(editButton);
            }
            // 编辑按钮事件
            editButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    if (type.equals("patient")) {
                        editPatient(row);
                    }
                }
                fireEditingStopped();
            });
            helpButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    if (type.equals("bed")) {
                        helpPatient(row);
                    }
                }
                fireEditingStopped();
            });
            // 分配按钮事件
            assignButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    if (type.equals("equipment")) {
                        assignEquipment(row);
                    }
                }
                fireEditingStopped();
            });
        }

        private void helpPatient(int row) {
            String bed_id = model.getValueAt(row, 0).toString();
            String ward_id = model.getValueAt(row, 1).toString();
            boolean helpStatus = nurse.findBedHelp(Integer.parseInt(bed_id), Integer.parseInt(ward_id));
            boolean useStatus = nurse.findBedUse(Integer.parseInt(bed_id), Integer.parseInt(ward_id));
            int confirm = JOptionPane.showConfirmDialog(NurseUI.this, "确定要帮助该病人吗？", "帮助确认", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (!helpStatus && useStatus) {
                    model.setValueAt("已帮助", row, 2);
                    JOptionPane.showMessageDialog(NurseUI.this, "帮助成功", " ", JOptionPane.INFORMATION_MESSAGE);
                    bed_add = false;
                    nurse.updateBedstatus(Integer.parseInt(bed_id), Integer.parseInt(ward_id), true);
                } else if (helpStatus) {
                    JOptionPane.showMessageDialog(NurseUI.this, "病人无需帮助", " ", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        private void assignEquipment(int row) {
            JTextField equipmentField = new JTextField();
            JTextField bedField = new JTextField();
            JTextField wardField = new JTextField();

            JPanel assignPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            assignPanel.add(new JLabel("设备编号:"));
            equipmentField.setEditable(false);
            equipmentField.setText(model.getValueAt(row, 0).toString());
            assignPanel.add(equipmentField);
            assignPanel.add(new JLabel("病床号:"));
            assignPanel.add(bedField);
            bedField.setEditable(false);
            assignPanel.add(new JLabel("病房:"));
            assignPanel.add(wardField);
            wardField.setEditable(false);

            JComboBox<String> unassignedEquipmentComboBox = new JComboBox<>();
            String[] bedInfoArray = nurse.getAllBedInfo().split("\n");
            for (String bedInfo : bedInfoArray) {
                unassignedEquipmentComboBox.addItem(bedInfo);
            }
            // 默认设置为第一个病人的病床号和病房号
            if (bedInfoArray.length > 0) {
                String defaultInfo = bedInfoArray[0]; // 获取第一个病人信息
                String[] parts = defaultInfo.split("，");
                int defaultBedId = Integer.parseInt(parts[1].split("：")[1]);
                int defaultWardId = Integer.parseInt(parts[2].split("：")[1]);
                bedField.setText(String.valueOf(defaultBedId));
                wardField.setText(String.valueOf(defaultWardId));
            }
            assignPanel.add(new JLabel("病房信息:"));
            assignPanel.add(unassignedEquipmentComboBox);
            // 通过selectedInfo获取bedId和wardId
            unassignedEquipmentComboBox.addActionListener(e -> {
                String selectedInfo = (String) unassignedEquipmentComboBox.getSelectedItem();
                if (selectedInfo != null) {
                    String[] parts = selectedInfo.split("，");
                    int bedId = Integer.parseInt(parts[1].split("：")[1]);
                    int wardId = Integer.parseInt(parts[2].split("：")[1]);
                    bedField.setText(String.valueOf(bedId));
                    wardField.setText(String.valueOf(wardId));
                }
            });
            int result = JOptionPane.showConfirmDialog(NurseUI.this, assignPanel, "分配设备", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                int equipmentId = Integer.parseInt(equipmentField.getText());
                int bedId = Integer.parseInt(bedField.getText());
                int wardId = Integer.parseInt(wardField.getText());
                // 分配设备
                nurse.assignEquipmentToPatient(equipmentId, bedId, wardId);
                equipment_add = false;
                // 修改表格中的数据
                model.setValueAt(bedId, row, 2);
                model.setValueAt(wardId, row, 3);
                JOptionPane.showMessageDialog(NurseUI.this, "设备已分配");
            }
        }

        private void editPatient(int row) {
            String bednum = model.getValueAt(row, 3).toString();
            String wardnum = model.getValueAt(row, 4).toString();
            String notes = (String) model.getValueAt(row, 6);
            JTextField notesField = new JTextField(notes);

            JPanel editPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 行 2 列布局
            editPanel.add(new JLabel("备注:"));
            editPanel.add(notesField);

            int result = JOptionPane.showConfirmDialog(NurseUI.this, editPanel, "编辑备注", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (!notes.equals(notesField.getText())) { // 判断备注是否改变
                    nurse.updatePatientNote(Integer.parseInt(bednum), Integer.parseInt(wardnum), notesField.getText());
                    model.setValueAt(notesField.getText(), row, 6); // 更新表格模型
                    JOptionPane.showMessageDialog(NurseUI.this, "备注已更新");
                } else {
                    JOptionPane.showMessageDialog(NurseUI.this, "备注相同，未改变");
                }
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }
}