package OOP.JFRAME.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class MakeAppointment implements Initializable {

    @FXML
    private Button apt;

    @FXML
    private Button cancl;

    @FXML
    private ComboBox<String> comboboxdoctor;

    @FXML
    private ComboBox<String> comboboxhospital;

    @FXML
    private TextField textfname;

    @FXML
    void aptbtn(ActionEvent event) {

    }

    @FXML
    void cnclbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardpatientappointment.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASS = "";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        populateHospitalComboBox();
        populateDoctorComboBox();
    }
    private void populateDoctorComboBox(){
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS)) {
            String query = "SELECT * FROM doctor";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String doctorName = resultSet.getString("doctor_name");
                comboboxdoctor.getItems().add(doctorName);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void populateHospitalComboBox(){
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS)) {
            String query = "SELECT * FROM hospital";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String hospitalName = resultSet.getString("hospital_name");
                comboboxhospital.getItems().add(hospitalName);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

