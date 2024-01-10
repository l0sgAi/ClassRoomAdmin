package org.example;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Teacher {
    private String sql_exec;//sql语句
    DefaultTableModel tabModelOut = new DefaultTableModel();//输出的表格内容

    boolean single=false;//检查是否有重复申请
    boolean statecheck=false;//检查申请教室是否已经被申请
    int pFlag=0;//检查这个教师已经发出的申请数
    boolean exist=false;//检查教室是否存在
    String []arr = new String[]{"null","null","null","null","null"};//账号信息
    void getStuDataByName(String stuname) {//按姓名查询学生信息
        Statement showAll = null;//新建查询
        ResultSet rs;
        stuname = '%' + stuname + '%';
        DefaultTableModel tabModel = new DefaultTableModel();
        try {
            showAll = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select * from Student where sname like '" + stuname + "'" ;
            // 执行查询，把查询的结果赋值给结果集对象
            rs = showAll.executeQuery(sql_exec);
            tabModel.addColumn("学号");
            tabModel.addColumn("姓名");
            tabModel.addColumn("班级");
            tabModel.addColumn("年龄");
            tabModel.addColumn("性别");
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
    void getStuDataByNo(String stuno) {//按学号查询学生信息
        Statement showAll = null;//新建查询
        ResultSet rs;
        stuno = '%' + stuno + '%';
        DefaultTableModel tabModel = new DefaultTableModel();
        try {
            showAll = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select * from Student where sno like '" + stuno + "'" ;
            // 执行查询，把查询的结果赋值给结果集对象
            rs = showAll.executeQuery(sql_exec);
            tabModel.addColumn("学号");
            tabModel.addColumn("姓名");
            tabModel.addColumn("班级");
            tabModel.addColumn("年龄");
            tabModel.addColumn("性别");
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
    void getGradeDataByName(String stuname) {//按姓名查询学生成绩
        Statement showAll = null;//新建查询
        ResultSet rs;
        stuname = '%' + stuname + '%';
        DefaultTableModel tabModel = new DefaultTableModel();
        try {
            showAll = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select Student.sno,sname,sclass,Course.cno,cname,score from\n" +
                    "(Student inner join Grade on Student.sno=Grade.sno and Student.sname like '" + stuname + "'"+")\n" +
                    "inner join Course on Course.cno=Grade.cno" ;
            // 执行查询，把查询的结果赋值给结果集对象
            rs = showAll.executeQuery(sql_exec);
            tabModel.addColumn("学号");
            tabModel.addColumn("姓名");
            tabModel.addColumn("班级");
            tabModel.addColumn("课程号");
            tabModel.addColumn("课程名");
            tabModel.addColumn("分数");
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
    void getGradeDataByNo(String stuno) {//获取所有学生成绩
        Statement showAll = null;//新建查询
        ResultSet rs;
        stuno = '%' + stuno + '%';
        DefaultTableModel tabModel = new DefaultTableModel();
        try {
            showAll = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select Student.sno,sname,sclass,Course.cno,cname,score from\n" +
                    "(Student inner join Grade on Student.sno=Grade.sno and Student.sno like '" + stuno + "'"+")\n" +
                    "inner join Course on Course.cno=Grade.cno" ;
            // 执行查询，把查询的结果赋值给结果集对象
            rs = showAll.executeQuery(sql_exec);
            tabModel.addColumn("学号");
            tabModel.addColumn("姓名");
            tabModel.addColumn("班级");
            tabModel.addColumn("课程号");
            tabModel.addColumn("课程名");
            tabModel.addColumn("分数");
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
    void showAllGradeData() {//获取所有成绩信息
        Statement showAll = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel = new DefaultTableModel();
        try {
            showAll = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select Student.sno,sname,sclass,Course.cno,cname,score from\n" +
                    "(Student inner join Grade on Student.sno=Grade.sno)\n" +
                    "inner join Course on Course.cno=Grade.cno" ;
            // 执行查询，把查询的结果赋值给结果集对象
            rs = showAll.executeQuery(sql_exec);
            tabModel.addColumn("学号");
            tabModel.addColumn("姓名");
            tabModel.addColumn("班级");
            tabModel.addColumn("课程号");
            tabModel.addColumn("课程名");
            tabModel.addColumn("分数");
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
    void showAllStuData() {//获取所有学生信息
        Statement showAll = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel = new DefaultTableModel();
        try {
            showAll = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select * from Student" ;
            // 执行查询，把查询的结果赋值给结果集对象
            rs = showAll.executeQuery(sql_exec);
            tabModel.addColumn("学号");
            tabModel.addColumn("姓名");
            tabModel.addColumn("班级");
            tabModel.addColumn("年龄");
            tabModel.addColumn("性别");
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
    void getCourseData() {//获取教师所执教的课程信息
        Statement showAll = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel = new DefaultTableModel();
        try {
            showAll = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select cno,cname,ctime,clocation,Teacher.tno,tname from \n" +
                    "Course inner join Teacher on Course.tno=Teacher.tno and Course.tno='" + Login.loginNo.trim() + "'";
            // 执行查询，把查询的结果赋值给结果集对象
            rs = showAll.executeQuery(sql_exec);
            tabModel.addColumn("课程号");
            tabModel.addColumn("课程名");
            tabModel.addColumn("上课时间");
            tabModel.addColumn("上课地点");
            tabModel.addColumn("任课教师工号");
            tabModel.addColumn("任课教师姓名");
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
    void upDataCourseData(String no,String name,String time,String location) {//更新对应课程信息
        Statement showAll = null;//新建更新
        ResultSet rs = null;
        DefaultTableModel tabModel = new DefaultTableModel();
        exist=false;
        System.out.println("所选课程号:"+no);
        System.out.println("课程名:"+"|"+name+"|");
        System.out.println("课程时间:"+"|"+name+"|");
        System.out.println("课程地点:"+"|"+name+"|");
        System.out.println("楼名:"+"|"+location.substring(0,3)+"|");
        System.out.println("教室号:"+"|"+location.substring(3)+"|");
        //@TEST_METHOD
        try {
            showAll = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select * from Classroom where rname='" + location.substring(0,3) + "'"+
                    " and rno=" + location.substring(3);
            // 更新表格内容
            rs = showAll.executeQuery(sql_exec);
            if(rs.next()){
                exist=true;
            }
            else{
                exist=false;
            }
            if(exist){
                sql_exec = "use DB2;\n" +
                        "update Course set cname=?,ctime=?,clocation=? where cno=?";
                // 执行查询，把查询的结果赋值给结果集对象
                PreparedStatement pre=DBConnection.Connection.prepareStatement(sql_exec);
                //设置占位符值
                pre.setString(1, name);
                pre.setString(2, time);
                pre.setString(3, location);
                pre.setString(4, no);
                pre.executeUpdate();//执行更新
                sql_exec = "use DB2;\n" +
                        "select cno,cname,ctime,clocation,Teacher.tno,tname from \n" +
                        "Course inner join Teacher on Course.tno=Teacher.tno and Course.tno=\'" + Login.loginNo.trim() + "\'";
                // 更新表格内容
                rs = showAll.executeQuery(sql_exec);
                tabModel.addColumn("课程号");
                tabModel.addColumn("课程名");
                tabModel.addColumn("上课时间");
                tabModel.addColumn("上课地点");
                tabModel.addColumn("任课教师工号");
                tabModel.addColumn("任课教师姓名");
                // 将结果集数据添加到模型
                while (rs.next()) {
                    Object[] row = new Object[rs.getMetaData().getColumnCount()];
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    tabModel.addRow(row);
                }
                tabModelOut = tabModel;
            }
            else{
                System.out.println("非法课程地点");
            }
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
                    "select * from Apply where atno=\'" + Login.loginNo.trim() + "\'" +
                    " and arno=\'" + no + "\'" + " and arname=\'" + name + "\'";
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
                    "select count(atno) from Apply where atno=\'" + Login.loginNo.trim() + "\'";//统计教师申请数量
            // 检查是这个教师的申请数量
            rs = sta.executeQuery(sql_exec);
            if (rs.next()) {
                pFlag=rs.getInt(1);
                System.out.println("申请数"+rs.getInt(1));
            }
            sql_exec = "use DB2;\n" +
                    "select * from Classroom where rno=\'" + no + "\'" +
                    " and rname=\'" + name + "\'" + " and rstate=\'" + "已预约" + "\'";
            // 检查教室是否已预约
            rs = sta.executeQuery(sql_exec);
            statecheck=false;
            if (rs.next()) {
                statecheck=false;
            }
            else{
                statecheck=true;
            }
            if (statecheck&&single&&pFlag<3) {
                applyRoomTea(no, name);
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
    void applyRoomTea(String no, String name) {//教师申请
        Statement sta = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel = new DefaultTableModel();
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "insert into Apply (atno,arno,arname,astate) values(?,?,?,?)";
            // 执行插入
            PreparedStatement pre = DBConnection.Connection.prepareStatement(sql_exec);
            pre.setString(1, Login.loginNo.trim());
            pre.setInt(2, Integer.parseInt(no));
            pre.setString(3, name);
            pre.setString(4, "待处理");
            pre.executeUpdate();//执行插入

            sql_exec = "use DB2;\n" +
                    "select tno,tname,tpost,arname,arno,astate from Teacher\n" +
                    "inner join Apply on Apply.atno=tno and tno=\'" + Login.loginNo.trim() + "\'";
            rs = sta.executeQuery(sql_exec);//返回申请信息
            tabModel.addColumn("申请人工号");
            tabModel.addColumn("申请人姓名");
            tabModel.addColumn("申请人职称");
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

    void showApplyTea() {//显示教师申请
        Statement sta = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel = new DefaultTableModel();
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select tno,tname,tpost,arname,arno,astate from Teacher\n" +
                    "inner join Apply on Apply.atno=tno and tno=\'" + Login.loginNo.trim() + "\'";
            rs = sta.executeQuery(sql_exec);
            tabModel.addColumn("申请人工号");
            tabModel.addColumn("申请人姓名");
            tabModel.addColumn("申请人职称");
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

    void delApplyTea(String name,int no) {//删除教师申请
        Statement sta = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel = new DefaultTableModel();
        try {
            sta = DBConnection.Connection.createStatement();
            PreparedStatement pre = null;
            sql_exec = "use DB2;\n" +
                    "select * from Apply where astate='申请通过' and atno=\'" + Login.loginNo.trim() + "\'"+
            " and arname=\'"+name+"\'"+" and arno="+no;
            //检查清除的申请是否为通过的申请
            rs = sta.executeQuery(sql_exec);
            if(rs.next()) {//如是，更新对应申请教室的信息
                sql_exec = "use DB2;\n" +
                        "update Classroom set ltno=?,rstate=? where ltno=?";//更新记录
                pre=DBConnection.Connection.prepareStatement(sql_exec);
                pre.setString(1,null);
                pre.setString(2,"未预约");
                pre.setString(3,Login.loginNo.trim());
                pre.executeUpdate();//执行更新
            }
            sql_exec = "use DB2;\n" +
                    "delete from Apply where atno=? and arname=? and arno=?";//删除选中记录
            pre=DBConnection.Connection.prepareStatement(sql_exec);
            pre.setString(1,Login.loginNo.trim());
            pre.setString(2,name);
            pre.setInt(3,no);
            pre.executeUpdate();//执行删除
            sql_exec = "use DB2;\n" +
                    "select tno,tname,tpost,arname,arno,astate from Teacher\n" +
                    "inner join Apply on Apply.atno=tno and tno=\'" + Login.loginNo.trim() + "\'";
            rs = sta.executeQuery(sql_exec);
            tabModel.addColumn("申请人工号");
            tabModel.addColumn("申请人姓名");
            tabModel.addColumn("申请人职称");
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
    void showClassRoomData() {//获取所有教室信息
        Statement sta = null;//新建查询
        ResultSet rs;
        DefaultTableModel tabModel = new DefaultTableModel();
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "use DB2;\n" +
                    "select rname,rno,rstate from Classroom";
            rs = sta.executeQuery(sql_exec);
            tabModel.addColumn("教室楼号");
            tabModel.addColumn("教室楼名");
            tabModel.addColumn("教室申请状态");
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
    void getTeacherInfo() {//获取账号信息
        Statement sta = null;//新建查询
        ResultSet rs;
        try {
            sta = DBConnection.Connection.createStatement();
            sql_exec = "select * from Teacher where tno=\'" + Login.loginNo.trim() + "\'";
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
