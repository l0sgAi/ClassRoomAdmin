package org.example;
import java.awt.*;
import javax.swing.*;
public class LoginWin extends JFrame {
    JFrame loginframe = new JFrame();
    //创建登录单选按钮
    JRadioButton radioButtonStu = new JRadioButton("学生");
    JRadioButton radioButtonTea = new JRadioButton("教师");
    JRadioButton radioButtonAd = new JRadioButton("管理员");
    //实例化FlowLayout流式布局类的对象，指定对齐方式为居中对齐组件之间的间隔为10个像素
    FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
    JLabel labname = new JLabel("账号：");
    JLabel checktip = new JLabel("选择您的登录角色：");
    //创建按钮组ButtonGroup，以此来实现单选
    ButtonGroup bg = new ButtonGroup();
    //实例化JTextField标签对象化
    JTextField text_name = new JTextField();
    Dimension dim1 = new Dimension(300, 30);
    JLabel labpass = new JLabel("密码：");
    //实例化JPasswordField
    JPasswordField text_password = new JPasswordField();
    JButton loginbtn = new JButton();
    //设置按键的显示内容
    Dimension dim2 = new Dimension(100, 30);
    JLabel tips = new JLabel("账号为学号/工号，管理员账号为SA;初始密码为学号工号+123，管理员密码为555555");
    Font tipfont = new Font("宋体", Font.PLAIN, 10);
    LoginWin() {
        //添加单选按钮
        bg.add(radioButtonStu);
        bg.add(radioButtonTea);
        bg.add(radioButtonAd);
        labname.setFont(new Font("宋体", Font.PLAIN, 14));
        text_name.setPreferredSize(dim1);//设置除顶级容器组件以外其他组件的大小
        //实例化JLabel标签对象，该对象显示“密码”
        //设置大小
        labpass.setFont(new Font("宋体", Font.PLAIN, 14));
        text_password.setPreferredSize(dim1);
        //实例化JButton组件
        loginbtn.setText("登录");
        loginbtn.setFont(new Font("宋体", Font.PLAIN, 14));
        //设置按键大小
        loginbtn.setSize(dim2);
        //设置输入提示
        tips.setFont(tipfont);
        tips.setForeground(Color.red);
        //设置窗体对象的属性值
        loginframe.setTitle("登录数据库系统");//设置窗体标题
        loginframe.setSize(400, 250);//设置窗体大小，只对顶层容器生效
        loginframe.setDefaultCloseOperation(3);//设置窗体关闭操作，3表示关闭窗体退出程序
        loginframe.setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        loginframe.setResizable(false);//禁止调整窗体大小
        loginframe.setFont(new Font("宋体", Font.PLAIN, 14));//设置字体，显示格式正常，大小
        //实例化流式布局类的对象
        loginframe.setLayout(fl);
        //实例化JLabel标签对象，该对象显示“账号”
        //将labname标签添加到窗体上
        loginframe.add(labname);
        //将textName标签添加到窗体上
        loginframe.add(text_name);
        //将labpass添加到窗体上
        loginframe.add(labpass);
        //将单选框添加到窗体上
        loginframe.add(text_password);
        loginframe.add(checktip);
        loginframe.add(radioButtonStu);
        loginframe.add(radioButtonTea);
        loginframe.add(radioButtonAd);
        loginframe.add(loginbtn);
        HandleLogin handleLogin = new HandleLogin();
        handleLogin.setWin(this);
        loginbtn.addActionListener(handleLogin);
        loginframe.add(tips);
        //添加到窗体
        loginframe.setVisible(true);//窗体可见，一定要放在所有组件加入窗体后
    }
}
