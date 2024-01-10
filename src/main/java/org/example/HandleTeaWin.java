package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HandleTeaWin implements ActionListener {
    private TeacherWin Win;
    Teacher teacher=new Teacher();
    int rowNumber;//选中表格的行号
    public void setWin(TeacherWin Win) {
        this.Win = Win;
    }
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        //获取按钮名
        String btnName = clickedButton.getText();
        if (btnName.equals("通过名字搜索学生信息")) {
            try {
                teacher.getStuDataByName(Win.stuName.getText().trim());
                Win.table1.setModel(teacher.tabModelOut);
                Win.table1.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("通过名字搜索成绩信息")) {
            try {
                teacher.getGradeDataByName(Win.stuName.getText().trim());
                Win.table1.setModel(teacher.tabModelOut);
                Win.table1.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("通过学号搜索学生信息")) {
            try {
                teacher.getStuDataByNo(Win.stuNo.getText().trim());
                Win.table1.setModel(teacher.tabModelOut);
                Win.table1.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("通过学号搜索成绩信息")) {
            try {
                teacher.getGradeDataByNo(Win.stuNo.getText().trim());
                Win.table1.setModel(teacher.tabModelOut);
                Win.table1.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "申请失败!",
                        "申请结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("显示所有学生信息")) {
            try {
                teacher.showAllStuData();
                Win.table1.setModel(teacher.tabModelOut);
                Win.table1.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("显示所有成绩信息")) {
            try {
                teacher.showAllGradeData();
                Win.table1.setModel(teacher.tabModelOut);
                Win.table1.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "执行错误！",
                        "显示结果", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (btnName.equals("更新所选课程信息")) {
            try {
                rowNumber=Win.table2.getSelectedRow();//选取的行号
                String selectedNo= Win.table2.getValueAt(rowNumber,0).toString();
                String inputName=Win.courseName.getText().trim();
                String inputTime=Win.courseTime.getText().trim();
                String inputLocation=Win.courseLocation.getText().trim();//输入函数的值
                inputName=inputProcess(inputName,1);
                inputTime=inputProcess(inputTime,2);
                inputLocation=inputProcess(inputLocation,3);
                teacher.upDataCourseData(selectedNo,inputName,inputTime,inputLocation);
                if(teacher.exist){
                    Win.table2.setModel(teacher.tabModelOut);
                    Win.table2.setEnabled(true);
                    JOptionPane.showMessageDialog(Win, "更新成功!",
                            "更新结果", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(Win, "非法的上课地点!",
                            "更新结果", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "更新失败!可能是您未选中右侧表格栏",
                        "更新结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("申请空教室")) {
            try {
                if (Win.radioSJ.isSelected()){
                    teacher.checkApply(Win.BuildingNumber.getText().trim(),"三江楼");
                    Win.table3.setModel(teacher.tabModelOut);
                    Win.table3.setEnabled(true);
                    if(teacher.pFlag<3){
                        if(teacher.exist&&teacher.single&&teacher.statecheck){
                            JOptionPane.showMessageDialog(Win, "申请成功!",
                                    "申请结果", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(!teacher.exist){
                            JOptionPane.showMessageDialog(Win, "不存在这个教室!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                        else if(!teacher.single){
                            JOptionPane.showMessageDialog(Win, "重复的申请!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(Win, "该教室已经被预约!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(Win, "每名教师最多只能有三个申请!",
                                "申请结果", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else if (Win.radioSS.isSelected()){
                    teacher.checkApply(Win.BuildingNumber.getText().trim(),"三山楼");
                    Win.table3.setModel(teacher.tabModelOut);
                    Win.table3.setEnabled(true);
                    if(teacher.pFlag<3){
                        if(teacher.exist&&teacher.single&&teacher.statecheck){
                            JOptionPane.showMessageDialog(Win, "申请成功!",
                                    "申请结果", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(!teacher.exist){
                            JOptionPane.showMessageDialog(Win, "不存在这个教室!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                        else if(!teacher.single){
                            JOptionPane.showMessageDialog(Win, "重复的申请!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(Win, "该教室已经被预约!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(Win, "每名教师最多只能有三个申请!",
                                "申请结果", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else if (Win.radioJT.isSelected()) {
                    teacher.checkApply(Win.BuildingNumber.getText().trim(), "讲堂群");
                    Win.table3.setModel(teacher.tabModelOut);
                    Win.table3.setEnabled(true);
                    if(teacher.pFlag<3){
                        if(teacher.exist&&teacher.single&&teacher.statecheck){
                            JOptionPane.showMessageDialog(Win, "申请成功!",
                                    "申请结果", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(!teacher.exist){
                            JOptionPane.showMessageDialog(Win, "不存在这个教室!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                        else if(!teacher.single){
                            JOptionPane.showMessageDialog(Win, "重复的申请!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(Win, "该教室已经被预约!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(Win, "每名教师最多只能有三个申请!",
                                "申请结果", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(Win, "请选择楼名!",
                            "申请结果", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println("Error: " + e1.getMessage());
            }
        }
        else if (btnName.equals("显示申请记录")) {
            try {
                teacher.showApplyTea();
                Win.table3.setModel(teacher.tabModelOut);
                Win.table3.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (btnName.equals("撤销选中的申请记录")) {
            try {
                rowNumber=Win.table3.getSelectedRow();
                String getName=Win.table3.getValueAt(rowNumber,3).toString();
                int getNo=Integer.parseInt(Win.table3.getValueAt(rowNumber,4).toString());
                teacher.delApplyTea(getName,getNo);
                Win.table3.setModel(teacher.tabModelOut);
                Win.table3.setEnabled(true);
                Win.table3.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "清除成功!",
                        "删除结果", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (btnName.equals("显示所有空教室")) {
            try {
                teacher.showClassRoomData();
                Win.table3.setModel(teacher.tabModelOut);
                Win.table3.setEnabled(true);
                Win.table3.setEnabled(false);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else{
            JOptionPane.showMessageDialog(Win, "未知选项！",
                    "操作结果", JOptionPane.WARNING_MESSAGE);
        }
    }
    void setWinInfo(){//显示教师个人信息
        teacher.getTeacherInfo();
        Win.jl_no.setText("工号:"+teacher.arr[0]);
        Win.jl_name.setText("姓名:"+teacher.arr[1]);
        Win.jl_post.setText("职称:"+teacher.arr[2]);
        Win.jl_age.setText("年龄:"+teacher.arr[3]);
        Win.jl_sex.setText("性别:"+teacher.arr[4]);
    }
    void showCourseData(){//显示初始的课程数据
        teacher.getCourseData();
        Win.table2.setModel(teacher.tabModelOut);
        Win.table2.setEnabled(true);
    }
    String inputProcess(String str,int index){//对更新操作的空数据进行处理
        if(str.isEmpty()){
            String result=Win.table2.getValueAt(rowNumber,index).toString();
            return result;
        }
        else{
            return str;
        }
    }
}

