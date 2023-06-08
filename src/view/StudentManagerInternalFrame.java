package view;

import Util.DbUtil;
import Util.StringUtil;
import dao.SecondaryDao;
import dao.StudentClassDao;
import dao.StudentDao;
import model.Secondary;
import model.Student;
import model.StudentClass;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class StudentManagerInternalFrame extends JInternalFrame {
    private JTable studenttable;
    private DbUtil dbUtil = new DbUtil();
    private StudentDao studentDao = new StudentDao();
    private JTextField s_nameTxt;
    private JTextField s_studentIdTxt;
    private JComboBox s_secondaryJCB;
    private JComboBox s_classJCB;
    private SecondaryDao secondaryDao = new SecondaryDao();
    private StudentClassDao studentClassDao = new StudentClassDao();
    private JTextField nameTxt;
    private JTextField studentIdTxt;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField dormitory_numTxt;
    private JTextField phone_numTxt;
    private JTextField addressTxt;
    private JComboBox secondaryJCB;
    private JComboBox studentClassJCB;
    private JComboBox yearJCB;
    private JComboBox monthJCB;
    private JComboBox dayJCB;
    private JRadioButton maleJRB;
    private JRadioButton femaleJRB;
    private JComboBox politics_statusJCB;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentManagerInternalFrame frame = new StudentManagerInternalFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public StudentManagerInternalFrame() {
        setIconifiable(true);
        setTitle("\u5B66\u751F\u4FE1\u606F\u4FEE\u6539");
        setClosable(true);
        setBounds(350, 80, 853, 604);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 94, 821, 180);
        getContentPane().add(scrollPane);

        studenttable = new JTable();
        studenttable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                studentTableMousePressed(e);
            }
        });
        studenttable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "学号", "学院", "班级", "姓名", "性别", "出生日期", "政治面貌", "家庭住址", "电话", "宿舍号"
                }
        ) {
            boolean[] columnEditables = new boolean[]{
                    false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        studenttable.getColumnModel().getColumn(0).setPreferredWidth(120);
        studenttable.getColumnModel().getColumn(1).setPreferredWidth(150);
        studenttable.getColumnModel().getColumn(2).setPreferredWidth(100);
        studenttable.getColumnModel().getColumn(3).setPreferredWidth(75);
        studenttable.getColumnModel().getColumn(4).setPreferredWidth(40);
        studenttable.getColumnModel().getColumn(5).setPreferredWidth(100);
        studenttable.getColumnModel().getColumn(6).setPreferredWidth(80);
        studenttable.getColumnModel().getColumn(7).setPreferredWidth(200);
        studenttable.getColumnModel().getColumn(8).setPreferredWidth(150);
        studenttable.getColumnModel().getColumn(9).setPreferredWidth(50);
        scrollPane.setViewportView(studenttable);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u641C\u7D22", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(10, 18, 821, 55);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("\u59D3\u540D");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel.setBounds(21, 20, 47, 25);
        panel.add(lblNewLabel);

        s_nameTxt = new JTextField();
        s_nameTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        s_nameTxt.setBounds(60, 20, 82, 24);
        panel.add(s_nameTxt);
        s_nameTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u5B66\u53F7");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(150, 20, 47, 25);
        panel.add(lblNewLabel_1);

        s_studentIdTxt = new JTextField();
        s_studentIdTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        s_studentIdTxt.setColumns(10);
        s_studentIdTxt.setBounds(190, 20, 132, 24);
        panel.add(s_studentIdTxt);

        JLabel lblNewLabel_1_1 = new JLabel("\u5B66\u9662");
        lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_1_1.setBounds(340, 20, 47, 25);
        panel.add(lblNewLabel_1_1);

        s_secondaryJCB = new JComboBox();
        s_secondaryJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        s_secondaryJCB.setBounds(378, 20, 143, 24);
        panel.add(s_secondaryJCB);

        JLabel lblNewLabel_1_1_1 = new JLabel("\u73ED\u7EA7");
        lblNewLabel_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_1_1_1.setBounds(531, 20, 47, 25);
        panel.add(lblNewLabel_1_1_1);

        s_classJCB = new JComboBox();
        s_classJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        s_classJCB.setBounds(568, 20, 143, 24);
        panel.add(s_classJCB);

        JButton btnNewButton = new JButton("\u67E5\u8BE2");
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentSearchActionPerformed(e);
            }
        });
        btnNewButton.setIcon(new ImageIcon(StudentManagerInternalFrame.class.getResource("/image/\u67E5\u8BE2.png")));
        btnNewButton.setBounds(721, 18, 90, 28);
        panel.add(btnNewButton);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "\u4FEE\u6539", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(10, 304, 821, 223);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_3 = new JLabel("\u59D3\u540D");
        lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(20, 26, 47, 25);
        panel_1.add(lblNewLabel_3);

        nameTxt = new JTextField();
        nameTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        nameTxt.setColumns(10);
        nameTxt.setBounds(60, 27, 91, 24);
        panel_1.add(nameTxt);

        JLabel lblNewLabel_3_1 = new JLabel("\u5B66\u53F7");
        lblNewLabel_3_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_3_1.setBounds(192, 26, 47, 25);
        panel_1.add(lblNewLabel_3_1);

        studentIdTxt = new JTextField();
        studentIdTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        studentIdTxt.setColumns(10);
        studentIdTxt.setBounds(234, 27, 149, 24);
        studentIdTxt.setEditable(false);
        panel_1.add(studentIdTxt);

        JLabel lblNewLabel_3_1_1 = new JLabel("\u51FA\u751F\u65E5\u671F");
        lblNewLabel_3_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_3_1_1.setBounds(450, 25, 70, 25);
        panel_1.add(lblNewLabel_3_1_1);

        yearJCB = new JComboBox();
        yearJCB.setModel(new DefaultComboBoxModel(new String[]{"2000\u5E74", "2001\u5E74", "2002\u5E74", "2003\u5E74", "2004\u5E74", "2005\u5E74"}));
        yearJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        yearJCB.setBounds(520, 26, 83, 23);
        panel_1.add(yearJCB);

        monthJCB = new JComboBox();
        monthJCB.setModel(new DefaultComboBoxModel(new String[]{"1\u6708", "2\u6708", "3\u6708", "4\u6708", "5\u6708", "6\u6708", "7\u6708", "8\u6708", "9\u6708", "10\u6708", "11\u6708", "12\u6708"}));
        monthJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        monthJCB.setBounds(610, 26, 70, 23);
        panel_1.add(monthJCB);

        dayJCB = new JComboBox();
        dayJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        dayJCB.setModel(new DefaultComboBoxModel(new String[]{"1\u65E5", "2\u65E5", "3\u65E5", "4\u65E5", "5\u65E5", "6\u65E5", "7\u65E5", "8\u65E5", "9\u65E5", "10\u65E5", "11\u65E5", "12\u65E5", "13\u65E5", "14\u65E5", "15\u65E5", "16\u65E5", "17\u65E5", "18\u65E5", "19\u65E5", "20\u65E5", "21\u65E5", "22\u65E5", "23\u65E5", "24\u65E5", "25\u65E5", "26\u65E5", "27\u65E5", "28\u65E5", "29\u65E5", "30\u65E5", "31\u65E5"}));
        dayJCB.setBounds(690, 26, 70, 23);
        panel_1.add(dayJCB);

        JLabel lblNewLabel_2_1 = new JLabel("\u5B66\u9662");
        lblNewLabel_2_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_2_1.setBounds(192, 71, 47, 25);
        panel_1.add(lblNewLabel_2_1);

        secondaryJCB = new JComboBox();
        secondaryJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        secondaryJCB.setBounds(234, 72, 149, 23);
        panel_1.add(secondaryJCB);

        JLabel lblNewLabel_2_1_1 = new JLabel("\u73ED\u7EA7");
        lblNewLabel_2_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_2_1_1.setBounds(192, 114, 47, 25);
        panel_1.add(lblNewLabel_2_1_1);

        studentClassJCB = new JComboBox();
        studentClassJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        studentClassJCB.setBounds(234, 115, 149, 23);
        panel_1.add(studentClassJCB);

        JLabel lblNewLabel_2_1_1_1 = new JLabel("\u653F\u6CBB\u9762\u8C8C");
        lblNewLabel_2_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_2_1_1_1.setBounds(20, 114, 70, 25);
        panel_1.add(lblNewLabel_2_1_1_1);

        politics_statusJCB = new JComboBox();
        politics_statusJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        politics_statusJCB.setModel(new DefaultComboBoxModel(new String[]{"\u7FA4\u4F17", "\u56E2\u5458", "\u515A\u5458"}));
        politics_statusJCB.setBounds(88, 115, 63, 23);
        panel_1.add(politics_statusJCB);

        JLabel lblNewLabel_3_2 = new JLabel("\u6027\u522B");
        lblNewLabel_3_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_3_2.setBounds(20, 71, 47, 25);
        panel_1.add(lblNewLabel_3_2);

        maleJRB = new JRadioButton("\u7537");
        buttonGroup.add(maleJRB);
        maleJRB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        maleJRB.setBounds(60, 67, 50, 32);
        panel_1.add(maleJRB);

        femaleJRB = new JRadioButton("\u5973");
        buttonGroup.add(femaleJRB);
        femaleJRB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        femaleJRB.setBounds(112, 67, 50, 32);
        panel_1.add(femaleJRB);

        JLabel lblNewLabel_3_2_1 = new JLabel("\u5BBF\u820D\u53F7");
        lblNewLabel_3_2_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_3_2_1.setBounds(654, 71, 57, 25);
        panel_1.add(lblNewLabel_3_2_1);

        dormitory_numTxt = new JTextField();
        dormitory_numTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        dormitory_numTxt.setColumns(10);
        dormitory_numTxt.setBounds(705, 72, 55, 24);
        panel_1.add(dormitory_numTxt);

        JLabel lblNewLabel_3_1_1_1 = new JLabel("\u5BB6\u5EAD\u4F4F\u5740");
        lblNewLabel_3_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_3_1_1_1.setBounds(450, 114, 70, 25);
        panel_1.add(lblNewLabel_3_1_1_1);

        JLabel lblNewLabel_3_1_1_2 = new JLabel("\u7535\u8BDD");
        lblNewLabel_3_1_1_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_3_1_1_2.setBounds(450, 71, 38, 25);
        panel_1.add(lblNewLabel_3_1_1_2);

        phone_numTxt = new JTextField();
        phone_numTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        phone_numTxt.setColumns(10);
        phone_numTxt.setBounds(520, 72, 124, 24);
        panel_1.add(phone_numTxt);

        addressTxt = new JTextField();
        addressTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        addressTxt.setColumns(10);
        addressTxt.setBounds(520, 114, 240, 24);
        panel_1.add(addressTxt);
        //保存
        JButton btnNewButton_1 = new JButton("\u4FDD\u5B58");
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentUpdateActionPerformed(e);
            }
        });
        btnNewButton_1.setIcon(new ImageIcon(StudentManagerInternalFrame.class.getResource("/image/\u4FDD\u5B58.png")));
        btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        btnNewButton_1.setBounds(270, 158, 113, 34);
        panel_1.add(btnNewButton_1);
        //删除
        JButton btnNewButton_1_1 = new JButton("\u5220\u9664");
        btnNewButton_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentDeleteActionPerformed(e);
            }
        });
        btnNewButton_1_1.setIcon(new ImageIcon(StudentManagerInternalFrame.class.getResource("/image/\u5220\u9664.png")));
        btnNewButton_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        btnNewButton_1_1.setBounds(450, 158, 113, 34);
        panel_1.add(btnNewButton_1_1);

        this.fillTable(new Student());
        this.fillJCB("search");
    }

    //删除事件处理
    private void studentDeleteActionPerformed(ActionEvent e) {
        String name = this.nameTxt.getText();
        String studentId = this.studentIdTxt.getText();
        String id = null;
        //获取数据库中ID
        Student selectedStu = new Student(studentId, name);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = studentDao.list(con, selectedStu);
            while (rs.next()) {
                id = String.valueOf(rs.getInt("id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要删除的学生!");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "确定要删除该学生吗？");
        if (n == 0) {
            con = null;
            try {
                con = dbUtil.getCon();
                int num = studentDao.delete(con, id);
                if (num == 1) {
                    JOptionPane.showMessageDialog(null, "删除成功!");
                    this.restNull();
                    this.fillTable(new Student());
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    dbUtil.closeCon(con);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    //表格点击事件
    private void studentTableMousePressed(MouseEvent e) {
        int row = this.studenttable.getSelectedRow();
        Student selectedStu = new Student((String) this.studenttable.getValueAt(row, 0), (String) this.studenttable.getValueAt(row, 3));
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = studentDao.list(con, selectedStu);
            while (rs.next()) {
                this.studentIdTxt.setText(rs.getString("studentId"));
                this.nameTxt.setText(rs.getString("name"));
                if (rs.getString("sex").equals("男")) {
                    this.maleJRB.setSelected(true);
                } else {
                    this.femaleJRB.setSelected(true);
                }
                this.yearJCB.setSelectedIndex(Integer.parseInt(rs.getString("year")) - 2000);
                this.monthJCB.setSelectedIndex(Integer.parseInt(rs.getString("month")) - 1);
                this.dayJCB.setSelectedIndex(Integer.parseInt(rs.getString("day")) - 1);
                if (rs.getString("politics_status").equals("群众")) {
                    this.politics_statusJCB.setSelectedIndex(0);
                } else if (rs.getString("politics_status").equals("团员")) {
                    this.politics_statusJCB.setSelectedIndex(1);

                } else {
                    this.politics_statusJCB.setSelectedIndex(2);
                }
                this.phone_numTxt.setText(rs.getString("phone_num"));
                this.addressTxt.setText(rs.getString("address"));
                this.dormitory_numTxt.setText(rs.getString("dormitory_num"));
                ResultSet rs1 = secondaryDao.list(con, new Secondary());
                ResultSet rs2 = studentClassDao.list(con, new StudentClass());
                Secondary secondary = null;
                int secondaryIndex = 1;
                StudentClass studentClass = null;
                int studentClassIndex = 1;
                while (rs1.next()) {
                    if (rs1.getString("id").equals(rs.getString("secondaryId"))) {
                        break;
                    }
                    secondaryIndex++;
                }
                while (rs2.next()) {
                    if (rs2.getString("id").equals(rs.getString("classId"))) {
                        break;
                    }
                    studentClassIndex++;
                }
                this.secondaryJCB.setSelectedIndex(secondaryIndex);
                this.studentClassJCB.setSelectedIndex(studentClassIndex);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //修改事件处理
    private void studentUpdateActionPerformed(ActionEvent e) {
        String name = this.nameTxt.getText();
        String studentId = this.studentIdTxt.getText();
        String phone_num = this.phone_numTxt.getText();
        String dormitory_num = this.dormitory_numTxt.getText();
        String address = this.addressTxt.getText();
        int id = 0;
        //获取数据库中ID
        Student selectedStu = new Student(studentId);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = studentDao.list(con, selectedStu);
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        String politics_status = "";
        if (this.politics_statusJCB.getSelectedIndex() == 0) {
            politics_status = "群众";
        } else if (this.politics_statusJCB.getSelectedIndex() == 1) {
            politics_status = "团员";
        } else {
            politics_status = "党员";
        }
        String sex = "";
        if (this.maleJRB.isSelected()) {
            sex = "男";
        } else {
            sex = "女";
        }
        String year = String.valueOf(this.yearJCB.getSelectedIndex() + 2000);
        String month = String.valueOf(this.monthJCB.getSelectedIndex() + 1);
        String day = String.valueOf(this.dayJCB.getSelectedIndex() + 1);

        Secondary secondary = (Secondary) this.secondaryJCB.getSelectedItem();
        int secondaryId = secondary.getId();

        StudentClass studentClass = (StudentClass) this.studentClassJCB.getSelectedItem();
        int classId = studentClass.getId();

        if (StringUtil.isEmpty(name)) {
            JOptionPane.showMessageDialog(null, "姓名不能为空!");
            return;
        }
        if (StringUtil.isEmpty(studentId)) {
            JOptionPane.showMessageDialog(null, "学号不能为空!");
            return;
        }
        if (StringUtil.isEmpty(phone_num)) {
            JOptionPane.showMessageDialog(null, "联系电话不能为空!");
            return;
        }
        if (StringUtil.isEmpty(dormitory_num)) {
            JOptionPane.showMessageDialog(null, "宿舍号不能为空!");
            return;
        }
        if (StringUtil.isEmpty(address)) {
            JOptionPane.showMessageDialog(null, "家庭住址不能为空!");
            return;
        }
        if(secondaryJCB.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "请选择所在学院!");
            return;
        }
        if(studentClassJCB.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "请选择所在班级!");
            return;
        }

        Student student = new Student(id, studentId, name, sex, year, month, day, politics_status, address, phone_num, dormitory_num, secondaryId, classId);

        con = null;
        try {
            con = dbUtil.getCon();
            int addnum = studentDao.update(con, student);
            if (addnum == 1) {
                JOptionPane.showMessageDialog(null, "学生信息保存成功!");
                this.fillTable(new Student());
                this.s_secondaryJCB.setSelectedIndex(0);
                this.s_classJCB.setSelectedIndex(0);
                restNull();
            } else {
                JOptionPane.showMessageDialog(null, "学生信息保存失败!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "学生信息保存失败!");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //查询事件处理
    private void studentSearchActionPerformed(ActionEvent e) {
        String student_name = this.s_nameTxt.getText();
        String studentId = this.s_studentIdTxt.getText();
        Secondary secondary = (Secondary) this.s_secondaryJCB.getSelectedItem();
        StudentClass studentClass = (StudentClass) this.s_classJCB.getSelectedItem();
        int secondary_id = secondary.getId();
        int class_id = studentClass.getId();
        Student student = new Student(studentId, student_name, secondary_id, class_id);
        this.fillTable(student);
    }

    //初始化表格
    private void fillTable(Student student) {
        DefaultTableModel dtm = (DefaultTableModel) this.studenttable.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = studentDao.list(con, student);
            while (rs.next()) {
                Vector v = new Vector<>();
                v.add(rs.getString("studentId"));
                v.add(rs.getString("secondary_name"));
                v.add(rs.getString("studentclass_name"));
                v.add(rs.getString("name"));
                v.add(rs.getString("sex"));
                StringBuilder birth = new StringBuilder();
                birth.append(rs.getString("year"));
                birth.append("-");
                birth.append(rs.getString("month"));
                birth.append("-");
                birth.append(rs.getString("day"));
                v.add(birth);
                v.add(rs.getString("politics_status"));
                v.add(rs.getString("address"));
                v.add(rs.getString("phone_num"));
                v.add(rs.getString("dormitory_num"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //初始化下拉框
    private void fillJCB(String type) {
        Connection con = null;
        Secondary secondary = null;
        StudentClass studentClass = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = secondaryDao.list(con, new Secondary());
            ResultSet rs1 = studentClassDao.list(con, new StudentClass());
            if ("search".equals(type)) {
                secondary = new Secondary();
                studentClass = new StudentClass();
                secondary.setSecondary_name("请选择...");
                studentClass.setStudentClass_name("请选择...");
                secondary.setId(-1);
                studentClass.setId(-1);
                this.s_secondaryJCB.addItem(secondary);
                this.s_classJCB.addItem(studentClass);
                this.secondaryJCB.addItem(secondary);
                this.studentClassJCB.addItem(studentClass);
            }
            while (rs.next()) {
                secondary = new Secondary();
                secondary.setSecondary_name(rs.getString("secondary_name"));
                secondary.setId(rs.getInt("id"));

                if ("search".equals(type)) {
                    this.s_secondaryJCB.addItem(secondary);
                    this.secondaryJCB.addItem(secondary);
                } else if ("modify".equals(type)) {

                }
            }

            while (rs1.next()) {
                studentClass = new StudentClass();
                studentClass.setStudentClass_name(rs1.getString("studentclass_name"));
                studentClass.setId(rs1.getInt("id"));

                if ("search".equals(type)) {
                    this.s_classJCB.addItem(studentClass);
                    this.studentClassJCB.addItem(studentClass);
                } else if ("modify".equals(type)) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //清空表单
    private void restNull() {
        this.studentIdTxt.setText("");
        this.nameTxt.setText("");
        this.maleJRB.setSelected(true);
        this.secondaryJCB.setSelectedIndex(0);
        this.studentClassJCB.setSelectedIndex(0);
        this.politics_statusJCB.setSelectedIndex(0);
        this.addressTxt.setText("");
        this.dormitory_numTxt.setText("");
        this.yearJCB.setSelectedIndex(0);
        this.monthJCB.setSelectedIndex(0);
        this.dayJCB.setSelectedIndex(0);
        this.phone_numTxt.setText("");
    }
}
