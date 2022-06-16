package view;

import Util.DbUtil;
import Util.StringUtil;
import dao.UserDao;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class RegFrame extends JFrame {

    private JPanel contentPane;
    private JTextField userNameText;

    private DbUtil dbUtil = new DbUtil();
    private UserDao userDao = new UserDao();
    private JPasswordField passwordTxt;
    private JPasswordField re_passwordTxt;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegFrame frame = new RegFrame();
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
    public RegFrame() {
        setResizable(false);
        setTitle("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 970, 570);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
        lblNewLabel_1.setIcon(new ImageIcon(RegFrame.class.getResource("/image/\u7528\u6237\u540D.png")));
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(667, 190, 107, 30);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("\u5BC6   \u7801\uFF1A");
        lblNewLabel_1_1.setIcon(new ImageIcon(RegFrame.class.getResource("/image/\u5BC6\u7801.png")));
        lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_1_1.setBounds(667, 240, 107, 30);
        contentPane.add(lblNewLabel_1_1);

        userNameText = new JTextField();
        userNameText.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        userNameText.setBounds(774, 190, 138, 30);
        contentPane.add(userNameText);
        userNameText.setColumns(10);

        //注册
        JButton registerButton = new JButton("\u6CE8\u518C");
        registerButton.setIcon(new ImageIcon(RegFrame.class.getResource("/image/\u6CE8\u518C.png")));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regActionPerformed(e);
            }
        });
        registerButton.setFocusable(false);
        registerButton.setBackground(new Color(240, 248, 255));
        registerButton.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        registerButton.setBounds(705, 370, 185, 46);
        contentPane.add(registerButton);

        //注 册 账 号
        JLabel lblNewLabel_2 = new JLabel("\u6CE8 \u518C \u8D26 \u53F7");
        lblNewLabel_2.setForeground(new Color(30, 144, 255));
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 34));
        lblNewLabel_2.setBounds(713, 75, 167, 73);
        contentPane.add(lblNewLabel_2);


        JLabel lblNewLabel_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
        lblNewLabel_3.setIcon(new ImageIcon(RegFrame.class.getResource("/image/\u5BC6\u7801.png")));
        lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(667, 290, 107, 30);
        contentPane.add(lblNewLabel_3);


        passwordTxt = new JPasswordField();
        passwordTxt.setBounds(774, 242, 138, 30);
        contentPane.add(passwordTxt);

        re_passwordTxt = new JPasswordField();
        re_passwordTxt.setBounds(774, 291, 138, 30);
        contentPane.add(re_passwordTxt);


        JButton backButton = new JButton("\u8FD4\u56DE");
        backButton.setIcon(new ImageIcon(RegFrame.class.getResource("/image/\u8FD4\u56DE.png")));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backActionPerformed(e);
            }
        });
        backButton.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        backButton.setFocusable(false);
        backButton.setBackground(new Color(240, 248, 255));
        backButton.setBounds(705, 442, 185, 46);
        contentPane.add(backButton);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(RegFrame.class.getResource("/image/login.png")));
        lblNewLabel.setBounds(0, 0, 960, 540);
        contentPane.add(lblNewLabel);
        this.setLocationRelativeTo(null);

    }

    private void backActionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

    private void regActionPerformed(ActionEvent e) {
        String userName = this.userNameText.getText();
        String password = new String(this.passwordTxt.getPassword());
        String re_password = new String(this.re_passwordTxt.getPassword());

        if (StringUtil.isEmpty(userName)) {
            JOptionPane.showMessageDialog(null, "账号不能为空!");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空!");
            return;
        }
        if (StringUtil.isEmpty(re_password)) {
            JOptionPane.showMessageDialog(null, "确认密码不能为空!");
            return;
        }
        if (password.equals(re_password)) {
            Connection con = null;
            User user = new User(userName, password);

            try {
                con = dbUtil.getCon();
                int addnum = userDao.addUser(con, user);
                if (addnum == 1) {
                    JOptionPane.showMessageDialog(null, "注册成功!");
                } else {
                    JOptionPane.showMessageDialog(null, "注册失败!");
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
        } else {
            JOptionPane.showMessageDialog(null, "两次密码不一致!");
            return;
        }
    }
}
