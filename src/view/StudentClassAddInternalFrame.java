package view;

import Util.DbUtil;
import Util.StringUtil;
import dao.StudentClassDao;
import model.StudentClass;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class StudentClassAddInternalFrame extends JInternalFrame {
    private JTextField studentClass_nameTxt;
    private JTextField studentClass_descTxt;
    private DbUtil dbUtil = new DbUtil();
    private StudentClassDao studentClassDao = new StudentClassDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentClassAddInternalFrame frame = new StudentClassAddInternalFrame();
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
    public StudentClassAddInternalFrame() {
        setIconifiable(true);
        setClosable(true);
        setTitle("\u73ED\u7EA7\u6DFB\u52A0");
        setBounds(515, 175, 518, 411);

        JLabel lblNewLabel = new JLabel("\u73ED\u7EA7\u540D\u79F0\uFF1A");
        lblNewLabel.setIcon(new ImageIcon(StudentClassAddInternalFrame.class.getResource("/image/\u540D\u79F0.png")));
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        studentClass_nameTxt = new JTextField();
        studentClass_nameTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        studentClass_nameTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u73ED\u7EA7\u5907\u6CE8\uFF1A");
        lblNewLabel_1.setIcon(new ImageIcon(StudentClassAddInternalFrame.class.getResource("/image/\u5907\u6CE8.png")));
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        studentClass_descTxt = new JTextField();
        studentClass_descTxt.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        studentClass_descTxt.setColumns(10);
        //添加
        JButton btnNewButton = new JButton("\u6DFB\u52A0");
        btnNewButton.setIcon(new ImageIcon(StudentClassAddInternalFrame.class.getResource("/image/\u6DFB\u52A0.png")));
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentClassAddActionPerformed();
            }
        });
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        //重置
        JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
        btnNewButton_1.setIcon(new ImageIcon(StudentClassAddInternalFrame.class.getResource("/image/\u91CD\u7F6E.png")));
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetActionPerformed(e);
            }
        });
        btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(65)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(18)
                                                .addComponent(studentClass_nameTxt, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                                .addGap(16)
                                                .addComponent(studentClass_descTxt, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(110, Short.MAX_VALUE))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(55)
                                .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                                .addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                .addGap(54))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(47)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(studentClass_nameTxt, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(35)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(studentClass_descTxt, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(45)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
                                .addGap(59))
        );
        getContentPane().setLayout(groupLayout);

    }

    //岗位添加
    private void studentClassAddActionPerformed() {
        String studentClass_name = this.studentClass_nameTxt.getText();
        String studentClass_desc = this.studentClass_descTxt.getText();

        if (StringUtil.isEmpty(studentClass_name)) {
            JOptionPane.showMessageDialog(null, "岗位名称不能为空!");
            return;
        }
        if (StringUtil.isEmpty(studentClass_desc)) {
            JOptionPane.showMessageDialog(null, "岗位描述不能为空!");
            return;
        }

        StudentClass studentClass = new StudentClass(studentClass_name, studentClass_desc);

        Connection con = null;
        try {
            con = dbUtil.getCon();
            int addnum = studentClassDao.add(con, studentClass);
            if (addnum == 1) {
                JOptionPane.showMessageDialog(null, "添加成功!");
                resetNull();
            } else {
                JOptionPane.showMessageDialog(null, "添加失败!");
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

    //重置事件处理
    private void resetActionPerformed(ActionEvent e) {
        resetNull();
    }

    public void resetNull() {
        this.studentClass_nameTxt.setText("");
        this.studentClass_descTxt.setText("");
    }
}
