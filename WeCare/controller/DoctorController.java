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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

    @FXML
    private Hyperlink admlogoutbtn;

    @FXML
    private Button appointmentbutton;

    @FXML
    private TableView<Doctor> tableviewid;
    @FXML
    private TableColumn<Doctor, String> strnumid;
    @FXML
    private TableColumn<Doctor, String> doctornameid;
    @FXML
    private TableColumn<Doctor, String> experienceid;
    @FXML
    private TableColumn<Doctor, String> hospitalid;
    @FXML
    private TableColumn<Doctor, String> phonenumid;
    @FXML
    private TableColumn<Doctor, Void> controlid;

    @FXML
    private Button doctorbutton;

    @FXML
    private Button hospitalbutton;

    @FXML
    private Label labeltotaldoctorid;

    @FXML
    private Button overviewbutton;

    @FXML
    private Button patientbutton;

    @FXML
    private Button settingsbtn;


    @FXML
    void admlogout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/login.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void appointmentbtn(ActionEvent event) {

    }

    @FXML
    void doctorbtn(ActionEvent event) {

    }

    @FXML
    void hospitalbtn(ActionEvent event) {

    }
    Doctor doctor = null;
    final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASS = "";

    @FXML
    void overviewbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardadminoverview.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void patientbtn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardadminpatient.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void settingsbtn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardadmin.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        loadDate();

        doctornameid.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        strnumid.setCellValueFactory(new PropertyValueFactory<>("nomorStr"));
        phonenumid.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        hospitalid.setCellValueFactory(new PropertyValueFactory<>("hospitalPlace"));
        experienceid.setCellValueFactory(new PropertyValueFactory<>("pengalamanKerja"));

        controlid.setCellFactory(param -> new TableCell<>() {

            private final Button deleteButton = new Button("DELETE");
            private final Button editButton = new Button("ADD");

            {
                deleteButton.setOnAction(event -> {
                    Doctor doctor = getTableView().getItems().get(getIndex());
                    deletedoctor(doctor);
                });

                editButton.setOnAction(event -> {
                    Doctor doctor = getTableView().getItems().get(getIndex());
                    adddoctor(doctor);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox();
                    buttons.setSpacing(10);
                    buttons.getChildren().addAll(editButton, deleteButton);
                    setGraphic(buttons);
                }
            }
        });

        try {

            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "SELECT COUNT(*) FROM doctor";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            result.next();
            int totaldoctor = result.getInt(1);
            labeltotaldoctorid.setText("" + totaldoctor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDate(){
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "SELECT * FROM doctor";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Doctor doctor = new Doctor(
                        result.getString("doctor_name"),
                        result.getString("nomor_str"),
                        result.getString("phone_num"),
                        result.getString("hospital_place"),
                        result.getString("pengalaman_kerja")
                );
                tableviewid.getItems().add(doctor);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deletedoctor(Doctor doctor) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "DELETE FROM doctor WHERE nomor_str = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, doctor.getNomorStr());
            statement.executeUpdate();
            tableviewid.getItems().remove(doctor);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void adddoctor(Doctor doctor) {
    }
}
