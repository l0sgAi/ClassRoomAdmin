package org.example;

import java.awt.*;
import javax.swing.*;

public class TeacherWin extends JFrame {//集成视图
    JFrame Win;
    JTabbedPane j;
    JPanel sStudent,manageCourse,classRoomApply,dataShow;//四个面板：搜索学生信息、课程信息管理、空教室申请、自身信息显示
    JLabel jl_no = new JLabel("工号:");
    JLabel jl_name = new JLabel("姓名:");
    JLabel jl_post = new JLabel("职务:");
    JLabel jl_age = new JLabel("年龄:");
    JLabel jl_sex= new JLabel("性别:");
    JTable table1 = new JTable();
    JTable table2 = new JTable();
    JTable table3 = new JTable();
    JButton sDataByName = new JButton("通过名字搜索学生信息");
    JButton sGradeByName = new JButton("通过名字搜索成绩信息");
    JButton sDataByNo = new JButton("通过学号搜索学生信息");
    JButton sGradeByNo = new JButton("通过学号搜索成绩信息");
    JButton sAllData = new JButton("显示所有学生信息");
    JButton sAllGrade = new JButton("显示所有成绩信息");
    JButton upDateBtn = new JButton("更新所选课程信息");
    JButton applyBtn = new JButton("申请空教室");
    JButton sApply = new JButton("显示申请记录");
    JButton delBtn = new JButton("撤销选中的申请记录");
    JButton showEmptyRoomTea = new JButton("显示所有空教室");
    ButtonGroup bgclass = new ButtonGroup();
    JRadioButton radioSJ = new JRadioButton("三江楼");
    JRadioButton radioSS = new JRadioButton("三山楼");
    JRadioButton radioJT = new JRadioButton("讲堂群");

    // 创建显示表格的滚动面板
    JTextField stuName = new JTextField(10);
    JTextField stuNo = new JTextField(10);
    JTextField courseName = new JTextField(10);
    JTextField courseTime = new JTextField(20);
    JTextField courseLocation = new JTextField(10);
    JTextField BuildingNumber=new JTextField(10);
    JScrollPane scrollPane1 = new JScrollPane(table1);
    JScrollPane scrollPane2 = new JScrollPane(table2);
    JScrollPane scrollPane3 = new JScrollPane(table3);
    Font font1 = new Font("微软雅黑", Font.PLAIN, 18);
    Font font2 = new Font("微软雅黑", Font.PLAIN, 14);
    Font font3 = new Font("微软雅黑", Font.PLAIN, 12);
    HandleTeaWin handleTeaWin=new HandleTeaWin();
    TeacherWin() {
        //组件创建
        Win = new JFrame("教师界面");
        j = new JTabbedPane(JTabbedPane.LEFT);
        //主界面初始化
        sStudent = new JPanel(null);
        manageCourse = new JPanel(null);
        classRoomApply = new JPanel(null);
        dataShow = new JPanel(null);
        //设置布局
        JLabel jl1 = new JLabel("学生信息查询");
        jl1.setFont(font1);
        jl1.setSize(200,20);
        jl1.setLocation(500,10);
        sStudent.add(jl1);
        handleTeaWin.setWin(this);
        handleTeaWin.setWinInfo();
        handleTeaWin.showCourseData();
        //添加面板中的组件
        JLabel jl1_h1 = new JLabel("学生姓名：");
        jl1_h1.setSize(200,20);
        jl1_h1.setLocation(20,40);
        sStudent.add(jl1_h1);
        stuName.setFont(font2);
        stuName.setSize(200,20);
        stuName.setLocation(20,70);
        sStudent.add(stuName);
        //姓名输入框
        sDataByName.setSize(200,20);
        sDataByName.setLocation(20,100);
        handleTeaWin.setWin(this);
        sDataByName.addActionListener(handleTeaWin);
        sStudent.add(sDataByName);
        //学生信息查询按钮
        sGradeByName.setSize(200,20);
        sGradeByName.setLocation(20,130);
        handleTeaWin.setWin(this);
        sGradeByName.addActionListener(handleTeaWin);
        sStudent.add(sGradeByName);
        //成绩信息查询按钮
        JLabel jl1_h2 = new JLabel("学生学号：");
        jl1_h2.setSize(200,20);
        jl1_h2.setLocation(20,200);
        sStudent.add(jl1_h2);

        stuNo.setSize(200,20);
        stuNo.setLocation(20,230);
        sStudent.add(stuNo);

        sDataByNo.setSize(200,20);
        sDataByNo.setLocation(20,260);
        handleTeaWin.setWin(this);
        sDataByNo.addActionListener(handleTeaWin);
        sStudent.add(sDataByNo);

        sGradeByNo.setSize(200,20);
        sGradeByNo.setLocation(20,290);
        handleTeaWin.setWin(this);
        sGradeByNo.addActionListener(handleTeaWin);
        sStudent.add(sGradeByNo);

        sAllData.setSize(200,20);
        sAllData.setLocation(20,340);
        handleTeaWin.setWin(this);
        sAllData.addActionListener(handleTeaWin);
        sStudent.add(sAllData);

        sAllGrade.setSize(200,20);
        sAllGrade.setLocation(20,370);
        handleTeaWin.setWin(this);
        sAllGrade.addActionListener(handleTeaWin);
        sStudent.add(sAllGrade);

        scrollPane1.setSize(600,371);
        scrollPane1.setLocation(250,40);
        sStudent.add(scrollPane1);//添加滚动面板
        //-----------------------------------学生信息查询面板构建结束-----------------------------
        JLabel jl2 = new JLabel("课程信息管理");
        jl2.setFont(font1);
        jl2.setSize(200,20);
        jl2.setLocation(500,11);
        manageCourse.add(jl2);
        JLabel jl2_h2 = new JLabel("修改右表中选中的课程信息");
        jl2_h2.setFont(font2);
        jl2_h2.setSize(200,20);
        jl2_h2.setLocation(20,40);
        manageCourse.add(jl2_h2);
        JLabel jl2_h3 = new JLabel("课程名：");
        jl2_h3.setSize(200,20);
        jl2_h3.setLocation(20,90);
        manageCourse.add(jl2_h3);
        courseName.setFont(font2);
        courseName.setSize(200,20);
        courseName.setLocation(20,120);
        manageCourse.add(courseName);
        JLabel jl2_h4 = new JLabel("上课时间：");
        jl2_h4.setSize(200,20);
        jl2_h4.setLocation(20,150);
        manageCourse.add(jl2_h4);
        courseTime.setFont(font2);
        courseTime.setSize(200,20);
        courseTime.setLocation(20,180);
        manageCourse.add(courseTime);
        JLabel jl2_h5 = new JLabel("上课地点：");
        jl2_h5.setSize(200,20);
        jl2_h5.setLocation(20,210);
        manageCourse.add(jl2_h5);
        courseLocation.setFont(font2);
        courseLocation.setSize(200,20);
        courseLocation.setLocation(20,240);
        manageCourse.add(courseLocation);
        upDateBtn.setSize(200,20);
        upDateBtn.setLocation(20,270);
        handleTeaWin.setWin(this);
        upDateBtn.addActionListener(handleTeaWin);
        manageCourse.add(upDateBtn);
        scrollPane2.setSize(600,371);
        scrollPane2.setLocation(250,40);
        manageCourse.add(scrollPane2);//添加滚动面板
        //-----------------------------------课程信息管理面板构建结束-----------------------------
        JLabel jl3 = new JLabel("空教室申请");
        jl3.setFont(font1);
        jl3.setSize(200,20);
        jl3.setLocation(500,10);
        classRoomApply.add(jl3);
        //添加面板中的组件
        bgclass.add(radioSJ);
        bgclass.add(radioSS);
        bgclass.add(radioJT);
        JLabel jl3_h1 = new JLabel("教室楼名:");
        jl3_h1.setFont(font2);
        jl3_h1.setSize(200,20);
        jl3_h1.setLocation(20,50);
        classRoomApply.add(jl3_h1);

        radioSJ.setFont(font2);
        radioSJ.setSize(200,20);
        radioSJ.setLocation(20,70);
        classRoomApply.add(radioSJ);

        radioSS.setFont(font2);
        radioSS.setSize(200,20);
        radioSS.setLocation(20,90);
        classRoomApply.add(radioSS);

        radioJT.setFont(font2);
        radioJT.setSize(200,20);
        radioJT.setLocation(20,110);
        classRoomApply.add(radioJT);

        JLabel jl3_h2 = new JLabel("教室号:");
        jl3_h2.setFont(font2);
        jl3_h2.setSize(200,20);
        jl3_h2.setLocation(20,140);

        classRoomApply.add(jl3_h2);
        BuildingNumber.setFont(font2);
        BuildingNumber.setSize(200,20);
        BuildingNumber.setLocation(20,160);
        classRoomApply.add(BuildingNumber);

        applyBtn.setSize(200,20);
        applyBtn.setLocation(20,180);
        handleTeaWin.setWin(this);
        applyBtn.addActionListener(handleTeaWin);
        classRoomApply.add(applyBtn);

        sApply.setSize(200,20);
        sApply.setLocation(20,250);
        handleTeaWin.setWin(this);
        sApply.addActionListener(handleTeaWin);
        classRoomApply.add(sApply);

        delBtn.setSize(200,20);
        delBtn.setLocation(20,280);
        handleTeaWin.setWin(this);
        delBtn.addActionListener(handleTeaWin);
        classRoomApply.add(delBtn);

        showEmptyRoomTea.setSize(200,20);
        showEmptyRoomTea.setLocation(20,310);
        handleTeaWin.setWin(this);
        showEmptyRoomTea.addActionListener(handleTeaWin);
        classRoomApply.add(showEmptyRoomTea);

        scrollPane3.setSize(600,371);
        scrollPane3.setLocation(250,40);
        classRoomApply.add(scrollPane3);//添加滚动面板
        //-----------------------------------空教室申请面板构建结束-----------------------------
        JLabel jl4 = new JLabel("账户信息");
        jl4.setFont(font1);
        jl4.setSize(200,20);
        jl4.setLocation(400,10);
        dataShow.add(jl4);
        jl_no.setFont(font2);
        jl_no.setSize(200,20);
        jl_no.setLocation(400,90);
        jl_name.setFont(font2);
        jl_name.setSize(200,20);
        jl_name.setLocation(400,140);
        jl_post.setFont(font2);
        jl_post.setSize(200,20);
        jl_post.setLocation(400,190);
        jl_age.setFont(font2);
        jl_age.setSize(200,20);
        jl_age.setLocation(400,240);
        jl_sex.setFont(font2);
        jl_sex.setSize(200,20);
        jl_sex.setLocation(400,290);
        dataShow.add(jl_no);
        dataShow.add(jl_name);
        dataShow.add(jl_post);
        dataShow.add(jl_age);
        dataShow.add(jl_sex);
        //-----------------------------------个人信息查看面板构建结束-----------------------------
        //添加面板至选项卡窗格中
        j.addTab("学生信息查询", sStudent);
        j.addTab("课程信息管理", manageCourse);
        j.addTab("空教室申请", classRoomApply);
        j.addTab("个人信息", dataShow);
        //设置主界面
        Win.setContentPane(j);
        Win.setSize(1000, 500);
        Win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Win.setVisible(true);
    }
}
