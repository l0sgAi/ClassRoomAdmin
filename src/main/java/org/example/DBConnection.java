package org.example;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection Connection = null;
   public static void ConnectDBV() {//访客模式连接数据库
        String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=DB2;encrypt=false";//这里输入自己的数据库名称（即将Stu改为你自己的数据库名称）其余都可以不做修改
        try {
            //1.加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");
            //2.连接
            Connection = DriverManager.getConnection(dbURL, "visitors", "123456");//这里的密码改为第一步你所修改的密码，用户名一般就为"sa"
            System.out.println("连接数据库成功！");
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("加载驱动失败！");
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }
    }
    public static void ChangeConnection(String usr,String pwd) throws SQLException {//更改访问登录名
        Connection.close();
        String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=DB2;encrypt=false";//这里输入自己的数据库名称（即将Stu改为你自己的数据库名称）其余都可以不做修改
        try {
            //1.加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");
            //2.连接
            Connection = DriverManager.getConnection(dbURL, usr, pwd);//这里的密码改为第一步你所修改的密码，用户名一般就为"sa"
            System.out.println("连接数据库成功！");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("加载驱动失败！");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }
    }
}
