package OOP.JFRAME;

import javax.swing.*;

public class LoginFrame extends JFrame
{
    JButton Button_Login;
    JLabel Label_Name;
    JLabel Label_Passwor;
    JTextField Text_Nam;
    JTextField Text_Password;
    JLabel Label_Title;

    public LoginFrame()
    {
        getContentPane().setLayout(null);
        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void setupGUI()
    {
        Button_Login = new JButton();
        Button_Login.setLocation(132,250);
        Button_Login.setSize(120,30);
        Button_Login.setText("Login");
        getContentPane().add(Button_Login);

        Label_Name = new JLabel();
        Label_Name.setLocation(55,141);
        Label_Name.setSize(100,30);
        Label_Name.setText("Name                 :");
        getContentPane().add(Label_Name);

        Label_Passwor = new JLabel();
        Label_Passwor.setLocation(55,182);
        Label_Passwor.setSize(100,30);
        Label_Passwor.setText("Password          :");
        getContentPane().add(Label_Passwor);

        Text_Nam = new JTextField();
        Text_Nam.setLocation(168,140);
        Text_Nam.setSize(160,30);
        Text_Nam.setText("");
        Text_Nam.setColumns(10);
        getContentPane().add(Text_Nam);

        Text_Password = new JPasswordField();
        Text_Password.setLocation(168,182);
        Text_Password.setSize(160,30);
        Text_Password.setText("");
        Text_Password.setColumns(10);
        getContentPane().add(Text_Password);

        Label_Title = new JLabel();
        Label_Title.setLocation(131,65);
        Label_Title.setSize(125,40);
        Label_Title.setText("Login Page");
        getContentPane().add(Label_Title);

        setTitle("We Care Application");
        setSize(400,400);
        setVisible(true);
        setResizable(false);


    }
    public static void main( String args[] )
    {
        new LoginFrame();
    }
}
