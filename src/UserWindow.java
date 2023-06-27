import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserWindow {
    private JFrame frame;
    private JTextField studentNumberField;
    private JTextArea resultArea;

    public void showWindow() {
        frame.setVisible(true);
    }

    public UserWindow() {
        frame = new JFrame("用户界面");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);

        // 创建标签、文本框和按钮
        JLabel userLabel = new JLabel("user界面");
        JLabel studentNumberLabel = new JLabel("学号：");
        studentNumberField = new JTextField(10);
        JButton queryButton = new JButton("查询");
        resultArea = new JTextArea(15, 20);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // 添加组件到窗口
        frame.add(userLabel);
        frame.add(studentNumberLabel);
        frame.add(studentNumberField);
        frame.add(queryButton);
        frame.add(scrollPane);

        // 添加查询按钮的点击事件处理
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                query();
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void query() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/学校信息管理系统";
        String dbUsername = "root";
        String dbPassword = "12345678";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM student_info WHERE stu_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            // 从文本框中获取学号
            String studentNumber = studentNumberField.getText();

            // 设置参数值
            statement.setString(1, studentNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("stu_name");
                String gender = resultSet.getString("gender");
                String native_place = resultSet.getString("native_place");
                String birth = resultSet.getString("birth");
                String dept = resultSet.getString("dept_no");
                String major = resultSet.getString("major_code");
                String class_no = resultSet.getString("class_no");
                String admission = resultSet.getString("admission_time");
                String home = resultSet.getString("home_address");
                String phone1 = resultSet.getString("phone");

                resultArea.setText("学号: " + studentNumber+ "\n"
                        + "姓名: " + name + "\n"
                        + "性别: " + gender + "\n" + "籍贯:" + native_place +"\n"+ "生日："+birth+"\n"+"系别:"+dept+"\n"+ "专业代码："+major+"\n"+"班级："+class_no + "\n"+"出生日期："+admission + "\n"+"地址："+home + "\n"+"联系方式："+phone1 + "\n");
            } else {
                resultArea.setText("未找到该学生信息。");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "数据库连接错误！");
        }
    }
}
