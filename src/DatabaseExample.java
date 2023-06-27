import com.sun.source.tree.NewArrayTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseExample {
    private JFrame frame;
    //private JTextField idField;
    private JTextField nameField;
    private JTextField genderField;
    private JTextField nativePlaceField;
    private JTextField birthField;
    private JTextField deptNoField;
    private JTextField majorCodeField;
    private JTextField classNoField;
    private JTextField admissionTimeField;
    private JTextField homeAddressField;
    private JTextField phoneField;
    private Connection connection;
    private DatabaseQueryExample queryExample;

    public DatabaseExample() {
        frame = new JFrame("学校信息管理系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(13, 2, 10, 10));
        frame.setResizable(false);



        // 创建标签
        //JLabel idLabel = new JLabel("学生ID（不输入则会顺位添加）：");
        JLabel nameLabel = new JLabel("姓名：");
        JLabel genderLabel = new JLabel("性别：");
        JLabel nativePlaceLabel = new JLabel("籍贯：");
        JLabel birthLabel = new JLabel("生日：");
        JLabel deptNoLabel = new JLabel("系别：");
        JLabel majorCodeLabel = new JLabel("专业代码：");
        JLabel classNoLabel = new JLabel("班级：");
        JLabel admissionTimeLabel = new JLabel("入学时间：");
        JLabel homeAddressLabel = new JLabel("家庭地址：");
        JLabel phoneLabel = new JLabel("电话号码：");

        // 创建文本框
        //idField = new JTextField(10);
        nameField = new JTextField(10);
        genderField = new JTextField(10);
        nativePlaceField = new JTextField(10);
        birthField = new JTextField(10);
        deptNoField = new JTextField(10);
        majorCodeField = new JTextField(10);
        classNoField = new JTextField(10);
        admissionTimeField = new JTextField(10);
        homeAddressField = new JTextField(10);
        phoneField = new JTextField(10);

        // 在管理员界面的构造函数中添加删除按钮和监听器
        // 创建按钮
        JButton updateButton = new JButton("修改数据");
        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = JOptionPane.showInputDialog(frame, "请输入要修改的学生的ID：");
                if (studentID != null && studentID != "") {
                    DatabaseUpdateExample databaseUpdateExample = new DatabaseUpdateExample(studentID);
                    databaseUpdateExample.showWindow();
                }
            }
        });

        //添加按钮到窗口
        frame.add(updateButton);

        // 创建按钮
        JButton addButton = new JButton("添加数据");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertData();
            }
        });

        //添加查询按钮
        JButton queryButton = new JButton("查询学生信息");


        //创建了一个新的对象，有关于查询窗口
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseQueryExample queryExample = new DatabaseQueryExample();
                queryExample.showWindow();
            }
        });


        // 在管理员界面的构造函数中添加删除按钮和监听器
// 创建按钮
        JButton deleteButton = new JButton("删除数据");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = JOptionPane.showInputDialog(frame, "请输入要删除的学生学号：");
                if (studentId != null && !studentId.isEmpty()) {
                    DatabaseDeleteExample deleteExample = new DatabaseDeleteExample(studentId);
                    deleteExample.showWindow();
                }
            }
        });

// 将按钮添加到窗口
        frame.add(deleteButton);





        // 将标签、文本框和按钮添加到窗口
        //frame.add(idLabel);
        //frame.add(idField);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(genderLabel);
        frame.add(genderField);
        frame.add(nativePlaceLabel);
        frame.add(nativePlaceField);
        frame.add(birthLabel);
        frame.add(birthField);
        frame.add(deptNoLabel);
        frame.add(deptNoField);
        frame.add(majorCodeLabel);
        frame.add(majorCodeField);
        frame.add(classNoLabel);
        frame.add(classNoField);
        frame.add(admissionTimeLabel);
        frame.add(admissionTimeField);
        frame.add(homeAddressLabel);
        frame.add(homeAddressField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(new JLabel()); // 空标签，用于布局
        frame.add(addButton);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setVisible(true);
        frame.add(new JLabel());//空标签
        frame.add(addButton);
        frame.add(queryButton);
    }

    private void insertData() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/学校信息管理系统";
        String username = "root";
        String password = "12345678";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "INSERT INTO student_info ( stu_name, gender, native_place, birth, dept_no, major_code, class_no, admission_time, home_address, phone) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // 从文本框中获取用户输入的数据
            //String id = idField.getText();
            String name = nameField.getText();
            String gender = genderField.getText();
            String nativePlace = nativePlaceField.getText();
            String birth = birthField.getText();
            String deptNo = deptNoField.getText();
            String majorCode = majorCodeField.getText();
            String classNo = classNoField.getText();
            String admissionTime = admissionTimeField.getText();
            String homeAddress = homeAddressField.getText();
            String phone = phoneField.getText();

            // 设置参数值
            //statement.setString(1, id);
            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setString(3, nativePlace);
            statement.setString(4, birth);
            statement.setString(5, deptNo);
            statement.setString(6, majorCode);
            statement.setString(7, classNo);
            statement.setString(8, admissionTime);
            statement.setString(9, homeAddress);
            statement.setString(10, phone);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "数据添加成功！");
            } else {
                JOptionPane.showMessageDialog(frame, "数据添加失败！");
            }

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "数据库连接错误！");
        }
    }
    public void showWindow(){
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            //初始化成员变量
            @Override
            public void run() {
                new DatabaseExample();
            }
            DatabaseExample example = new DatabaseExample();
        });
    }
}


