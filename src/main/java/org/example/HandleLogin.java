package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HandleLogin implements ActionListener {
    LoginWin Win;
    Login login = new Login();

    public void setWin(LoginWin Win) {
        this.Win = Win;
    }

    public void actionPerformed(ActionEvent e) {
        if (Win.radioButtonStu.isSelected()) {
            Statement stuSt = null;
            try {
                stuSt = DBConnection.Connection.createStatement();
                String sql = "select * from Student where sno=\'" + Win.text_name.getText() + "\'";//查询账号是否为数据库中的学号
                // 执行查询，把查询的结果赋值给结果集对象
                ResultSet rs = stuSt.executeQuery(sql);
                if (rs.next()) {//账号存在
                    if (Win.text_password.getText().equals(Win.text_name.getText() + "123")) {//检查密码
                        login.loginType = 0;
                        login.loginCheck();
                        Login.loginNo=Win.text_name.getText();
                        JOptionPane.showMessageDialog(Win, "登录成功!你选择的登录角色是：学生",
                                "登录结果", JOptionPane.INFORMATION_MESSAGE);
                        Win.loginframe.setVisible(false);
                        StudentWin stuwin=new StudentWin();
                    } else {
                        JOptionPane.showMessageDialog(Win, "密码错误！",
                                "登录结果", JOptionPane.WARNING_MESSAGE);
                    }
                } else {//账号不存在
                    JOptionPane.showMessageDialog(Win, "账号" + Win.text_name.getText() + "不存在！",
                            "登录结果", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(Win, "登录失败，连接超时。",
                        "登录结果", JOptionPane.WARNING_MESSAGE);
                throw new RuntimeException(ex);
            }
        } else if (Win.radioButtonTea.isSelected()) {
            Statement stuSt = null;
            try {
                stuSt = DBConnection.Connection.createStatement();
                String sql = "select * from Teacher where tno=\'" + Win.text_name.getText() + "\'";//查询账号是否为数据库中的学号
                // 执行查询，把查询的结果赋值给结果集对象
                ResultSet rs = stuSt.executeQuery(sql);
                if (rs.next()) {//账号存在
                    if (Win.text_password.getText().equals(Win.text_name.getText() + "123")) {//检查密码
                        login.loginType = 1;
                        login.loginCheck();
                        Login.loginNo=Win.text_name.getText();
                        JOptionPane.showMessageDialog(Win, "登录成功!你选择的登录角色是：老师",
                                "登录结果", JOptionPane.INFORMATION_MESSAGE);
                        Win.loginframe.setVisible(false);
                        TeacherWin teacherWin=new TeacherWin();
                    } else {
                        JOptionPane.showMessageDialog(Win, "密码错误！",
                                "登录结果", JOptionPane.WARNING_MESSAGE);
                    }
                } else {//账号不存在
                    JOptionPane.showMessageDialog(Win, "账号" + Win.text_name.getText() + "不存在！",
                            "登录结果", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(Win, "登录失败，连接超时。",
                        "登录结果", JOptionPane.WARNING_MESSAGE);
                throw new RuntimeException(ex);
            }
        } else if (Win.radioButtonAd.isSelected()) {
            try {
                if (Win.text_name.getText().equals("SA")) {//账号存在
                    if (Win.text_password.getText().equals("555555")) {//检查密码
                        login.loginType = 2;
                        login.loginCheck();
                        Login.loginNo=Win.text_name.getText();
                        JOptionPane.showMessageDialog(Win, "登录成功!你选择的登录角色是：管理员",
                                "登录结果", JOptionPane.INFORMATION_MESSAGE);
                        Win.loginframe.setVisible(false);
                        SaWin saWin=new SaWin();
                    } else {
                        JOptionPane.showMessageDialog(Win, "密码错误！",
                                "登录结果", JOptionPane.WARNING_MESSAGE);
                    }
                } else {//账号不存在
                    JOptionPane.showMessageDialog(Win, "账号" + Win.text_name.getText() + "不存在！",
                            "登录结果", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(Win, "登录失败，连接超时。",
                        "登录结果", JOptionPane.WARNING_MESSAGE);
                throw new RuntimeException(ex);
            }
        } else {
            JOptionPane.showMessageDialog(Win, "请先选择登录角色!",
                    "登录结果", JOptionPane.WARNING_MESSAGE);
        }
    }
}

