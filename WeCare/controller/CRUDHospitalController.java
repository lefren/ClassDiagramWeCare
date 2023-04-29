package OOP.JFRAME.controller;

import OOP.JFRAME.Hospital;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class CRUDHospitalController {

    @FXML
    private Button addbtnid;

    @FXML
    private Button cancelbtnid;

    @FXML
    private TextField hospitaladdressid;

    @FXML
    private TextField hospitalnameid;

    @FXML
    private TextField hospitalnumber;

    @FXML
    void addbtn(ActionEvent event) {
        Registerhospital();

        if(hospital != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Registration successful");

            alert.showAndWait();
        }
    }

    @FXML
    void cancelbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardadminhospital.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }


    private Window stage;
    private ActionEvent event;
    public Hospital hospital;

    public void Registerhospital(){
        String name = hospitalnameid.getText();
        String address = hospitaladdressid.getText();
        String phone = hospitalnumber.getText();

        if(name.isEmpty() || address.isEmpty() || phone.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Try Again");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields");

            alert.showAndWait();
            return;
        }

        hospital = addhospitaltodb(name, address, phone);
    }
    private Hospital addhospitaltodb(String name, String address, String phone){
        Hospital hospital = null;
        final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASS = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

            String query = "SELECT * FROM hospital WHERE hospital_name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            String sql = "INSERT INTO hospital (hospital_name, address, notelp_rs) VALUES (?,?,?)";

            PreparedStatement insertstmt = conn.prepareStatement(sql);
            insertstmt.setString(1,name);
            insertstmt.setString(2,address);
            insertstmt.setString(3,phone);

            int adddedrows = insertstmt.executeUpdate();

            if(adddedrows > 0){
                hospital = new Hospital(name, address, phone);
            }

            resultSet.close();
            preparedStatement.close();
            insertstmt.close();
            conn.close();

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        return hospital;
    }
}
