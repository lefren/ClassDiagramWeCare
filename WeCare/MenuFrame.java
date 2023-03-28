package OOP.JFRAME;

import javax.swing.*;

public class MenuFrame extends JFrame
{
    JLabel Label_Title;
    JButton Button_LogPat;
    JButton Button_LogAdm;
    JButton Button_RegPat;

    public MenuFrame()
    {
        getContentPane().setLayout(null);
        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void setupGUI()
    {
        Label_Title = new JLabel();
        Label_Title.setLocation(136,62);
        Label_Title.setSize(120,30);
        Label_Title.setText("We Care Application");
        getContentPane().add(Label_Title);

        Button_LogPat = new JButton();
        Button_LogPat.setLocation(120,182);
        Button_LogPat.setSize(150,40);
        Button_LogPat.setText("Login as Patient");
        getContentPane().add(Button_LogPat);

        Button_LogAdm = new JButton();
        Button_LogAdm.setLocation(120,233);
        Button_LogAdm.setSize(150,40);
        Button_LogAdm.setText("Login as Admin");
        getContentPane().add(Button_LogAdm);

        Button_RegPat = new JButton();
        Button_RegPat.setLocation(120,130);
        Button_RegPat.setSize(150,40);
        Button_RegPat.setText("Register as Patient");
        getContentPane().add(Button_RegPat);

        setTitle("untitled ( JFrame )");
        setSize(400,400);
        setVisible(true);
        setResizable(true);


    }
    public static void main( String args[] )
    {
        new MenuFrame();
    }
}
