package OOP.JFRAME.controller;

import OOP.JFRAME.Doctor;
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

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class CRUDDoctorController {

    @FXML
    private Button btncancel;

    @FXML
    private Button docbtnregister;

    @FXML
    private PasswordField doctorconfirmpassword;

    @FXML
    private TextField doctorexperience;

    @FXML
    private TextField doctornameid;

    @FXML
    private PasswordField doctorpassword;

    @FXML
    private TextField doctorphoneid;

    @FXML
    private TextField hospitalid;

    @FXML
    private TextField strnumberid;

    @FXML
    void docbtncancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardadmindoctor.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void docbuttonreg(ActionEvent event) throws IOException{
        Registerdoctor();

        if(doctor != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Registration successful");

            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Confirm Password does not match");

            alert.showAndWait();
        }

    }
    private Window stage;
    private ActionEvent event;
    public Doctor doctor;
    public void Registerdoctor(){
        String name = doctornameid.getText();
        String strnum = strnumberid.getText();
        String phonenum = doctorphoneid.getText();
        String hospital = hospitalid.getText();
        String experience = doctorexperience.getText();
        String password = doctorpassword.getText();
        String conf_pass = doctorconfirmpassword.getText();

        if(name.isEmpty() || phonenum.isEmpty() || strnum.isEmpty() || hospital.isEmpty() || experience.isEmpty() || password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Try Again");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields");

            alert.showAndWait();
            return;
        }

        if(!password.equals(conf_pass)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Try Again");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields");

            alert.showAndWait();
            return;
        }

        doctor = adddoctortodb(name,strnum,phonenum,hospital,experience,password);

    }

    private Doctor adddoctortodb(String doctorname, String nomorstr, String phonenum, String hospitalplace, String pengalamankerja, String password){
        Doctor doctor = null;
        final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASS = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

            String query = "SELECT * FROM doctor WHERE nomor_str = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nomorstr);
            ResultSet resultSet = preparedStatement.executeQuery();

            String sql = "INSERT INTO doctor (doctor_name, nomor_str, phone_num, hospital_place, pengalaman_kerja, password) VALUES (?,?,?,?,?,?)";
            PreparedStatement insertstmt = conn.prepareStatement(sql);
            insertstmt.setString(1,doctorname);
            insertstmt.setString(2, nomorstr);
            insertstmt.setString(3,phonenum);
            insertstmt.setString(4, hospitalplace);
            insertstmt.setString(5,pengalamankerja);
            insertstmt.setString(6, password);

            int addedrows = insertstmt.executeUpdate();
            if(addedrows > 0){
                doctor = new Doctor(doctorname,nomorstr,phonenum,hospitalplace,pengalamankerja);

            sql = "INSERT INTO users (name, age, phone, nik, password, roles) VALUES (?, ?, ?, ?, ?, ?)";
            insertstmt = conn.prepareStatement(sql);
            insertstmt.setString(1, doctorname);
            insertstmt.setInt(2, Integer.parseInt("1"));
            insertstmt.setString(3, "anonymous");
            insertstmt.setString(4, nomorstr);
            insertstmt.setString(5, password);
            insertstmt.setString(6, "doctor");
            insertstmt.executeUpdate();

            }
            resultSet.close();
            preparedStatement.close();
            insertstmt.close();

        }catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        return doctor;
    }


}
