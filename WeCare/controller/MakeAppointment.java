package OOP.JFRAME.controller;

import OOP.JFRAME.Apptmt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    void aptbtn(ActionEvent event) throws IOException {
        MakePatientAppointment();

        if (apptmt != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Appointment successful");

            alert.showAndWait();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardpatientappointment.fxml")));
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
        }

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
    private Window stage;
    private ActionEvent event;
    public Apptmt apptmt;
    public void MakePatientAppointment(){
        String doctor = comboboxdoctor.getValue();
        String hospital = comboboxhospital.getValue();
        String name = textfname.getText();

        if(doctor.isEmpty() || hospital.isEmpty() || name.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Try Again");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields");

            alert.showAndWait();
            return;
        }

        apptmt = addappointment(doctor, hospital, name);

    }
    private Apptmt addappointment(String doctorname, String hospital, String name){
        Apptmt appointment = null;

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASS);

            String query = "SELECT * FROM appointment WHERE hospital = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, hospital);
            ResultSet resultSet = preparedStatement.executeQuery();

            String sql ="INSERT INTO appointment (hospital, patient, doctor) VALUES (?,?,?)";

            PreparedStatement insertstmt = conn.prepareStatement(sql);
            insertstmt.setString(1, hospital);
            insertstmt.setString(2, name);
            insertstmt.setString(3, doctorname);

            int addedrows = insertstmt.executeUpdate();

            if(addedrows > 0){
                appointment = new Apptmt(name,hospital,doctorname);
            }
            resultSet.close();
            preparedStatement.close();
            insertstmt.close();
            conn.close();

        }catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        return appointment;
    }
}

