package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student { // 定义成绩查询表格列名数组
    private String sql_exec;//sql语句
    DefaultTableModel tabModel = new DefaultTableModel();

    boolean single=false;//检查是否有重复申请
    boolean statecheck=false;//检查申请教室是否已经被申请
    int pFlag=0;//检查这个学生已经发出的申请数
    boolean exist=false;//检查教室是否存在

    String []arr = new String[]{"null","null","null","null","null"};//账号信息

    void getGradeData() {//获取所有成绩信息
        Statement showAll = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel1 = new DefaultTableModel();
        try {
            showAll = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select Student.sno,sname,sclass,Course.cno,cname,score,tname from " +
                    "((Student inner join " +
                    "Grade on Grade.sno=Student.sno and " +
                    "Student.sno='" + Login.loginNo.trim() + "'" +
                    "inner join Course on Course.cno=Grade.cno)) " +
                    "inner join " +
                    "Teacher on Course.tno=Teacher.tno";
            // 执行查询，把查询的结果赋值给结果集对象

            rs = showAll.executeQuery(sql_exec);
            tabModel1.addColumn("学号");
            tabModel1.addColumn("姓名");
            tabModel1.addColumn("班级");
            tabModel1.addColumn("课程号");
            tabModel1.addColumn("课程名");
            tabModel1.addColumn("成绩");
            tabModel1.addColumn("任课教师");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel1.addRow(row);
            }
            tabModel = tabModel1;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }

    void sByClassName(String className) {//按课程名查询
        Statement byClassName = null;//新建查询
        ResultSet rs;
        className = '%' + className + '%';
        System.out.println("|" + className + "|");
        DefaultTableModel tabModel2 = new DefaultTableModel();
        try {
            byClassName = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select Student.sno,sname,sclass,Course.cno,cname,score,tname from " +
                    "((Student inner join " +
                    "Grade on Grade.sno=Student.sno and " +
                    "Student.sno='" + Login.loginNo.trim() + "'" +
                    "inner join Course on Course.cno=Grade.cno and Course.cname like '" + className + "'" +
                    ")) " +
                    "inner join " +
                    "Teacher on Course.tno=Teacher.tno";
            // 执行查询，把查询的结果赋值给结果集对象

            rs = byClassName.executeQuery(sql_exec);
            tabModel2.addColumn("学号");
            tabModel2.addColumn("姓名");
            tabModel2.addColumn("班级");
            tabModel2.addColumn("课程号");
            tabModel2.addColumn("课程名");
            tabModel2.addColumn("成绩");
            tabModel2.addColumn("任课教师");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel2.addRow(row);
            }
            tabModel = tabModel2;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }

    void sByTeaName(String name) {//按教室名查询
        Statement byTeaName = null;//新建查询
        ResultSet rs;
        name = '%' + name + '%';
        DefaultTableModel tabModel3 = new DefaultTableModel();
        try {
            byTeaName = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select Student.sno,sname,sclass,Course.cno,cname,score,tname from " +
                    "((Student inner join " +
                    "Grade on Grade.sno=Student.sno and " +
                    "Student.sno='" + Login.loginNo.trim() + "'" +
                    "inner join Course on Course.cno=Grade.cno)) " +
                    "inner join " +
                    "Teacher on Course.tno=Teacher.tno and Teacher.tname like'" + name + "'";
            // 执行查询，把查询的结果赋值给结果集对象
            rs = byTeaName.executeQuery(sql_exec);
            tabModel3.addColumn("学号");
            tabModel3.addColumn("姓名");
            tabModel3.addColumn("班级");
            tabModel3.addColumn("课程号");
            tabModel3.addColumn("课程名");
            tabModel3.addColumn("成绩");
            tabModel3.addColumn("任课教师");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel3.addRow(row);
            }
            tabModel = tabModel3;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }

    void checkApply(String no, String name) {//申请检查
        Statement sta = null;//新建查询
        ResultSet rs;
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select * from Apply where asno='" + Login.loginNo.trim() + "'" +
                    " and arno='" + no + "'" + " and arname='" + name + "'";
            // 检查是否有重复申请
            rs = sta.executeQuery(sql_exec);
            single=false;
            if (rs.next()) {
                single=false;
            }
            else{
                single=true;
            }
            sql_exec = "use DB2;\n" +
                    "select count(asno) from Apply where asno='" + Login.loginNo.trim() + "'";//统计学生申请数量
            // 检查是这个学生的申请数量
            rs = sta.executeQuery(sql_exec);
            if (rs.next()) {
                pFlag=rs.getInt(1);
                System.out.println("申请数"+rs.getInt(1));
            }
            sql_exec = "use DB2;\n" +
                    "select * from Classroom where rno='" + no + "'" +
                    " and rname='" + name + "'" + " and rstate='" + "已预约" + "'";
            // 检查教室是否已预约
            rs = sta.executeQuery(sql_exec);
            statecheck=false;
            if (rs.next()) {
                statecheck=false;
            }
            else{
                statecheck=true;
            }
            if (statecheck&&single&&pFlag==0) {
                applyRoomStu(no, name);
            }
            exist=false;
            sql_exec = "use DB2;\n" +
                    "select * from Classroom where rno=\'" + no + "\'";//检查教室是否存在
            rs = sta.executeQuery(sql_exec);
            if (rs.next()) {
                exist=true;
            }
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }
    void applyRoomStu(String no, String name) {//学生申请
        Statement sta = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel4 = new DefaultTableModel();
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "insert into Apply (asno,arno,arname,astate) values(?,?,?,?)";
            // 执行插入
            PreparedStatement pre = DBConnection.Connection.prepareStatement(sql_exec);
            pre.setString(1, Login.loginNo);
            pre.setInt(2, Integer.parseInt(no));
            pre.setString(3, name);
            pre.setString(4, "待处理");
            pre.executeUpdate();//执行插入

            sql_exec = "use DB2;\n" +
                    "select sno,sname,sclass,arname,arno,astate from Student\n" +
                    "inner join Apply on Apply.asno=sno and sno='" + Login.loginNo.trim() + "'";
            rs = sta.executeQuery(sql_exec);
            tabModel4.addColumn("申请人学号");
            tabModel4.addColumn("申请人姓名");
            tabModel4.addColumn("申请人班级");
            tabModel4.addColumn("申请楼名");
            tabModel4.addColumn("申请教室号");
            tabModel4.addColumn("受理状态");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel4.addRow(row);
            }
            tabModel = tabModel4;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }

    void showApplyStu() {//显示学生申请
        Statement sta = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel4 = new DefaultTableModel();
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select sno,sname,sclass,arname,arno,astate from Student\n" +
                    "inner join Apply on Apply.asno=sno and sno='" + Login.loginNo.trim() + "'";
            rs = sta.executeQuery(sql_exec);
            tabModel4.addColumn("申请人学号");
            tabModel4.addColumn("申请人姓名");
            tabModel4.addColumn("申请人班级");
            tabModel4.addColumn("申请楼名");
            tabModel4.addColumn("申请教室号");
            tabModel4.addColumn("受理状态");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel4.addRow(row);
            }
            tabModel = tabModel4;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }
    void delApplyStu() {//删除学生申请
        Statement sta = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel4 = new DefaultTableModel();
        try {
            sta = DBConnection.Connection.createStatement();
            PreparedStatement pre = null;
            sql_exec = "use DB2;\n" +
                     "select * from Apply where astate='申请通过' and asno='" + Login.loginNo.trim() + "'";
            //检查清除的申请是否为通过的申请
            rs = sta.executeQuery(sql_exec);
            if(rs.next()) {//如是，更新对应申请教室的信息
                sql_exec = "use DB2;\n" +
                        "update Classroom set lsno=?,rstate=? where lsno=?";//更新记录
                pre=DBConnection.Connection.prepareStatement(sql_exec);
                pre.setString(1,null);
                pre.setString(2,"未预约");
                pre.setString(3,Login.loginNo.trim());
                pre.executeUpdate();//执行更新
            }
            sql_exec = "use DB2;\n" +
                    "delete from Apply where asno=?";//删除记录
            pre=DBConnection.Connection.prepareStatement(sql_exec);
            pre.setString(1,Login.loginNo.trim());
            pre.executeUpdate();//执行删除
            sql_exec = "use DB2;\n" +
                    "select sno,sname,sclass,arname,arno,astate from Student\n" +
                    "inner join Apply on Apply.asno=sno and sno='" + Login.loginNo.trim() + "'";
            rs = sta.executeQuery(sql_exec);
            tabModel4.addColumn("申请人学号");
            tabModel4.addColumn("申请人姓名");
            tabModel4.addColumn("申请人班级");
            tabModel4.addColumn("申请楼名");
            tabModel4.addColumn("申请教室号");
            tabModel4.addColumn("受理状态");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel4.addRow(row);
            }
            tabModel = tabModel4;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }
    void showEmptyRooms() {//显示空教室
        Statement sta = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel4 = new DefaultTableModel();
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select rname,rno,rstate from Classroom";
            rs = sta.executeQuery(sql_exec);
            tabModel4.addColumn("教室楼号");
            tabModel4.addColumn("教室楼名");
            tabModel4.addColumn("教室申请状态");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel4.addRow(row);
            }
            tabModel = tabModel4;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }
    void classQueueByName(String className) {//按课程名查询课程信息
        Statement sta = null;//新建查询
        ResultSet rs;
        className = '%' + className + '%';
        System.out.println("|" + className + "|");
        DefaultTableModel tabModel2 = new DefaultTableModel();
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "select Student.sno,sname,sclass,Course.cno,cname,ctime,clocation,tname from\n" +
                    "((Student inner join Grade on Student.sno=Grade.sno and Student.sno='" + Login.loginNo.trim() + "'\n" +
                    "inner join Course on Grade.cno=Course.cno and Course.cname like '" + className + "'"+
                    ")) inner join Teacher on Course.tno=Teacher.tno";
            // 执行查询，把查询的结果赋值给结果集对象
            rs = sta.executeQuery(sql_exec);
            tabModel2.addColumn("学号");
            tabModel2.addColumn("姓名");
            tabModel2.addColumn("班级");
            tabModel2.addColumn("课程号");
            tabModel2.addColumn("课程名");
            tabModel2.addColumn("上课时间");
            tabModel2.addColumn("上课地点");
            tabModel2.addColumn("任课教师");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel2.addRow(row);
            }
            tabModel = tabModel2;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }
    void classQueueByTea(String teaName) {//按教师名查询课程信息
        Statement sta = null;//新建查询
        ResultSet rs;
        teaName = '%' + teaName + '%';
        System.out.println("|" + teaName + "|");
        DefaultTableModel tabModel2 = new DefaultTableModel();
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "select Student.sno,sname,sclass,Course.cno,cname,ctime,clocation,tname from\n" +
                    "((Student inner join Grade on Student.sno=Grade.sno and Student.sno='" + Login.loginNo.trim() + "'\n" +
                    "inner join Course on Grade.cno=Course.cno)) inner join\n" +
                    "Teacher on Course.tno=Teacher.tno and tname like '" + teaName + "'";
            // 执行查询，把查询的结果赋值给结果集对象
            rs = sta.executeQuery(sql_exec);
            tabModel2.addColumn("学号");
            tabModel2.addColumn("姓名");
            tabModel2.addColumn("班级");
            tabModel2.addColumn("课程号");
            tabModel2.addColumn("课程名");
            tabModel2.addColumn("上课时间");
            tabModel2.addColumn("上课地点");
            tabModel2.addColumn("任课教师");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel2.addRow(row);
            }
            tabModel = tabModel2;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }
    void showClassInfo() {//显示所有课程信息
        Statement sta = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel2 = new DefaultTableModel();
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "select Student.sno,sname,sclass,Course.cno,cname,ctime,clocation,tname from\n" +
                    "((Student inner join Grade on Student.sno=Grade.sno and Student.sno='" + Login.loginNo.trim() + "'\n" +
                    "inner join Course on Grade.cno=Course.cno)) inner join Teacher on Course.tno=Teacher.tno";
            // 执行查询，把查询的结果赋值给结果集对象
            rs = sta.executeQuery(sql_exec);
            tabModel2.addColumn("学号");
            tabModel2.addColumn("姓名");
            tabModel2.addColumn("班级");
            tabModel2.addColumn("课程号");
            tabModel2.addColumn("课程名");
            tabModel2.addColumn("上课时间");
            tabModel2.addColumn("上课地点");
            tabModel2.addColumn("任课教师");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel2.addRow(row);
            }
            tabModel = tabModel2;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }
    void getStudentInfo() {//获取账号信息
        Statement sta = null;//新建查询
        ResultSet rs;
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "select * from Student where sno='" + Login.loginNo.trim() + "'";
            // 执行查询，把查询的结果赋值给结果集对象
            rs = sta.executeQuery(sql_exec);
            while(rs.next()) {
                arr[0]=rs.getString(1);
                arr[1]=rs.getString(2);
                arr[2]=rs.getString(3);
                arr[3]=String.valueOf(rs.getInt(4));
                arr[4]=rs.getString(5);
            }
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }
}
