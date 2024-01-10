package org.example;

import java.sql.SQLException;

public class Login {//确定登录角色，连接对应数据库
    static String loginNo = "null";
    int loginType=-1;//确认登录类型，-1未指定、0学生、1老师、2管理员
    public void loginCheck() throws SQLException {//根据登录类型进行登录检查
        if(loginType==0){
            try {
                DBConnection.ChangeConnection("students","123456");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(loginType==1){
            try{
                DBConnection.ChangeConnection("teachers","123456");
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(loginType==2){
            try{
                DBConnection.ChangeConnection("db2ads","123456");
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
