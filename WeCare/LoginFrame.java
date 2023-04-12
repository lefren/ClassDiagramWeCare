package OOP.JFRAME;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame
{
    JLabel Label_Title;
    JLabel Label_nik;
    JLabel Label_Password;
    JTextField Text_nik;
    JPasswordField Text_Password;
    JButton Button_Login, Button_Register;

    public LoginFrame()
    {
        getContentPane().setLayout(null);
        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void setupGUI()
    {
        Label_Title = new JLabel();
        Label_Title.setLocation(168,80);
        Label_Title.setSize(125,40);
        Font font = Label_Title.getFont();
        font = font.deriveFont(16.0f);
        Label_Title.setFont(font);
        Label_Title.setText("Login Page");
        getContentPane().add(Label_Title);

        Label_nik = new JLabel();
        Label_nik.setLocation(55,141);
        Label_nik.setSize(100,30);
        Label_nik.setText("NIK");
        getContentPane().add(Label_nik);

        Label_Password = new JLabel();
        Label_Password.setLocation(55,182);
        Label_Password.setSize(100,30);
        Label_Password.setText("Password");
        getContentPane().add(Label_Password);

        Text_nik = new JTextField();
        Text_nik.setLocation(168,140);
        Text_nik.setSize(228,30);
        Text_nik.setText("");
        Text_nik.setColumns(10);
        getContentPane().add(Text_nik);

        Text_Password = new JPasswordField();
        Text_Password.setLocation(168,182);
        Text_Password.setSize(228,30);
        Text_Password.setText("");
        Text_Password.setColumns(10);
        getContentPane().add(Text_Password);

        Button_Login = new JButton();
        Button_Login.setLocation(168,240);
        Button_Login.setSize(100,30);
        Button_Login.setText("Login");
        getContentPane().add(Button_Login);

        Button_Register = new JButton();
        Button_Register.setLocation(296,240);
        Button_Register.setSize(100,30);
        Button_Register.setText("Register");
        getContentPane().add(Button_Register);

        Button_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nik = Text_nik.getText();
                String pass = String.valueOf(Text_Password.getPassword());

                user = getAuthenticatedUser(nik,pass);

                if (user != null){
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "NIK or password Invalid!",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        Button_Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
                dispose();
            }
        });

        setTitle("We Care Application");
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    public User user;
    private User getAuthenticatedUser(String nik, String pass){
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASS = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM users WHERE nik=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nik);
            preparedStatement.setString(2, pass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.age = resultSet.getString("age");
                user.phone = resultSet.getString("phone");
                user.nik = resultSet.getString("nik");
                user.password = resultSet.getString("password");
            }

            statement.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
