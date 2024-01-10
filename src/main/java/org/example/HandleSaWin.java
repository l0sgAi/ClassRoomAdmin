package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HandleSaWin implements ActionListener {
    private SaWin Win;
    int RowNumber;
    Sa sa = new Sa();
    int rowNumber;//选中表格的行号

    public void setWin(SaWin Win) {
        this.Win = Win;
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        //获取按钮名
        String btnName = clickedButton.getText();
        if (btnName.equals("同意学生申请")) {
            try {
                rowNumber = Win.table1.getSelectedRow();
                int getNo = Integer.parseInt(Win.table1.getValueAt(rowNumber, 0).toString());
                String getRowName = Win.table1.getValueAt(rowNumber, 3).toString();
                int getRoomNo = Integer.parseInt(Win.table1.getValueAt(rowNumber, 4).toString());
                String getlsno = Win.table1.getValueAt(rowNumber, 1).toString();
                sa.upDateApplyStu(getlsno, getNo, getRowName, getRoomNo, 1);
                if (sa.agreeCheck) {
                    Win.table1.setModel(sa.tabModelOut);
                    Win.table1.setEnabled(true);
                    JOptionPane.showMessageDialog(Win, "同意申请操作成功!",
                            "管理结果", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Win, "此教室已经有一个成功的使用申请!!",
                            "管理结果", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!您可能未选中任何行!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("同意教师申请")) {
            try {
                rowNumber = Win.table2.getSelectedRow();
                int getNo = Integer.parseInt(Win.table2.getValueAt(rowNumber, 0).toString());
                String getRowName = Win.table2.getValueAt(rowNumber, 3).toString();
                int getRoomNo = Integer.parseInt(Win.table2.getValueAt(rowNumber, 4).toString());
                String getltno = Win.table2.getValueAt(rowNumber, 1).toString();
                sa.upDateApplyTea(getltno, getNo, getRowName, getRoomNo, 1);
                if (sa.agreeCheck) {
                    Win.table2.setModel(sa.tabModelOut);
                    Win.table2.setEnabled(true);
                    JOptionPane.showMessageDialog(Win, "同意申请操作成功!",
                            "管理结果", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Win, "此教室已经有一个成功的使用申请!!",
                            "管理结果", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!您可能未选中任何行!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("拒绝学生申请")) {
            try {
                rowNumber = Win.table1.getSelectedRow();
                int getNo = Integer.parseInt(Win.table1.getValueAt(rowNumber, 0).toString());
                String getRowName = Win.table1.getValueAt(rowNumber, 3).toString();
                int getRoomNo = Integer.parseInt(Win.table1.getValueAt(rowNumber, 4).toString());
                String getlsno = Win.table1.getValueAt(rowNumber, 1).toString();
                sa.upDateApplyStu(getlsno, getNo, getRowName, getRoomNo, 0);
                Win.table1.setModel(sa.tabModelOut);
                Win.table1.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "拒绝申请操作成功!",
                        "管理结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!您可能未选中任何行!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("拒绝教师申请")) {
            try {
                rowNumber = Win.table2.getSelectedRow();
                int getNo = Integer.parseInt(Win.table2.getValueAt(rowNumber, 0).toString());
                String getRowName = Win.table2.getValueAt(rowNumber, 3).toString();
                int getRoomNo = Integer.parseInt(Win.table2.getValueAt(rowNumber, 4).toString());
                String getltno = Win.table2.getValueAt(rowNumber, 1).toString();
                sa.upDateApplyTea(getltno, getNo, getRowName, getRoomNo, 0);
                Win.table2.setModel(sa.tabModelOut);
                Win.table2.setEnabled(true);
                JOptionPane.showMessageDialog(Win, "拒绝申请操作成功!",
                        "管理结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!您可能未选中任何行!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("删除学生申请")) {
            try {

                JOptionPane.showMessageDialog(Win, "删除申请操作成功!",
                        "管理结果", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else if (btnName.equals("删除教师申请")) {
            try {

                JOptionPane.showMessageDialog(Win, "删除申请操作成功!",
                        "管理结果", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(Win, "查询失败!",
                        "查询结果", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(Win, "未知选项！",
                    "操作结果", JOptionPane.WARNING_MESSAGE);
        }

    }
    void showApplyData() {//显示初始的申请数据
        sa.showStudentApply();
        Win.table1.setModel(sa.tabModelOut);
        Win.table1.setEnabled(true);
        sa.showTeacherApply();
        Win.table2.setModel(sa.tabModelOut);
        Win.table2.setEnabled(true);
    }
}

