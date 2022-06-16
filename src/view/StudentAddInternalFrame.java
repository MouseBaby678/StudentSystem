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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentAddInternalFrame extends JInternalFrame {
    private DbUtil dbUtil = new DbUtil();
    private StudentDao studentDao = new StudentDao();
    private SecondaryDao secondaryDao = new SecondaryDao();
    private StudentClassDao studentClassDao = new StudentClassDao();
    private JTextField nameTxt;
    private JTextField studentIdTxt;
    private JTextField phone_numTxt;
    private JTextField addressTxt;
    private JTextField dormitory_numTxt;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JComboBox studentClassJCB;
    private JComboBox secondaryJCB;
    private JRadioButton maleJRB;
    private JRadioButton femaleJRB;
    private JComboBox politics_statusJCB;
    private JComboBox dayJCB;
    private JComboBox monthJCB;
    private JComboBox yearJCB;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentAddInternalFrame frame = new StudentAddInternalFrame();
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
    public StudentAddInternalFrame() {
        setIconifiable(true);
        setClosable(true);
        setTitle("\u5B66\u751F\u6DFB\u52A0");
        setBounds(465, 135, 617, 544);
        //添加
        JButton btnNewButton = new JButton("\u6DFB\u52A0");
        btnNewButton.setIcon(new ImageIcon(StudentAddInternalFrame.class.getResource("/image/\u6DFB\u52A0.png")));
        btnNewButton.setBounds(77, 411, 127, 58);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentAddActionPerformed();
            }
        });
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        //重置
        JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
        btnNewButton_1.setIcon(new ImageIcon(StudentAddInternalFrame.class.getResource("/image/\u91CD\u7F6E.png")));
        btnNewButton_1.setBounds(392, 411, 127, 58);
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetActionPerformed(e);
            }
        });
        btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        getContentPane().setLayout(null);
        getContentPane().add(btnNewButton);
        getContentPane().add(btnNewButton_1);

        JLabel lblNewLabel = new JLabel("\u59D3\u540D");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel.setBounds(29, 36, 40, 33);
        getContentPane().add(lblNewLabel);

        nameTxt = new JTextField();
        nameTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        nameTxt.setBounds(79, 40, 147, 28);
        getContentPane().add(nameTxt);
        nameTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u5B66\u53F7");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(265, 36, 40, 33);
        getContentPane().add(lblNewLabel_1);

        studentIdTxt = new JTextField();
        studentIdTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        studentIdTxt.setColumns(10);
        studentIdTxt.setBounds(326, 40, 249, 28);
        getContentPane().add(studentIdTxt);

        JLabel lblNewLabel_2 = new JLabel("\u6027\u522B");
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(29, 92, 40, 33);
        getContentPane().add(lblNewLabel_2);

        maleJRB = new JRadioButton("\u7537");
        maleJRB.setSelected(true);
        buttonGroup.add(maleJRB);
        maleJRB.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        maleJRB.setBounds(79, 92, 50, 32);
        getContentPane().add(maleJRB);

        femaleJRB = new JRadioButton("\u5973");
        buttonGroup.add(femaleJRB);
        femaleJRB.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        femaleJRB.setBounds(138, 92, 50, 32);
        getContentPane().add(femaleJRB);

        JLabel lblNewLabel_1_1 = new JLabel("\u51FA\u751F\u65E5\u671F");
        lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(265, 92, 81, 33);
        getContentPane().add(lblNewLabel_1_1);

        yearJCB = new JComboBox();
        yearJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        yearJCB.setModel(new DefaultComboBoxModel(new String[]{"2000\u5E74", "2001\u5E74", "2002\u5E74", "2003\u5E74", "2004\u5E74", "2005\u5E74"}));
        yearJCB.setBounds(354, 95, 83, 30);
        getContentPane().add(yearJCB);

        monthJCB = new JComboBox();
        monthJCB.setModel(new DefaultComboBoxModel(new String[]{"1\u6708", "2\u6708", "3\u6708", "4\u6708", "5\u6708", "6\u6708", "7\u6708", "8\u6708", "9\u6708", "10\u6708", "11\u6708", "12\u6708"}));
        monthJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        monthJCB.setBounds(443, 95, 63, 30);
        getContentPane().add(monthJCB);

        dayJCB = new JComboBox();
        dayJCB.setModel(new DefaultComboBoxModel(new String[]{"1\u65E5", "2\u65E5", "3\u65E5", "4\u65E5", "5\u65E5", "6\u65E5", "7\u65E5", "8\u65E5", "9\u65E5", "10\u65E5", "11\u65E5", "12\u65E5", "13\u65E5", "14\u65E5", "15\u65E5", "16\u65E5", "17\u65E5", "18\u65E5", "19\u65E5", "20\u65E5", "21\u65E5", "22\u65E5", "23\u65E5", "24\u65E5", "25\u65E5", "26\u65E5", "27\u65E5", "28\u65E5", "29\u65E5", "30\u65E5", "31\u65E5"}));
        dayJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        dayJCB.setBounds(513, 95, 63, 30);
        getContentPane().add(dayJCB);

        JLabel lblNewLabel_1_1_1 = new JLabel("\u653F\u6CBB\u9762\u8C8C");
        lblNewLabel_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(29, 140, 81, 33);
        getContentPane().add(lblNewLabel_1_1_1);

        politics_statusJCB = new JComboBox();
        politics_statusJCB.setModel(new DefaultComboBoxModel(new String[]{"\u7FA4\u4F17", "\u56E2\u5458", "\u515A\u5458"}));
        politics_statusJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        politics_statusJCB.setBounds(120, 142, 106, 30);
        getContentPane().add(politics_statusJCB);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("\u8054\u7CFB\u7535\u8BDD");
        lblNewLabel_1_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel_1_1_1_1.setBounds(265, 140, 81, 33);
        getContentPane().add(lblNewLabel_1_1_1_1);

        phone_numTxt = new JTextField();
        phone_numTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        phone_numTxt.setColumns(10);
        phone_numTxt.setBounds(364, 144, 211, 28);
        getContentPane().add(phone_numTxt);

        JLabel lblNewLabel_1_1_1_2 = new JLabel("\u5BB6\u5EAD\u4F4F\u5740");
        lblNewLabel_1_1_1_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel_1_1_1_2.setBounds(29, 256, 81, 33);
        getContentPane().add(lblNewLabel_1_1_1_2);

        addressTxt = new JTextField();
        addressTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        addressTxt.setBounds(122, 256, 453, 104);
        getContentPane().add(addressTxt);
        addressTxt.setColumns(10);

        JLabel lblNewLabel_1_1_1_2_1 = new JLabel("\u5BBF\u820D\u53F7");
        lblNewLabel_1_1_1_2_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel_1_1_1_2_1.setBounds(440, 195, 63, 33);
        getContentPane().add(lblNewLabel_1_1_1_2_1);

        dormitory_numTxt = new JTextField();
        dormitory_numTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        dormitory_numTxt.setColumns(10);
        dormitory_numTxt.setBounds(505, 199, 70, 28);
        getContentPane().add(dormitory_numTxt);

        JLabel lblNewLabel_3 = new JLabel("\u73ED\u7EA7");
        lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(250, 195, 40, 33);
        getContentPane().add(lblNewLabel_3);

        studentClassJCB = new JComboBox();
        studentClassJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        studentClassJCB.setBounds(292, 198, 138, 30);
        getContentPane().add(studentClassJCB);

        JLabel lblNewLabel_3_1 = new JLabel("\u5B66\u9662");
        lblNewLabel_3_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel_3_1.setBounds(29, 195, 40, 33);
        getContentPane().add(lblNewLabel_3_1);

        secondaryJCB = new JComboBox();
        secondaryJCB.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        secondaryJCB.setBounds(72, 198, 177, 30);
        getContentPane().add(secondaryJCB);
        //调用填充方法
        this.fillJCB();
    }

    //添加事件处理
    private void studentAddActionPerformed() {
        String name = this.nameTxt.getText();
        String studentId = this.studentIdTxt.getText();
        String phone_num = this.phone_numTxt.getText();
        String dormitory_num = this.dormitory_numTxt.getText();
        String address = this.addressTxt.getText();

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

        Student student = new Student(studentId, name, sex, year, month, day, politics_status, address, phone_num, dormitory_num, secondaryId, classId);

        Connection con = null;
        try {
            con = dbUtil.getCon();
            int addnum = studentDao.add(con, student);
            if (addnum == 1) {
                JOptionPane.showMessageDialog(null, "学生信息添加成功!");
                resetNull();
            } else {
                JOptionPane.showMessageDialog(null, "学生信息添加失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "学生信息添加失败!");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //重置事件处理
    private void resetActionPerformed(ActionEvent e) {
        resetNull();
    }

    public void resetNull() {
        this.nameTxt.setText("");
        this.studentIdTxt.setText("");
        this.maleJRB.setSelected(true);
        this.addressTxt.setText("");
        this.phone_numTxt.setText("");
        this.dormitory_numTxt.setText("");
        this.studentIdTxt.setText("");
        this.politics_statusJCB.setSelectedIndex(0);
        this.yearJCB.setSelectedIndex(0);
        this.monthJCB.setSelectedIndex(0);
        this.dayJCB.setSelectedIndex(0);
        this.secondaryJCB.setSelectedIndex(0);
    }

    //填充下拉框
    private void fillJCB() {
        Connection con = null;
        Secondary secondary = null;
        StudentClass studentClass = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = secondaryDao.list(con, new Secondary());
            ResultSet rs1 = studentClassDao.list(con, new StudentClass());
            while (rs.next()) {
                secondary = new Secondary();
                secondary.setId(rs.getInt("id"));
                secondary.setSecondary_name(rs.getString("secondary_name"));
                this.secondaryJCB.addItem(secondary);//直接添加Secondary类型，并且重写toString()方法，方便后续获取secondaryId
            }
            while (rs1.next()) {
                studentClass = new StudentClass();
                studentClass.setId(rs1.getInt("id"));
                studentClass.setStudentClass_name(rs1.getString("studentclass_name"));
                this.studentClassJCB.addItem(studentClass);

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
}
