import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUpdateExample {
    private JFrame frame;
    private JTextField studentIdField;
    private JTextField nameField;
    private JTextField genderField;
    // 添加其他文本框组件

    public DatabaseUpdateExample(String studentId) {
        frame = new JFrame("修改学生信息");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));
        frame.setResizable(false);

        // 创建标签和文本框
        JLabel studentIdLabel = new JLabel("学号：");
        studentIdField = new JTextField(10);
        studentIdField.setEditable(false); // 学号不可编辑
        studentIdField.setText(studentId);

        JLabel nameLabel = new JLabel("姓名：");
        nameField = new JTextField(10);

        JLabel genderLabel = new JLabel("性别：");
        genderField = new JTextField(10);

        // 添加其他标签和文本框组件

        // 创建按钮
        JButton updateButton = new JButton("确认修改");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        });

        // 将标签、文本框和按钮添加到窗口
        frame.add(studentIdLabel);
        frame.add(studentIdField);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(genderLabel);
        frame.add(genderField);
        // 添加其他标签、文本框和按钮组件

        frame.add(new JLabel()); // 空标签，用于布局
        frame.add(updateButton);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void updateData() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/学校信息管理系统";
        String username = "root";
        String password = "12345678";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "UPDATE student_info SET stu_name = ?, gender = ? WHERE stu_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            String name = nameField.getText();
            String gender = genderField.getText();
            String studentId = studentIdField.getText();

            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setString(3, studentId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "数据修改成功！");
            } else {
                JOptionPane.showMessageDialog(frame, "数据修改失败！");
            }

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "数据库连接错误！");
        }
    }

    public void showWindow() {
        frame.setVisible(true);
    }
}
