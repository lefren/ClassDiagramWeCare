package OOP.JFRAME.controller;

import OOP.JFRAME.Appointment;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class PatientAppointmentController implements Initializable {
    @FXML
    private Button appointmentpatientbutton;

    @FXML
    private VBox listviewid;
    @FXML
    private Label doctorid;
    @FXML
    private Label hospitalid;
    @FXML
    private HBox listdoctor;

    @FXML
    private Button doctorlistbutton;


    @FXML
    private Button hospitallistbutton;

    @FXML
    private Button overviewpatientbutton;
    @FXML
    private Button makeappointment;


    @FXML
    private Hyperlink patientlogoutbtn;

    @FXML
    void appointmentappointmentbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardpatientappointment.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void overviewpatientbtn(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardpatient.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void patientlogout(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/login.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void makeappointmentbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/makeappointment.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        stage.centerOnScreen();
    }
    @FXML
    private TableView<Appointment> appointmentlist;

    @FXML
    private TableColumn<Appointment, String> doctornameid;

    @FXML
    private TableColumn<Appointment, String>  hospitalnameid;

    @FXML
    private TableColumn<Appointment, String>  numberid;

    @FXML
    private TableColumn<Appointment, Void> controlid;

    final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASS = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAppointments();

        numberid.setCellValueFactory(new PropertyValueFactory<>("id"));
        hospitalnameid.setCellValueFactory(new PropertyValueFactory<>("hospitalName"));
        doctornameid.setCellValueFactory(new PropertyValueFactory<>("doctorName"));

        controlid.setCellFactory(param -> new TableCell<>() {

            private final Button paybutton = new Button("PAY");
            private final Label statusLabel = new Label("PENDING");
            {
                paybutton.setOnAction(event -> {
                    Appointment apptmt = getTableView().getItems().get(getIndex());
                    try {
                        payappointment(apptmt);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                statusLabel.setStyle("-fx-font-weight: bold");
                setGraphic(new HBox(10, paybutton, statusLabel));
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    Appointment apptmt = getTableView().getItems().get(getIndex());
                    statusLabel.setText(apptmt.getStatus());
                    if (apptmt.getStatus().equals("success")) {
                        statusLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: green;");
                    } else {
                        statusLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: yellow;");
                    }
                    HBox buttons = new HBox();
                    buttons.setSpacing(10);
                    buttons.getChildren().addAll(paybutton, statusLabel);
                    setGraphic(buttons);
                }
            }
        });
    }



    private void payappointment(Appointment appointment) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/payment1.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        stage.centerOnScreen();
    }


    private void loadAppointments() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

            String query = "SELECT id, hospital, doctor, status FROM appointment";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = new Appointment(
                        resultSet.getInt("id")
                        , resultSet.getString("hospital")
                        , resultSet.getString("doctor")
                        , resultSet.getString("status"),
                        null

                );
                appointmentlist.getItems().add(appointment);
            }
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

}
