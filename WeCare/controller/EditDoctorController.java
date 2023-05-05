package OOP.JFRAME.controller;

import OOP.JFRAME.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditDoctorController implements Initializable {

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
    private ComboBox<String> hospitalid;

    @FXML
    private TextField strnumberid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateHospitalComboBox();
    }

    private void populateHospitalComboBox() {
        final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASS = "";

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS)) {
            String query = "SELECT * FROM hospital";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String hospitalName = resultSet.getString("hospital_name");
                hospitalid.getItems().add(hospitalName);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
    void docbtnupd(ActionEvent event) throws IOException {
        UpdateDoctor();


        if (doctor != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Update successful");

            alert.showAndWait();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardadmindoctor.fxml")));
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
        } else {
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

    public void UpdateDoctor() {
        String name = doctornameid.getText();
        String strnum = strnumberid.getText();
        String phonenum = doctorphoneid.getText();
        String hospital = hospitalid.getValue();
        String experience = doctorexperience.getText();
        String password = doctorpassword.getText();
        String conf_pass = doctorconfirmpassword.getText();

        System.out.println(name + strnum + phonenum + hospital + experience + password + conf_pass);

        if (name.isEmpty() || phonenum.isEmpty() || strnum.isEmpty() || hospital.isEmpty() || experience.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Try Again");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields");

            alert.showAndWait();
            return;
        }

        if (!password.equals(conf_pass)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Try Again");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields");

            alert.showAndWait();
            return;
        }

        doctor = updateDoctorInDB(name, strnum, phonenum, hospital, experience, password);

    }

    private Doctor updateDoctorInDB(String doctorname, String nomorstr, String phonenum, String hospitalplace, String pengalamankerja, String password){
        Doctor doctor = null;
        final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASS = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

            String sql = "UPDATE doctor SET doctor_name=?, phone_num=?, hospital_place=?, pengalaman_kerja=?, password=? WHERE nomor_str = ?";
            PreparedStatement updatestmt = conn.prepareStatement(sql);
            updatestmt.setString(1,doctorname);
            updatestmt.setString(2, phonenum);
            updatestmt.setString(3,hospitalplace);
            updatestmt.setString(4, pengalamankerja);
            updatestmt.setString(5, password);
            updatestmt.setString(6, nomorstr);

            int updatedrows = updatestmt.executeUpdate();
            if(updatedrows > 0){
                doctor = new Doctor(doctorname,nomorstr,phonenum,hospitalplace,pengalamankerja);
            }

            updatestmt.close();

        }catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        return doctor;
    }
}
