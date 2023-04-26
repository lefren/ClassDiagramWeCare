package OOP.JFRAME.controller;

import OOP.JFRAME.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Registercontroller {

    @FXML
    private Button regsigninbtn;

    @FXML
    private Button regsignupbtn;

    @FXML
    private TextField regtxtage;

    @FXML
    private PasswordField regtxtconfpass = new PasswordField();

    @FXML
    private TextField regtxtname;

    @FXML
    private TextField regtxtnik;

    @FXML
    private PasswordField regtxtpass = new PasswordField();

    @FXML
    private TextField regtxtphonenum;
    private Window stage;
    private ActionEvent event;

    @FXML
    void regsignin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Login.fxml")));
        Scene scene = new Scene(root);

        Registercontroller controller = new Registercontroller();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        loader.setController(controller);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    @FXML
    void regsignup(ActionEvent event) throws IOException {
        Registeruser();

        if(user != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Registration successful");

            alert.showAndWait();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Login.fxml")));
            Scene scene = new Scene(root);

            Registercontroller controller = new Registercontroller();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
            loader.setController(controller);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Confirm Password does not match");

            alert.showAndWait();
        }
    }
    public void Registeruser() throws IOException {
        String name = regtxtname.getText();
        String age = regtxtage.getText();
        String phone = regtxtphonenum.getText();
        String nik = regtxtnik.getText();
        String password = regtxtpass.getText();
        String confirm_password = regtxtconfpass.getText();

        if(name.isEmpty() || age.isEmpty() || phone.isEmpty() || nik.isEmpty() || password.isEmpty() || confirm_password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Try Again");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields");

            alert.showAndWait();
            return;
        }

        if(!password.equals(confirm_password)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Try Again");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields");

            alert.showAndWait();
            return;
        }

        user = addUserToDB(name,age,phone,nik,password);
    }
    public User user;
    private User addUserToDB(String name, String age, String phone, String nik, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASS = "";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

            String query = "SELECT * FROM users WHERE nik = ?";
            PreparedStatement checkStmt = conn.prepareStatement(query);
            checkStmt.setString(1, nik);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "NIK Already Exist! Please input the correct NIK");
                return null;
            }

            if (nik.length() <= 16) {
                JOptionPane.showMessageDialog(null, "NIK must be at least 16 characters long");
                return null;
            }

            if (!name.matches("^[a-zA-Z ]+$")) {
                JOptionPane.showMessageDialog(null, "Name must contain only letters and spaces");
                return null;
            }

            if (!phone.matches("^\\d{10,}$")) {
                JOptionPane.showMessageDialog(null, "Phone number must contain at least 10 digits");
                return null;
            }

            if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
                JOptionPane.showMessageDialog(null, "Password must contain at least one lowercase letter, one uppercase letter, and one number");
                return null;
            }


            String sql = "INSERT INTO users (name, age, phone, nik, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(sql);
            insertStmt.setString(1, name);
            insertStmt.setInt(2, Integer.parseInt(age));
            insertStmt.setString(3, phone);
            insertStmt.setString(4, nik);
            insertStmt.setString(5, password);

            int addedRows = insertStmt.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.age = age;
                user.phone = phone;
                user.nik = nik;
                user.password = password;
            }

            rs.close();
            checkStmt.close();
            insertStmt.close();
            conn.close();

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        return user;
    }

}
