package OOP.JFRAME;

//Code Genarated by JGuiD

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterFrame extends JDialog
{
    JButton B_Submit;
    JButton B_CANCEL;
    JLabel Label_Men;
    JLabel L_Name;
    JLabel L_Number;
    JLabel L_Age;
    JLabel L_NIK;
    JLabel L_PASS;
    JLabel L_CONF_PASS;
    JTextField TF_Nam;
    JTextField TF_Ag;
    JTextField TF_Numbe;
    JTextField TF_NIK;
    JTextField TF_PASS;
    JTextField TF_CONF_PASS;

    public RegisterFrame()
    {
        getContentPane().setLayout(null);
        setupGUI();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    void setupGUI(){
        //        TITLE
        Label_Men = new JLabel();
        Label_Men.setLocation(190,40);
        Label_Men.setSize(150,50);
        Font font = Label_Men.getFont();
        font = font.deriveFont(16.0f);
        Label_Men.setFont(font);
        Label_Men.setText("Register Menu");
        getContentPane().add(Label_Men);
//        END OF TITLE
//        LABEL
        L_Name = new JLabel();
        L_Name.setLocation(55,94);
        L_Name.setSize(110,30);
        L_Name.setText("Name");
        getContentPane().add(L_Name);

        L_Age = new JLabel();
        L_Age.setLocation(55,138);
        L_Age.setSize(110,30);
        L_Age.setText("Age");
        getContentPane().add(L_Age);

        L_Number = new JLabel();
        L_Number.setLocation(55,181);
        L_Number.setSize(110,30);
        L_Number.setText("Phone Number");
        getContentPane().add(L_Number);

        L_NIK = new JLabel();
        L_NIK.setLocation(55,224);
        L_NIK.setSize(110,30);
        L_NIK.setText("NIK");
        getContentPane().add(L_NIK);

        L_PASS = new JLabel();
        L_PASS.setLocation(55,267);
        L_PASS.setSize(110,30);
        L_PASS.setText("Password");
        getContentPane().add(L_PASS);

        L_CONF_PASS= new JLabel();
        L_CONF_PASS.setLocation(55,312);
        L_CONF_PASS.setSize(110,30);
        L_CONF_PASS.setText("Confirm Password");
        getContentPane().add(L_CONF_PASS);
//        END OF LABEL
//        TEXT FIELD
        TF_Nam = new JTextField();
        TF_Nam.setLocation(190,94);
        TF_Nam.setSize(230,30);
        TF_Nam.setText("");
        TF_Nam.setColumns(10);
        getContentPane().add(TF_Nam);

        TF_Ag = new JTextField();
        TF_Ag.setLocation(190,139);
        TF_Ag.setSize(230,30);
        TF_Ag.setText("");
        TF_Ag.setColumns(10);
        getContentPane().add(TF_Ag);

        TF_Numbe = new JTextField();
        TF_Numbe.setLocation(190,182);
        TF_Numbe.setSize(230,30);
        TF_Numbe.setText("");
        TF_Numbe.setColumns(10);
        getContentPane().add(TF_Numbe);

        TF_NIK = new JTextField();
        TF_NIK.setLocation(190,225);
        TF_NIK.setSize(230,30);
        TF_NIK.setText("");
        TF_NIK.setColumns(10);
        getContentPane().add(TF_NIK);

        TF_PASS = new JTextField();
        TF_PASS.setLocation(190,270);
        TF_PASS.setSize(230,30);
        TF_PASS.setText("");
        TF_PASS.setColumns(10);
        getContentPane().add(TF_PASS);

        TF_CONF_PASS = new JTextField();
        TF_CONF_PASS.setLocation(190,315);
        TF_CONF_PASS.setSize(230,30);
        TF_CONF_PASS.setText("");
        TF_CONF_PASS.setColumns(10);
        getContentPane().add(TF_CONF_PASS);
//        END OF TEXT FIELD

        B_Submit = new JButton();
        B_Submit.setLocation(190,372);
        B_Submit.setSize(90,30);
        B_Submit.setText("Submit");
        getContentPane().add(B_Submit);

        B_CANCEL = new JButton();
        B_CANCEL.setLocation(290,372);
        B_CANCEL.setSize(90,30);
        B_CANCEL.setText("Cancel");
        getContentPane().add(B_CANCEL);

        B_Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registeruser();
            }
        });
        B_CANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.mainF();
                dispose();
            }
        });

        setTitle("Register Form");
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }


    public void Registeruser(){
        String name = TF_Nam.getText();
        String age = TF_Ag.getText();
        String phone = TF_Numbe.getText();
        String nik = TF_NIK.getText();
        String password = TF_PASS.getText();
        String confirm_password = TF_CONF_PASS.getText();

        if(name.isEmpty() || age.isEmpty() || phone.isEmpty() || nik.isEmpty() || password.isEmpty() || confirm_password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!password.equals(confirm_password)){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDB(name,age,phone,nik,password);
        if(user != null){
            dispose();
            new LoginFrame();
        }else{
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public User user;

    private User addUserToDB(String name, String age, String phone, String nik, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASS = "";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

            // Check if NIK already exists
            String query = "SELECT * FROM users WHERE nik = ?";
            PreparedStatement checkStmt = conn.prepareStatement(query);
            checkStmt.setString(1, nik);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "NIK Already Exist! Please input the correct NIK");
                return null;
            }

            // Check if NIK length is at least 16
            if (nik.length() < 16) {
                JOptionPane.showMessageDialog(null, "NIK must be at least 16 characters long");
                return null;
            }

            // Validate name
            if (!name.matches("^[a-zA-Z ]+$")) {
                JOptionPane.showMessageDialog(null, "Name must contain only letters and spaces");
                return null;
            }

            // Validate phone number
            if (!phone.matches("^\\d{10,}$")) {
                JOptionPane.showMessageDialog(null, "Phone number must contain at least 10 digits");
                return null;
            }

            // Validate password
            if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
                JOptionPane.showMessageDialog(null, "Password must contain at least one lowercase letter, one uppercase letter, and one number");
                return null;
            }

            // Hash password using SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] hashedPassword = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            String hashedPasswordStr = sb.toString();

            // Insert new user into database
            String sql = "INSERT INTO users (name, age, phone, nik, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(sql);
            insertStmt.setString(1, name);
            insertStmt.setInt(2, Integer.parseInt(age));
            insertStmt.setString(3, phone);
            insertStmt.setString(4, nik);
            insertStmt.setString(5, hashedPasswordStr);

            int addedRows = insertStmt.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.age = age;
                user.phone = phone;
                user.nik = nik;
                user.password = hashedPasswordStr;
            }

            rs.close();
            checkStmt.close();
            insertStmt.close();
            conn.close();

        } catch (SQLException | NoSuchAlgorithmException | NumberFormatException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void main( String args[] )
    {
        RegisterFrame myform = new RegisterFrame();
        User user = myform.user;
        if(user != null){
            System.out.println("Successfull");
        }else{
            System.out.println("Register canceled!");
        }
    }
}