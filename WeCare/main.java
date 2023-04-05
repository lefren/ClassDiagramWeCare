package OOP.JFRAME;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class main {
    public static void main(String[] args) {
        mainF();
    }

//    mainframe
    public static void mainF(){
        MenuFrame nmf=new MenuFrame();
        nmf.setTitle("MENU");
        nmf.Button_LogPat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nmf.setVisible(false);
                login();
            }
        });

        nmf.Button_RegPat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nmf.setVisible(false);
                register();

            }
        });
    }

    public static void login(){
        LoginFrame lgn =new LoginFrame();
        lgn.setTitle("login Patient");
        lgn.setVisible(true);

        lgn.Button_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lgn.setVisible(false);

            }
        });
    }

    public static void register(){
        RegisterFrame rgs = new RegisterFrame();
        rgs.setTitle("Register Patient");
        rgs.setVisible(true);

        rgs.B_Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int name = 1;
                int age = 1;
                int number = 1;
                int pass = 1;
                int conf_pass = 1;

                if (rgs.TF_Nam.getText().equals("")){
                    System.out.println("Name cannot be empty!");
                    name = 0;
                }

                if (rgs.TF_Ag.getText().equals("")){
                    System.out.println("Age cannot be empty!");
                    age = 0;
                }

                if (rgs.TF_Numbe.getText().equals("")){
                    System.out.println("Phone number cannot be empty!");
                    number = 0;
                }

                if (rgs.TF_PASS.getText().equals("")){
                    System.out.println("Password cannot be empty!");
                    pass = 0;
                }

                if (rgs.TF_CONF_PASS.getText().equals("")){
                    System.out.println("Confirm Password cannot be empty!");
                    conf_pass = 0;
                }

                if (name == 1 && age == 1 && pass == 1 && conf_pass == 1 && number == 1) {
                    rgs.setVisible(false);
                    System.out.println("Register Successful!");
                    mainF();
                }
            }
        });
    }
}
