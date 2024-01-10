package org.example;
public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //在主函数中，实例化Login类的对象，调用初始化界面的方法
        DBConnection.ConnectDBV();
        LoginWin loginWin =new LoginWin();
    }
}
