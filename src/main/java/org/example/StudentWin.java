package org.example;

import java.awt.*;
import javax.swing.*;

public class StudentWin extends JFrame {//集成视图
    JFrame Win;
    JTabbedPane j;
    JLabel jl_no = new JLabel("学号:");
    JLabel jl_name = new JLabel("姓名:");
    JLabel jl_class = new JLabel("班级:");
    JLabel jl_age = new JLabel("年龄:");
    JLabel jl_sex= new JLabel("性别:");
    JTextField Name=new JTextField(10);
    JTextField Teacher=new JTextField(10);
    JTextField BuildingNumber=new JTextField(10);
    JTextField courseName=new JTextField(10);
    JTextField courseTeacher=new JTextField(10);
    JTextField test=new JTextField(10);
    JButton sByNameBtn = new JButton("按课程名模糊查询");
    JButton sByTeaBtn = new JButton("按教师名模糊查询");
    JButton sAll = new JButton("全部显示");

    JButton applyBtn = new JButton("申请空教室");
    JButton sApply = new JButton("显示申请记录");
    JButton delBtn = new JButton("撤销申请");

    JButton NameBtn = new JButton("课程名模糊查询");
    JButton TeaBtn = new JButton("教师名模糊查询");
    JButton showAllCourse = new JButton("显示全部课程信息");
    JButton showEmptyRoom = new JButton("显示所有空教室");
    ButtonGroup bgclass = new ButtonGroup();


    //创建楼号选择单选按钮
    JRadioButton radioSJ = new JRadioButton("三江楼");
    JRadioButton radioSS = new JRadioButton("三山楼");
    JRadioButton radioJT = new JRadioButton("讲堂群");
    JPanel sGrade, cApply, sClass, sStu;//成绩查询、空教室申请、课程查询、学生信息查询
    JTable table1 = new JTable();
    JTable table2 = new JTable();
    JTable table3 = new JTable();
    JTable table4 = new JTable();
    // 创建显示表格的滚动面板
    JScrollPane scrollPane1 = new JScrollPane(table1);
    JScrollPane scrollPane2 = new JScrollPane(table2);
    JScrollPane scrollPane3 = new JScrollPane(table3);
    Font font1 = new Font("微软雅黑", Font.PLAIN, 18);
    Font font2 = new Font("微软雅黑", Font.PLAIN, 14);
    Font font3 = new Font("微软雅黑", Font.PLAIN, 10);
    HandleStuWin handleStuWin=new HandleStuWin();;

    StudentWin() {
        //组件创建
        Win = new JFrame("学生界面");
        j = new JTabbedPane(JTabbedPane.LEFT);
        //主界面初始化
        sGrade = new JPanel(null);
        cApply = new JPanel(null);
        sClass = new JPanel(null);
        sStu = new JPanel(null);
        //设置布局
        JLabel jl1 = new JLabel("成绩查询");
        jl1.setFont(font1);
        jl1.setSize(200,20);
        jl1.setLocation(500,10);
        sGrade.add(jl1);
        //添加面板中的组件
        JLabel jl5 = new JLabel("课程名:");
        jl5.setFont(font2);
        jl5.setSize(200,20);
        jl5.setLocation(20,40);
        sGrade.add(jl5);
        Name.setFont(font2);
        Name.setSize(200,20);
        Name.setLocation(20,65);
        sGrade.add(Name);
        sByNameBtn.setSize(200,20);
        sByNameBtn.setLocation(20,90);
        handleStuWin.setWin(this);
        sByNameBtn.addActionListener(handleStuWin);
        sGrade.add(sByNameBtn);
        JLabel jl6 = new JLabel("教师姓名:");
        jl6.setFont(font2);
        jl6.setSize(200,20);
        jl6.setLocation(20,135);
        sGrade.add(jl6);
        Teacher.setFont(font2);
        Teacher.setSize(200,20);
        Teacher.setLocation(20,160);
        sGrade.add(Teacher);
        sByTeaBtn.setSize(200,20);
        sByTeaBtn.setLocation(20,185);
        handleStuWin.setWin(this);
        sByTeaBtn.addActionListener(handleStuWin);
        sGrade.add(sByTeaBtn);
        sAll.setSize(200,20);
        sAll.setLocation(20,210);
        handleStuWin.setWin(this);
        sAll.addActionListener(handleStuWin);
        sGrade.add(sAll);
        scrollPane1.setSize(600,371);
        scrollPane1.setLocation(250,40);
        sGrade.add(scrollPane1);//添加滚动面板
        //-----------------------------------成绩查询面板构建结束-----------------------------
        JLabel jl2 = new JLabel("空教室申请");
        jl2.setFont(font1);
        jl2.setSize(200,20);
        jl2.setLocation(500,11);
        cApply.add(jl2);
        //添加面板中的组件
        bgclass.add(radioSJ);
        bgclass.add(radioSS);
        bgclass.add(radioJT);
        JLabel jl55 = new JLabel("教室楼名:");
        jl55.setFont(font2);
        jl55.setSize(200,20);
        jl55.setLocation(20,50);
        cApply.add(jl55);

        radioSJ.setFont(font2);
        radioSJ.setSize(200,20);
        radioSJ.setLocation(20,70);
        cApply.add(radioSJ);

        radioSS.setFont(font2);
        radioSS.setSize(200,20);
        radioSS.setLocation(20,90);
        cApply.add(radioSS);

        radioJT.setFont(font2);
        radioJT.setSize(200,20);
        radioJT.setLocation(20,110);
        cApply.add(radioJT);

        JLabel jl66 = new JLabel("教室号:");
        jl66.setFont(font2);
        jl66.setSize(200,20);
        jl66.setLocation(20,140);
        cApply.add(jl66);

        BuildingNumber.setFont(font2);
        BuildingNumber.setSize(200,20);
        BuildingNumber.setLocation(20,160);
        cApply.add(BuildingNumber);

        applyBtn.setSize(200,20);
        applyBtn.setLocation(20,180);
        handleStuWin.setWin(this);
        applyBtn.addActionListener(handleStuWin);
        cApply.add(applyBtn);

        sApply.setSize(200,20);
        sApply.setLocation(20,250);
        handleStuWin.setWin(this);
        sApply.addActionListener(handleStuWin);
        cApply.add(sApply);

        delBtn.setSize(200,20);
        delBtn.setLocation(20,280);
        handleStuWin.setWin(this);
        delBtn.addActionListener(handleStuWin);
        cApply.add(delBtn);

        showEmptyRoom.setSize(200,20);
        showEmptyRoom.setLocation(20,310);
        handleStuWin.setWin(this);
        showEmptyRoom.addActionListener(handleStuWin);
        cApply.add(showEmptyRoom);

        scrollPane2.setSize(600,371);
        scrollPane2.setLocation(250,41);
        cApply.add(scrollPane2);//添加滚动面板
        //-----------------------------------空教室申请面板构建结束-----------------------------
        JLabel jl3 = new JLabel("课程查询");
        jl3.setFont(font1);
        jl3.setSize(200,20);
        jl3.setLocation(500,10);
        sClass.add(jl3);
        //添加面板中的组件
        JLabel jl555 = new JLabel("课程名:");
        jl555.setFont(font2);
        jl555.setSize(200,20);
        jl555.setLocation(20,90);
        sClass.add(jl555);
        courseName.setFont(font2);
        courseName.setSize(200,20);
        courseName.setLocation(20,115);
        sClass.add(courseName);
        NameBtn.setSize(200,20);
        NameBtn.setLocation(20,140);
        handleStuWin.setWin(this);
        NameBtn.addActionListener(handleStuWin);
        sClass.add(NameBtn);
        JLabel jl666 = new JLabel("教师姓名:");
        jl666.setFont(font2);
        jl666.setSize(200,20);
        jl666.setLocation(20,170);
        sClass.add(jl666);
        courseTeacher.setFont(font2);
        courseTeacher.setSize(200,20);
        courseTeacher.setLocation(20,200);
        sClass.add(courseTeacher);
        TeaBtn.setSize(200,20);
        TeaBtn.setLocation(20,230);
        handleStuWin.setWin(this);
        TeaBtn.addActionListener(handleStuWin);
        sClass.add(TeaBtn);
        showAllCourse.setSize(200,20);
        showAllCourse.setLocation(20,260);
        handleStuWin.setWin(this);
        showAllCourse.addActionListener(handleStuWin);
        sClass.add(showAllCourse);
        scrollPane3.setSize(600,371);
        scrollPane3.setLocation(250,40);
        sClass.add(scrollPane3);//添加滚动面板
        //-----------------------------------课程查询面板构建结束-----------------------------
        JLabel jl4 = new JLabel("学生信息查询");
        jl4.setFont(font1);
        jl4.setSize(200,20);
        jl4.setLocation(400,10);
        sStu.add(jl4);
        jl_no.setFont(font2);
        jl_no.setSize(200,20);
        jl_no.setLocation(400,90);
        jl_name.setFont(font2);
        jl_name.setSize(200,20);
        jl_name.setLocation(400,140);
        jl_class.setFont(font2);
        jl_class.setSize(200,20);
        jl_class.setLocation(400,190);
        jl_age.setFont(font2);
        jl_age.setSize(200,20);
        jl_age.setLocation(400,240);
        jl_sex.setFont(font2);
        jl_sex.setSize(200,20);
        jl_sex.setLocation(400,290);
        handleStuWin.setWinInfo();
        sStu.add(jl_no);
        sStu.add(jl_name);
        sStu.add(jl_class);
        sStu.add(jl_age);
        sStu.add(jl_sex);
        //-----------------------------------个人信息查看面板构建结束-----------------------------

        //添加面板至选项卡窗格中
        j.addTab("成绩查询", sGrade);
        j.addTab("空教室查询", cApply);
        j.addTab("课程信息查询", sClass);
        j.addTab("学生信息查询", sStu);
        //设置主界面
        Win.setContentPane(j);
        Win.setSize(1000, 500);
        Win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Win.setVisible(true);
    }
}
