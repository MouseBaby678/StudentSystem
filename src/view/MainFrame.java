package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JDesktopPane table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
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
    public MainFrame() {
        setTitle("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //学院管理
        JMenu mnNewMenu = new JMenu("\u5B66\u9662\u7BA1\u7406");
        mnNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        mnNewMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/image/\u5B66\u9662\u7BA1\u7406.png")));
        menuBar.add(mnNewMenu);
        //学院添加
        JMenuItem mntmNewMenuItem = new JMenuItem("\u5B66\u9662\u6DFB\u52A0");
        mntmNewMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/image/\u6DFB\u52A0.png")));
        mntmNewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SecondaryAddInternalFrame secondaryAddInternalFrame = new SecondaryAddInternalFrame();
                secondaryAddInternalFrame.setVisible(true);
                table.add(secondaryAddInternalFrame);
            }
        });
        mntmNewMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        mnNewMenu.add(mntmNewMenuItem);
        //学院删改
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u5B66\u9662\u5220\u6539");
        mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrame.class.getResource("/image/\u5220\u6539.png")));
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SecondaryManagerInternalFrame secondaryManagerInternalFrame = new SecondaryManagerInternalFrame();
                secondaryManagerInternalFrame.setVisible(true);
                table.add(secondaryManagerInternalFrame);
            }
        });
        mntmNewMenuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        mnNewMenu.add(mntmNewMenuItem_1);
        //班级管理
        JMenu mnNewMenu_1 = new JMenu("\u73ED\u7EA7\u7BA1\u7406");
        mnNewMenu_1.setIcon(new ImageIcon(MainFrame.class.getResource("/image/\u73ED\u7EA7\u7BA1\u7406.png")));
        mnNewMenu_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        menuBar.add(mnNewMenu_1);
        //班级添加
        JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("\u73ED\u7EA7\u6DFB\u52A0");
        mntmNewMenuItem_1_1.setIcon(new ImageIcon(MainFrame.class.getResource("/image/\u6DFB\u52A0.png")));
        mnNewMenu_1.add(mntmNewMenuItem_1_1);
        mntmNewMenuItem_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentClassAddInternalFrame postAddInternalFrame = new StudentClassAddInternalFrame();
                postAddInternalFrame.setVisible(true);
                table.add(postAddInternalFrame);
            }
        });
        mntmNewMenuItem_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        //岗位维护
        JMenuItem mntmNewMenuItem_1_2 = new JMenuItem("\u73ED\u7EA7\u5220\u6539");
        mntmNewMenuItem_1_2.setIcon(new ImageIcon(MainFrame.class.getResource("/image/\u5220\u6539.png")));
        mnNewMenu_1.add(mntmNewMenuItem_1_2);
        mntmNewMenuItem_1_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentClassManagerInternalFrame postManagerInternalFrame = new StudentClassManagerInternalFrame();
                postManagerInternalFrame.setVisible(true);
                table.add(postManagerInternalFrame);
            }
        });
        mntmNewMenuItem_1_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        //学生信息管理
        JMenu mnNewMenu_1_1 = new JMenu("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406");
        mnNewMenu_1_1.setIcon(new ImageIcon(MainFrame.class.getResource("/image/\u5B66\u751F\u7BA1\u7406.png")));
        mnNewMenu_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        menuBar.add(mnNewMenu_1_1);
        //学生信息添加
        JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("\u5B66\u751F\u4FE1\u606F\u6DFB\u52A0");
        mntmNewMenuItem_2_1.setIcon(new ImageIcon(MainFrame.class.getResource("/image/\u6DFB\u52A0.png")));
        mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentAddInternalFrame studentAddInternalFrame = new StudentAddInternalFrame();
                studentAddInternalFrame.setVisible(true);
                table.add(studentAddInternalFrame);
            }
        });
        mntmNewMenuItem_2_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        mnNewMenu_1_1.add(mntmNewMenuItem_2_1);
        //学生信息修改
        JMenuItem mntmNewMenuItem_1_3_1 = new JMenuItem("\u5B66\u751F\u4FE1\u606F\u4FEE\u6539");
        mntmNewMenuItem_1_3_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentManagerInternalFrame studentManagerInternalFrame = new StudentManagerInternalFrame();
                studentManagerInternalFrame.setVisible(true);
                table.add(studentManagerInternalFrame);
            }
        });
        mntmNewMenuItem_1_3_1.setIcon(new ImageIcon(MainFrame.class.getResource("/image/\u5220\u6539.png")));
        mntmNewMenuItem_1_3_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        mnNewMenu_1_1.add(mntmNewMenuItem_1_3_1);

        JMenu mnNewMenu_1_1_1 = new JMenu("\u8BBE\u7F6E");
        mnNewMenu_1_1_1.setIcon(new ImageIcon(MainFrame.class.getResource("/image/\u8BBE\u7F6E.png")));
        mnNewMenu_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        menuBar.add(mnNewMenu_1_1_1);
        //退出系统
        JMenuItem mntmNewMenuItem_1_3_1_1 = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
        mntmNewMenuItem_1_3_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mntmNewMenuItem_1_3_1_1.setIcon(new ImageIcon(MainFrame.class.getResource("/image/\u9000\u51FA\u7CFB\u7EDF.png")));
        mntmNewMenuItem_1_3_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        mnNewMenu_1_1_1.add(mntmNewMenuItem_1_3_1_1);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        table = new JDesktopPane();
        contentPane.add(table, BorderLayout.CENTER);

        final JLabel background = new JLabel();
        URL resource = this.getClass().getResource("/image/back.jpg");
        final ImageIcon icon = new ImageIcon(resource);
        icon.setImage(icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
        background.setIcon(icon);
        background.setBounds(0, 0, this.getWidth(), this.getWidth());
        table.add(background, new Integer(Integer.MIN_VALUE));

        getContentPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int width = e.getComponent().getWidth();
                int height = e.getComponent().getHeight();
                icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
                background.setBounds(0, 0, width, height);
            }
        });
        this.setLocationRelativeTo(null);

    }

}
