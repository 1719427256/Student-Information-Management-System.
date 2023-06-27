import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginExample {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DatabaseExample databaseExample;

    public void showLoginWindow() {
        frame.setVisible(true);
    }



    public LoginExample() {
        frame = new JFrame("登录");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);

        // 创建文本框和标签
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        JLabel usernameLabel = new JLabel("用户名：");
        JLabel passwordLabel = new JLabel("密码：");

        // 创建按钮
        JButton loginButton = new JButton("登录");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // 添加组件到窗口
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.databaseExample = databaseExample;
        //frame.dispose();
        //databaseExample = new DatabaseExample();
        //

        //在"LoginExample"类的构造函数调用中传递一个"DatabaseExample"对象作为参数
        //this.databaseExample = databaseExample;

    }

    private void login() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/学校信息管理系统";
        String dbUsername = "root";
        String dbPassword = "12345678";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM user_info WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            // 从文本框中获取用户名和密码
            String inputUsername = usernameField.getText();
            String inputPassword = new String(passwordField.getPassword());

            // 设置参数值
            statement.setString(1, inputUsername);
            statement.setString(2, inputPassword);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String identity = resultSet.getString("identity");
                JOptionPane.showMessageDialog(frame, "登录成功！身份：" + identity);

                frame.dispose(); // 关闭登录窗口
                if (identity.equals("admin")){
                    databaseExample = new DatabaseExample();
                    databaseExample.showWindow();
                }
                else {
                    UserWindow userWindow = new UserWindow();
                    userWindow.showWindow();
                }
                //databaseExample = new DatabaseExample();// 创建DatabaseExample窗口
                //databaseExample.showWindow();


            } else {
                JOptionPane.showMessageDialog(frame, "登录失败！请检查用户名和密码。");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "数据库连接错误！");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DatabaseExample databaseExample = new DatabaseExample();
                new LoginExample();
            }
        });
    }
}
