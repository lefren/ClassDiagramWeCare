package OOP.JFRAME;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame
{
    JLabel Label_Title;
    JLabel Label_nik;
    JLabel Label_Password;
    JTextField Text_nik;
    JTextField Text_Password;
    JButton Button_Login;

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
        Text_nik.setSize(160,30);
        Text_nik.setText("");
        Text_nik.setColumns(10);
        getContentPane().add(Text_nik);

        Text_Password = new JPasswordField();
        Text_Password.setLocation(168,182);
        Text_Password.setSize(160,30);
        Text_Password.setText("");
        Text_Password.setColumns(10);
        getContentPane().add(Text_Password);

        Button_Login = new JButton();
        Button_Login.setLocation(168,240);
        Button_Login.setSize(120,30);
        Button_Login.setText("Login");
        getContentPane().add(Button_Login);

        setTitle("We Care Application");
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);


    }
    public static void main( String args[] )
    {
        new LoginFrame();
    }
}
