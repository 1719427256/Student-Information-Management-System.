import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQueryExample {
    private JFrame frame;
    private JTextField studentIdField;
    private JTextArea resultArea;
    private Connection connection;


    public DatabaseQueryExample() {
        frame = new JFrame("学生信息查询");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);

        // 创建标签和文本框
        JLabel studentIdLabel = new JLabel("学号：");
        studentIdField = new JTextField(10);

        // 创建按钮
        JButton queryButton = new JButton("查询");
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();
                queryData(studentId);
            }
        });

        // 创建结果展示区域
        resultArea = new JTextArea(15, 25);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // 添加组件到窗口
        frame.add(studentIdLabel);
        frame.add(studentIdField);
        frame.add(queryButton);
        frame.add(scrollPane);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 在构造函数中建立数据库连接
        String jdbcUrl = "jdbc:mysql://localhost:3306/学校信息管理系统";
        String dbUsername = "root";
        String dbPassword = "12345678";

        try {
            connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "数据库连接错误！");
        }
    }

    public void queryData(String studentId) {
        String sql = "SELECT * FROM student_info WHERE stu_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            // 设置学号参数值
            statement.setString(1, studentId);

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

                resultArea.setText("学号: " + studentId + "\n"
                        + "姓名: " + name + "\n"
                        + "性别: " + gender + "\n" + "籍贯:" + native_place +"\n"+ "生日："+birth+"\n"+"系别:"+dept+"\n"+ "专业代码："+major+"\n"+"班级："+class_no + "\n"+"出生日期："+admission + "\n"+"地址："+home + "\n"+"联系方式："+phone1 + "\n");
            } else {
                resultArea.setText("未找到该学生信息。");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "查询错误！");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "数据库连接关闭错误！");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DatabaseQueryExample();
            }
        });
    }

    //用于显示查询窗口
    public void showWindow(){
        frame.setVisible(true);
    }
}

