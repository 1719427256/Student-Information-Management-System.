import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDeleteExample {
    private JFrame frame;
    private JTextField studentIdField;

    public DatabaseDeleteExample(String studentId) {
        frame = new JFrame("删除学生信息");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 2, 10, 10));
        frame.setResizable(false);

        // 创建标签和文本框
        JLabel studentIdLabel = new JLabel("学号：");
        studentIdField = new JTextField(10);
        studentIdField.setEditable(false); // 学号不可编辑
        studentIdField.setText(studentId);

        // 创建按钮
        JButton deleteButton = new JButton("确认删除");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });

        // 将标签、文本框和按钮添加到窗口
        frame.add(studentIdLabel);
        frame.add(studentIdField);
        frame.add(new JLabel()); // 空标签，用于布局
        frame.add(deleteButton);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void deleteData() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/学校信息管理系统";
        String username = "root";
        String password = "12345678";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "DELETE FROM student_info WHERE stu_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            String studentId = studentIdField.getText();
            statement.setString(1, studentId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "数据删除成功！");
            } else {
                JOptionPane.showMessageDialog(frame, "数据删除失败！");
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
