package org.example;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sa {
    private String sql_exec;//sql语句
    DefaultTableModel tabModelOut = new DefaultTableModel();//输出的表格内容
    boolean agreeCheck = false;
    void showStudentApply() {//显示学生申请
        Statement sta = null;//新建更新
        ResultSet rs = null;
        DefaultTableModel tabModel = new DefaultTableModel();
        //@TEST_METHOD
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select ano,asno,sname,arname,arno,astate \n" +
                    "from (Apply inner join Student on Student.sno=asno) ";
            // 更新表格内容
            rs = sta.executeQuery(sql_exec);
            tabModel.addColumn("申请序号");
            tabModel.addColumn("申请人学号");
            tabModel.addColumn("申请人姓名");
            tabModel.addColumn("申请楼名");
            tabModel.addColumn("申请教室号");
            tabModel.addColumn("受理状态");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel.addRow(row);
            }
            tabModelOut = tabModel;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }

    void showTeacherApply() {//显示教师申请
        Statement sta = null;//新建更新
        ResultSet rs = null;
        DefaultTableModel tabModel = new DefaultTableModel();
        //@TEST_METHOD
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select ano,atno,tname,arname,arno,astate \n" +
                    "from (Apply inner join Teacher on Teacher.tno=atno) ";
            // 更新表格内容
            rs = sta.executeQuery(sql_exec);
            tabModel.addColumn("申请序号");
            tabModel.addColumn("申请人工号");
            tabModel.addColumn("申请人姓名");
            tabModel.addColumn("申请楼名");
            tabModel.addColumn("申请教室号");
            tabModel.addColumn("受理状态");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel.addRow(row);
            }
            tabModelOut = tabModel;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }

    void upDateApplyStu(String lsno, int no, String roomName, int roomNo, int index) {//更新学生申请信息，1为同意，其它拒绝
        Statement sta = null;//新建更新
        ResultSet rs = null;//返回结果集
        DefaultTableModel tabModel = new DefaultTableModel();
        //@TEST_METHOD
        try {
            sta = DBConnection.Connection.createStatement();
            if (index == 1) {//同意申请
                sql_exec = "use DB2;\n" +
                        "select * from Apply where arno=" + roomNo + " and arname=\'" + roomName + "\'" + " and  astate='申请通过'";
                // 检查是否有重复申请
                rs = sta.executeQuery(sql_exec);
                //检查通过的申请是否重复
                if (rs.next()) {
                    agreeCheck = false;
                } else {
                    agreeCheck = true;
                }
                if (agreeCheck) {
                    sql_exec = "use DB2;\n" +
                            "update Apply set astate=? where ano=?";
                    // 执行查询，把查询的结果赋值给结果集对象
                    PreparedStatement pre = DBConnection.Connection.prepareStatement(sql_exec);
                    //设置占位符值
                    pre.setString(1, "申请通过");
                    pre.setInt(2, no);
                    pre.executeUpdate();//执行更新
                    //对应教室信息更新
                    sql_exec = "use DB2;\n" +
                            "update Classroom set lsno=?,rstate=? where rno=? and rname=?";
                    //设置占位符值
                    pre = DBConnection.Connection.prepareStatement(sql_exec);
                    pre.setString(1, lsno);
                    pre.setString(2, "已预约");
                    pre.setInt(3, roomNo);
                    pre.setString(4, roomName);
                    pre.executeUpdate();//执行更新
                } else {
                    System.out.println("重复申请");
                }
            } else {//不同意申请
                sql_exec = "use DB2;\n" +
                        "select * from Apply where ano="+no+" and astate='申请通过'";
                // 检查不同意申请之前是否为通过申请，如果是，则需要保证数据的一致性，更新表Classroom中的数据
                rs = sta.executeQuery(sql_exec);
                if (rs.next()) {
                    sql_exec = "use DB2;\n" +
                            "update Classroom set lsno=?,rstate=? where rno=? and rname=?";
                    // 执行查询，把查询的结果赋值给结果集对象
                    PreparedStatement pre = DBConnection.Connection.prepareStatement(sql_exec);
                    //设置占位符值
                    pre.setString(1, null);
                    pre.setString(2, "未预约");
                    pre.setInt(3, roomNo);
                    pre.setString(4, roomName);
                    pre.executeUpdate();//执行更新
                }
                sql_exec = "use DB2;\n" +
                        "update Apply set astate=? where ano=?";
                // 执行查询，把查询的结果赋值给结果集对象
                PreparedStatement pre = DBConnection.Connection.prepareStatement(sql_exec);
                //设置占位符值
                pre.setString(1, "申请失败");
                pre.setInt(2, no);
                pre.executeUpdate();//执行更新
            }
            sql_exec = "use DB2;\n" +
                    "select ano,asno,sname,arname,arno,astate \n" +
                    "from (Apply inner join Student on Student.sno=asno) ";
            // 更新表格内容
            rs = sta.executeQuery(sql_exec);
            tabModel.addColumn("申请序号");
            tabModel.addColumn("申请人学号");
            tabModel.addColumn("申请人姓名");
            tabModel.addColumn("申请楼名");
            tabModel.addColumn("申请教室号");
            tabModel.addColumn("处理状态");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel.addRow(row);
            }
            tabModelOut = tabModel;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }

    void upDateApplyTea(String ltno, int no, String roomName, int roomNo, int index) {//更新学生申请信息，1为同意，其它拒绝
        Statement sta = null;//新建更新
        ResultSet rs = null;//返回结果集
        DefaultTableModel tabModel = new DefaultTableModel();
        //@TEST_METHOD
        try {
            sta = DBConnection.Connection.createStatement();
            if (index == 1) {//同意申请
                sql_exec = "use DB2;\n" +
                        "select * from Apply where arno=" + roomNo + " and arname=\'" + roomName + "\'" + " and  astate='申请通过'";
                // 检查是否有重复申请
                rs = sta.executeQuery(sql_exec);
                //检查通过的申请是否重复
                if (rs.next()) {
                    agreeCheck = false;
                } else {
                    agreeCheck = true;
                }
                if (agreeCheck) {
                    sql_exec = "use DB2;\n" +
                            "update Apply set astate=? where ano=?";
                    // 执行更新
                    PreparedStatement pre = DBConnection.Connection.prepareStatement(sql_exec);
                    //设置占位符值
                    pre.setString(1, "申请通过");
                    pre.setInt(2, no);
                    pre.executeUpdate();//执行更新
                    //对应教室信息更新
                    sql_exec = "use DB2;\n" +
                            "update Classroom set ltno=?,rstate=? where rno=? and rname=?";
                    //设置占位符值
                    pre = DBConnection.Connection.prepareStatement(sql_exec);
                    pre.setString(1, ltno);
                    pre.setString(2, "已预约");
                    pre.setInt(3, roomNo);
                    pre.setString(4, roomName);
                    pre.executeUpdate();//执行更新
                } else {
                    System.out.println("重复申请");
                }
            } else {//不同意申请
                sql_exec = "use DB2;\n" +
                        "select * from Apply where ano="+no+" and astate='申请通过'";
                // 检查不同意申请之前是否为通过申请，如果是，则需要保证数据的一致性，更新表Classroom中的数据
                rs = sta.executeQuery(sql_exec);
                if (rs.next()) {
                    sql_exec = "use DB2;\n" +
                            "update Classroom set ltno=?,rstate=? where rno=? and rname=?";
                    // 执行查询，把查询的结果赋值给结果集对象
                    PreparedStatement pre = DBConnection.Connection.prepareStatement(sql_exec);
                    //设置占位符值
                    pre.setString(1, null);
                    pre.setString(2, "未预约");
                    pre.setInt(3, roomNo);
                    pre.setString(4, roomName);
                    pre.executeUpdate();//执行更新
                }
                sql_exec = "use DB2;\n" +
                        "update Apply set astate=? where ano=?";
                // 执行更新
                PreparedStatement pre = DBConnection.Connection.prepareStatement(sql_exec);
                //设置占位符值
                pre.setString(1, "申请失败");
                pre.setInt(2, no);
                pre.executeUpdate();//执行更新
            }
            sql_exec = "use DB2;\n" +
                    "select ano,atno,tname,arname,arno,astate \n" +
                    "from (Apply inner join Teacher on Teacher.tno=atno) ";
            // 更新表格内容
            rs = sta.executeQuery(sql_exec);
            tabModel.addColumn("申请序号");
            tabModel.addColumn("申请人工号");
            tabModel.addColumn("申请人姓名");
            tabModel.addColumn("申请楼名");
            tabModel.addColumn("申请教室号");
            tabModel.addColumn("受理状态");
            // 将结果集数据添加到模型
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                tabModel.addRow(row);
            }
            tabModelOut = tabModel;
        } catch (Exception ee) {
            System.out.println("sql执行错误");
            ee.printStackTrace();
        }
    }
}
