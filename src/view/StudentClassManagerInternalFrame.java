package view;

import Util.DbUtil;
import Util.StringUtil;
import dao.StudentClassDao;
import model.StudentClass;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
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

public class StudentClassManagerInternalFrame extends JInternalFrame {
    private JTable studentClass_table;
    private DbUtil dbUtil = new DbUtil();
    private StudentClassDao studentClassDao = new StudentClassDao();
    private JTextField s_studentClass_nameTxt;
    private JTextField idTxt;
    private JTextField studentClass_nameTxt;
    private JTextArea studentClass_descTxt;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentClassManagerInternalFrame frame = new StudentClassManagerInternalFrame();
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
    public StudentClassManagerInternalFrame() {
        setEnabled(false);
        setClosable(true);
        setIconifiable(true);
        setTitle("\u73ED\u7EA7\u5220\u6539");
        setBounds(475, 75, 614, 646);

        JScrollPane scrollPane = new JScrollPane();

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "搜索", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "修改", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        //修改
        JButton btnNewButton_1 = new JButton("\u4FDD\u5B58");
        btnNewButton_1.setIcon(new ImageIcon(StudentClassManagerInternalFrame.class.getResource("/image/\u4FDD\u5B58.png")));
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentClassUpdateActionPerformed(e);
            }
        });
        btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        //删除
        JButton btnNewButton_1_1 = new JButton("\u5220\u9664");
        btnNewButton_1_1.setIcon(new ImageIcon(StudentClassManagerInternalFrame.class.getResource("/image/\u5220\u6539.png")));
        btnNewButton_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentClassDeleteActionPerformed(e);
            }
        });
        btnNewButton_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(110)
                                .addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                                .addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                                .addGap(116))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(31)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE))
                                                .addContainerGap(37, Short.MAX_VALUE))))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(33)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                        .addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        //编号
        JLabel lblNewLabel_1 = new JLabel("\u7F16\u53F7");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        idTxt = new JTextField();
        idTxt.setEditable(false);
        idTxt.setColumns(10);
        //班级名称
        JLabel lblNewLabel_1_1 = new JLabel("\u73ED\u7EA7\u540D\u79F0");
        lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        studentClass_nameTxt = new JTextField();
        studentClass_nameTxt.setColumns(10);
        //班级备注
        JLabel lblNewLabel_1_2 = new JLabel("\u73ED\u7EA7\u5907\u6CE8");
        lblNewLabel_1_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        studentClass_descTxt = new JTextArea();
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(27)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(studentClass_descTxt, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                                                .addGap(42)
                                                .addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(studentClass_nameTxt, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(13, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(studentClass_nameTxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(gl_panel_1.createSequentialGroup()
                                                        .addGap(2)
                                                        .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))))
                                .addGap(29)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(studentClass_descTxt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(121, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);
        //班级名称
        JLabel lblNewLabel = new JLabel("\u73ED\u7EA7\u540D\u79F0");
        lblNewLabel.setIcon(new ImageIcon(StudentClassManagerInternalFrame.class.getResource("/image/\u540D\u79F0.png")));
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        s_studentClass_nameTxt = new JTextField();
        s_studentClass_nameTxt.setColumns(10);
        //查询
        JButton btnNewButton = new JButton("\u67E5\u8BE2");
        btnNewButton.setIcon(new ImageIcon(StudentClassManagerInternalFrame.class.getResource("/image/\u67E5\u8BE2.png")));
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentClassSearchActionPerformed(e);
            }
        });
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap(31, Short.MAX_VALUE)
                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(s_studentClass_nameTxt, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                                .addGap(34)
                                .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                .addGap(26))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnNewButton)
                                        .addComponent(s_studentClass_nameTxt, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                                .addContainerGap(24, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        studentClass_table = new JTable();
        studentClass_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                studentClassTableMousePressed(e);
            }
        });
        studentClass_table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "编号", "班级名称", "班级备注"
                }
        ) {
            boolean[] columnEditables = new boolean[]{
                    false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        studentClass_table.getColumnModel().getColumn(0).setResizable(false);
        studentClass_table.getColumnModel().getColumn(1).setResizable(false);
        studentClass_table.getColumnModel().getColumn(2).setResizable(false);
        scrollPane.setViewportView(studentClass_table);
        getContentPane().setLayout(groupLayout);

        this.fillTable(new StudentClass());
    }

    //删除事件处理
    private void studentClassDeleteActionPerformed(ActionEvent e) {
        String id = this.idTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要删除的班级!");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "确定要删除该班级吗？");
        if (n == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                int num = studentClassDao.delete(con, id);
                if (num == 1) {
                    JOptionPane.showMessageDialog(null, "删除成功!");
                    this.restNull();
                    this.fillTable(new StudentClass());
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

    //修改事件处理
    private void studentClassUpdateActionPerformed(ActionEvent e) {
        String id = this.idTxt.getText();
        String studentClass_name = this.studentClass_nameTxt.getText();
        String studentClass_desc = this.studentClass_descTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要修改的记录!");
            return;
        }
        if (StringUtil.isEmpty(studentClass_name)) {
            JOptionPane.showMessageDialog(null, "部门名称不能为空!");
            return;
        }
        if (StringUtil.isEmpty(studentClass_desc)) {
            JOptionPane.showMessageDialog(null, "部门描述不能为空!");
            return;
        }
        StudentClass studentClass = new StudentClass(Integer.parseInt(id), studentClass_name, studentClass_desc);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int num = studentClassDao.update(con, studentClass);
            if (num == 1) {
                JOptionPane.showMessageDialog(null, "修改成功!");
                this.restNull();
                this.fillTable(new StudentClass());
            } else {
                JOptionPane.showMessageDialog(null, "修改失败!");
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

    //表格点击事件
    private void studentClassTableMousePressed(MouseEvent e) {
        int row = this.studentClass_table.getSelectedRow();
        this.idTxt.setText((String) this.studentClass_table.getValueAt(row, 0));
        this.studentClass_nameTxt.setText((String) this.studentClass_table.getValueAt(row, 1));
        this.studentClass_descTxt.setText((String) this.studentClass_table.getValueAt(row, 2));
    }

    //查询事件处理
    private void studentClassSearchActionPerformed(ActionEvent e) {
        String s_studentClass_name = this.s_studentClass_nameTxt.getText();
        StudentClass studentClass = new StudentClass();
        studentClass.setStudentClass_name(s_studentClass_name);
        this.fillTable(studentClass);
    }

    //初始化表格
    private void fillTable(StudentClass studentClass) {
        DefaultTableModel dtm = (DefaultTableModel) this.studentClass_table.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = studentClassDao.list(con, studentClass);
            while (rs.next()) {
                Vector v = new Vector<>();
                v.add(rs.getString("id"));
                v.add(rs.getString("studentClass_name"));
                v.add(rs.getString("studentClass_desc"));
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

    //清空表单
    private void restNull() {
        this.idTxt.setText("");
        this.studentClass_nameTxt.setText("");
        this.studentClass_descTxt.setText("");
    }
}
