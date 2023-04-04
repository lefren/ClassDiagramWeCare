package OOP.JFRAME;

//Code Genarated by JGuiD

import javax.swing.*;

public class RegisterFrame extends JFrame
{
    JLabel Label_Men;
    JButton B_Submit;
    JLabel L_Name;
    JLabel L_Number;
    JLabel L_NI;
    JLabel L_Age;
    JTextField TF_Nam;
    JTextField TF_Ag;
    JTextField TF_Numbe;
    JTextField TF_NI;

    JTextField TF_PASS;

    public RegisterFrame()
    {
        getContentPane().setLayout(null);
        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void setupGUI()
    {
        Label_Men = new JLabel();
        Label_Men.setLocation(142,40);
        Label_Men.setSize(90,30);
        Label_Men.setText("Register Menu");
        getContentPane().add(Label_Men);

        B_Submit = new JButton();
        B_Submit.setLocation(126,284);
        B_Submit.setSize(130,30);
        B_Submit.setText("Submit");
        getContentPane().add(B_Submit);

        L_Name = new JLabel();
        L_Name.setLocation(55,94);
        L_Name.setSize(110,30);
        L_Name.setText("Name                      :");
        getContentPane().add(L_Name);

        L_Number = new JLabel();
        L_Number.setLocation(55,181);
        L_Number.setSize(110,30);
        L_Number.setText("Phone Number     :");
        getContentPane().add(L_Number);

        L_NI = new JLabel();
        L_NI.setLocation(55,226);
        L_NI.setSize(110,30);
        L_NI.setText("Password              :");
        getContentPane().add(L_NI);

        L_Age = new JLabel();
        L_Age.setLocation(55,138);
        L_Age.setSize(110,30);
        L_Age.setText("Age                         :");
        getContentPane().add(L_Age);

        TF_Nam = new JTextField();
        TF_Nam.setLocation(178,94);
        TF_Nam.setSize(170,30);
        TF_Nam.setText("");
        TF_Nam.setColumns(10);
        getContentPane().add(TF_Nam);

        TF_Ag = new JTextField();
        TF_Ag.setLocation(179,139);
        TF_Ag.setSize(170,30);
        TF_Ag.setText("");
        TF_Ag.setColumns(10);
        getContentPane().add(TF_Ag);

        TF_Numbe = new JTextField();
        TF_Numbe.setLocation(179,182);
        TF_Numbe.setSize(170,30);
        TF_Numbe.setText("");
        TF_Numbe.setColumns(10);
        getContentPane().add(TF_Numbe);

        TF_NI = new JTextField();
        TF_NI.setLocation(180,228);
        TF_NI.setSize(170,30);
        TF_NI.setText("");
        TF_NI.setColumns(10);
        getContentPane().add(TF_NI);

        setTitle("untitled ( JFrame )");
        setSize(400,400);
        setVisible(true);
        setResizable(true);
    }

    public void Registeruser(){
        String name = TF_Nam.getText();
        String age = TF_Ag.getText();
        String phone = TF_Numbe.getText();
        String identity = TF_NI.getText();
        String password = TF_PASS.getText();
//        String Cofirmpassword = String.valueOf()

        if(name.isEmpty() || age.isEmpty() || phone.isEmpty() || identity.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try Again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addUserToDB(name,age,phone,identity,password);
        if(user != null){
            dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Confirm Password does not match", "Try Again", JOptionPane.ERROR_MESSAGE);
        }

    }
    public User user;
    private User addUserToDB(String name, String age, String phone, String identity, String password){
        User user = null;
        return user;
    }

    public static void main( String args[] )
    {
        new RegisterFrame();
    }
}
