package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HandleStuWin implements ActionListener {
    private StudentWin Win;
    private String btnName;//获取按钮名
    private JButton clickedButton;

    public void setWin(StudentWin Win) {
        this.Win = Win;
    }

    Student student = new Student();
    public void actionPerformed(ActionEvent e) {
        clickedButton = (JButton) e.getSource();
        btnName = clickedButton.getText();
        if (btnName.equals("全部显示")) {
            try {
                student.getGradeData();
                Win.table1.setModel(student.tabModel);
                Win.table1.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("按课程名模糊查询")) {
            try {
                student.sByClassName(Win.Name.getText().trim());
                Win.table1.setModel(student.tabModel);
                Win.table1.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("按教师名模糊查询")) {
            try {
                student.sByTeaName(Win.Teacher.getText().trim());
                Win.table1.setModel(student.tabModel);
                Win.table1.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("申请空教室")) {
            try {
                if (Win.radioSJ.isSelected()){
                    student.checkApply(Win.BuildingNumber.getText().trim(),"三江楼");
                    Win.table2.setModel(student.tabModel);
                    Win.table2.setEnabled(true);
                    if (student.pFlag == 0) {
                        if (student.exist && student.single && student.statecheck) {
                            JOptionPane.showMessageDialog(Win, "申请成功!",
                                    "申请结果", JOptionPane.INFORMATION_MESSAGE);
                        } else if (!student.exist) {
                            JOptionPane.showMessageDialog(Win, "不存在这个教室!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        } else if (!student.single) {
                            JOptionPane.showMessageDialog(Win, "重复的申请!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(Win, "该教室已经被预约!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(Win, "每名学生只能有一个申请!",
                                "申请结果", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else if (Win.radioSS.isSelected()){
                    student.checkApply(Win.BuildingNumber.getText().trim(),"三山楼");
                    Win.table2.setModel(student.tabModel);
                    Win.table2.setEnabled(true);
                    if (student.pFlag == 0) {
                        if (student.exist && student.single && student.statecheck) {
                            JOptionPane.showMessageDialog(Win, "申请成功!",
                                    "申请结果", JOptionPane.INFORMATION_MESSAGE);
                        } else if (!student.exist) {
                            JOptionPane.showMessageDialog(Win, "不存在这个教室!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        } else if (!student.single) {
                            JOptionPane.showMessageDialog(Win, "重复的申请!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(Win, "该教室已经被预约!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(Win, "每名学生只能有一个申请!",
                                "申请结果", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else if (Win.radioJT.isSelected()) {
                    student.checkApply(Win.BuildingNumber.getText().trim(), "讲堂群");
                    Win.table2.setModel(student.tabModel);
                    Win.table2.setEnabled(true);
                    if (student.pFlag == 0) {
                        if (student.exist && student.single && student.statecheck) {
                            JOptionPane.showMessageDialog(Win, "申请成功!",
                                    "申请结果", JOptionPane.INFORMATION_MESSAGE);
                        } else if (!student.exist) {
                            JOptionPane.showMessageDialog(Win, "不存在这个教室!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        } else if (!student.single) {
                            JOptionPane.showMessageDialog(Win, "重复的申请!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(Win, "该教室已经被预约!",
                                    "申请结果", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(Win, "每名学生只能有一个申请!",
                                "申请结果", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(Win, "请选择楼名!",
                            "申请结果", JOptionPane.WARNING_MESSAGE);
                }

            }catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(Win, "申请失败!",
                    "申请结果", JOptionPane.WARNING_MESSAGE);
        }
        } else if (btnName.equals("显示申请记录")) {
            try {
                student.showApplyStu();
                Win.table2.setModel(student.tabModel);
                Win.table2.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("撤销申请")) {
            try {
                student.delApplyStu();
                Win.table2.setModel(student.tabModel);
                Win.table2.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "清除成功!",
                        "删除结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "清除失败!您可能还未提交申请！",
                        "删除结果", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (btnName.equals("显示所有空教室")) {
            try {
                student.showEmptyRooms();
                Win.table2.setModel(student.tabModel);
                Win.table2.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!可能是数据库连接错误。",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (btnName.equals("课程名模糊查询")) {
            try {
                student.classQueueByName(Win.courseName.getText().trim());
                Win.table3.setModel(student.tabModel);
                Win.table3.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("教师名模糊查询")) {
            try {
                student.classQueueByTea(Win.courseTeacher.getText().trim());
                Win.table3.setModel(student.tabModel);
                Win.table3.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (btnName.equals("显示全部课程信息")) {
            try {
                student.showClassInfo();
                Win.table3.setModel(student.tabModel);
                Win.table3.setEnabled(true);
                Win.table3.setEnabled(false);
                JOptionPane.showMessageDialog(Win, "查询成功!",
                        "查询结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(Win, "未知选项！",
                    "操作结果", JOptionPane.WARNING_MESSAGE);
        }
    }
    void setWinInfo(){
        student.getStudentInfo();
        Win.jl_no.setText("学号:"+student.arr[0]);
        Win.jl_name.setText("姓名:"+student.arr[1]);
        Win.jl_class.setText("班级:"+student.arr[2]);
        Win.jl_age.setText("年龄:"+student.arr[3]);
        Win.jl_sex.setText("性别:"+student.arr[4]);
    }
}

