package org.example;
import java.awt.*;
import javax.swing.*;
public class SaWin extends JFrame {
    JFrame Win;
    JTabbedPane j;
    JPanel studentPanel,teacherPanel;//2个面板：学生申请管理、教师申请管理
    //创建登录单选按钮
    JButton agreeBtnStu = new JButton("同意学生申请");
    JButton agreeBtnTea = new JButton("同意教师申请");

    JButton cancelBtnStu = new JButton("拒绝学生申请");
    JButton cancelBtnTea = new JButton("拒绝教师申请");
    JButton delBtnStu = new JButton("删除学生申请");
    JButton delBtnTea = new JButton("删除教师申请");

    JTable table1 = new JTable();
    JTable table2 = new JTable();
    // 创建显示表格的滚动面板
    JScrollPane scrollPane1 = new JScrollPane(table1);
    JScrollPane scrollPane2 = new JScrollPane(table2);
    HandleSaWin handleSaWin=new HandleSaWin();;//控制器
    Font font1 = new Font("微软雅黑", Font.PLAIN, 18);
    Font font2 = new Font("微软雅黑", Font.PLAIN, 14);
    Font font3 = new Font("微软雅黑", Font.PLAIN, 12);
    SaWin() {
        Win = new JFrame("管理员界面");
        j = new JTabbedPane(JTabbedPane.LEFT);
        //主界面初始化
        studentPanel = new JPanel(null);
        teacherPanel = new JPanel(null);
        //设置布局
        JLabel jl1 = new JLabel("学生申请管理");
        jl1.setFont(font1);
        jl1.setSize(200,20);
        jl1.setLocation(300,10);
        studentPanel.add(jl1);
        //添加面板中的组件
        handleSaWin.setWin(this);
        agreeBtnStu.addActionListener(handleSaWin);
        agreeBtnStu.setSize(200,20);
        agreeBtnStu.setLocation(250,450);
        studentPanel.add(agreeBtnStu);

        handleSaWin.setWin(this);
        cancelBtnStu.addActionListener(handleSaWin);
        cancelBtnStu.setSize(200,20);
        cancelBtnStu.setLocation(250,480);
        studentPanel.add(cancelBtnStu);

        handleSaWin.setWin(this);
        delBtnStu.addActionListener(handleSaWin);
        delBtnStu.setSize(200,20);
        delBtnStu.setLocation(250,510);
        //studentPanel.add(delBtnStu);

        scrollPane1.setSize(600,371);
        scrollPane1.setLocation(50,40);
        studentPanel.add(scrollPane1);//添加滚动面板
        //------------------------学生申请管理面板结束----------------------
        //设置布局
        JLabel jl2 = new JLabel("学生申请管理");
        jl2.setFont(font1);
        jl2.setSize(200,20);
        jl2.setLocation(300,10);
        teacherPanel.add(jl2);
        //添加面板中的组件
        handleSaWin.setWin(this);
        agreeBtnTea.addActionListener(handleSaWin);
        agreeBtnTea.setSize(200,20);
        agreeBtnTea.setLocation(250,450);
        teacherPanel.add(agreeBtnTea);

        handleSaWin.setWin(this);
        cancelBtnTea.addActionListener(handleSaWin);
        cancelBtnTea.setSize(200,20);
        cancelBtnTea.setLocation(250,480);
        teacherPanel.add(cancelBtnTea);

        handleSaWin.setWin(this);
        delBtnTea.addActionListener(handleSaWin);
        delBtnTea.setSize(200,20);
        delBtnTea.setLocation(250,510);
        //teacherPanel.add(delBtnTea);

        scrollPane2.setSize(600,371);
        scrollPane2.setLocation(50,40);
        teacherPanel.add(scrollPane2);//添加滚动面板
        //------------------------教师申请管理面板结束----------------------
        j.addTab("学生申请管理", studentPanel);
        j.addTab("教师申请管理", teacherPanel);
        //设置主界面
        Win.setContentPane(j);
        Win.setSize(800, 600);
        Win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        handleSaWin.showApplyData();
        Win.setVisible(true);
    }
}
