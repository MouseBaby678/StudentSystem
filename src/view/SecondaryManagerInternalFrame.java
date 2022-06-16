package view;

import Util.DbUtil;
import Util.StringUtil;
import dao.SecondaryDao;
import model.Secondary;

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

public class SecondaryManagerInternalFrame extends JInternalFrame {
    private JTable secondary_table;
    private DbUtil dbUtil = new DbUtil();
    private SecondaryDao secondaryDao = new SecondaryDao();
    private JTextField s_secondary_nameTxt;
    private JTextField idTxt;
    private JTextField secondary_nameTxt;
    private JTextArea secondary_descTxt;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SecondaryManagerInternalFrame frame = new SecondaryManagerInternalFrame();
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
    public SecondaryManagerInternalFrame() {
        setEnabled(false);
        setClosable(true);
        setIconifiable(true);
        setTitle("\u5B66\u9662\u5220\u6539");
        setBounds(475, 75, 614, 646);

        JScrollPane scrollPane = new JScrollPane();

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "搜索", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "修改", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        //保存
        JButton btnNewButton_1 = new JButton("\u4FDD\u5B58");
        btnNewButton_1.setIcon(new ImageIcon(SecondaryManagerInternalFrame.class.getResource("/image/\u4FDD\u5B58.png")));
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondaryUpdateActionPerformed(e);
            }
        });
        btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        //删除
        JButton btnNewButton_1_1 = new JButton("\u5220\u9664");
        btnNewButton_1_1.setIcon(new ImageIcon(SecondaryManagerInternalFrame.class.getResource("/image/\u5220\u6539.png")));
        btnNewButton_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondaryDeleteActionPerformed(e);
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
        JLabel lblNewLabel_1 = new JLabel("编号");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        idTxt = new JTextField();
        idTxt.setEditable(false);
        idTxt.setColumns(10);
        //学院名称
        JLabel lblNewLabel_1_1 = new JLabel("学院名称");
        lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        secondary_nameTxt = new JTextField();
        secondary_nameTxt.setColumns(10);
        //学院备注
        JLabel lblNewLabel_1_2 = new JLabel("学院备注");
        lblNewLabel_1_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        secondary_descTxt = new JTextArea();
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(27)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(secondary_descTxt, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                                                .addGap(42)
                                                .addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(secondary_nameTxt, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(13, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(secondary_nameTxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(gl_panel_1.createSequentialGroup()
                                                        .addGap(2)
                                                        .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))))
                                .addGap(29)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(secondary_descTxt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(121, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);
        //学院名称
        JLabel lblNewLabel = new JLabel("\u5B66\u9662\u540D\u79F0");
        lblNewLabel.setIcon(new ImageIcon(SecondaryManagerInternalFrame.class.getResource("/image/\u540D\u79F0.png")));
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        s_secondary_nameTxt = new JTextField();
        s_secondary_nameTxt.setColumns(10);
        //查询
        JButton btnNewButton = new JButton("\u67E5\u8BE2");
        btnNewButton.setIcon(new ImageIcon(SecondaryManagerInternalFrame.class.getResource("/image/\u67E5\u8BE2.png")));
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondarySearchActionPerformed(e);
            }
        });
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap(31, Short.MAX_VALUE)
                                .addComponent(lblNewLabel)
                                .addGap(15)
                                .addComponent(s_secondary_nameTxt, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(s_secondary_nameTxt, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                                .addContainerGap(24, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        secondary_table = new JTable();
        secondary_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                secondaryTableMousePressed(e);
            }
        });
        secondary_table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "编号", "学院名称", "学院备注"
                }
        ) {
            boolean[] columnEditables = new boolean[]{
                    false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        secondary_table.getColumnModel().getColumn(0).setResizable(false);
        secondary_table.getColumnModel().getColumn(1).setResizable(false);
        secondary_table.getColumnModel().getColumn(2).setResizable(false);
        scrollPane.setViewportView(secondary_table);
        getContentPane().setLayout(groupLayout);

        this.fillTable(new Secondary());
    }

    //删除事件处理
    private void secondaryDeleteActionPerformed(ActionEvent e) {
        String id = this.idTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要删除的学院!");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "确定要删除该学院吗？");
        if (n == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                int num = secondaryDao.delete(con, id);
                if (num == 1) {
                    JOptionPane.showMessageDialog(null, "删除成功!");
                    this.restNull();
                    this.fillTable(new Secondary());
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
    private void secondaryUpdateActionPerformed(ActionEvent e) {
        String id = this.idTxt.getText();
        String secondary_name = this.secondary_nameTxt.getText();
        String secondary_desc = this.secondary_descTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要修改的记录!");
            return;
        }
        if (StringUtil.isEmpty(secondary_name)) {
            JOptionPane.showMessageDialog(null, "部门名称不能为空!");
            return;
        }
        if (StringUtil.isEmpty(secondary_desc)) {
            JOptionPane.showMessageDialog(null, "部门描述不能为空!");
            return;
        }
        Secondary secondary = new Secondary(Integer.parseInt(id), secondary_name, secondary_desc);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int num = secondaryDao.update(con, secondary);
            if (num == 1) {
                JOptionPane.showMessageDialog(null, "修改成功!");
                this.restNull();
                this.fillTable(new Secondary());
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
    private void secondaryTableMousePressed(MouseEvent e) {
        int row = this.secondary_table.getSelectedRow();
        this.idTxt.setText((String) this.secondary_table.getValueAt(row, 0));
        this.secondary_nameTxt.setText((String) this.secondary_table.getValueAt(row, 1));
        this.secondary_descTxt.setText((String) this.secondary_table.getValueAt(row, 2));
    }

    //查询事件处理
    private void secondarySearchActionPerformed(ActionEvent e) {
        String s_secondary_name = this.s_secondary_nameTxt.getText();
        Secondary secondary = new Secondary();
        secondary.setSecondary_name(s_secondary_name);
        this.fillTable(secondary);
    }

    //初始化表格
    private void fillTable(Secondary secondary) {
        DefaultTableModel dtm = (DefaultTableModel) this.secondary_table.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = secondaryDao.list(con, secondary);
            while (rs.next()) {
                Vector v = new Vector<>();
                v.add(rs.getString("id"));
                v.add(rs.getString("secondary_name"));
                v.add(rs.getString("secondary_desc"));
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
        this.secondary_nameTxt.setText("");
        this.secondary_descTxt.setText("");
    }
}
