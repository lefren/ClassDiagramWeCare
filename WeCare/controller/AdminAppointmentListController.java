
package OOP.JFRAME.controller;

import OOP.JFRAME.Appointment;
import OOP.JFRAME.Apptmt;
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

public class AdminAppointmentListController implements Initializable {

    @FXML
    private Hyperlink admlogoutbtn;

    @FXML
    private Button appointmentbutton;


    @FXML
    private Button doctorbutton;
    @FXML
    private TableView<Apptmt> tableviewid;
    @FXML
    private TableColumn<Apptmt, String> doctornameid;
    @FXML
    private TableColumn<Apptmt, String> hospitalnameid;
    @FXML
    private TableColumn<Apptmt, String> patientnameid;
    @FXML
    private TableColumn<Appointment, Void> statusid;

    @FXML
    private Button hospitalbutton;

    @FXML
    private Label labeltotalappointment;

    @FXML
    private Button overviewbutton;

    @FXML
    private Button patientbutton;


    @FXML
    private Button settingsbtn;

    Apptmt apptmt = null;
    final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASS = "";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        loadDate();

        hospitalnameid.setCellValueFactory(new PropertyValueFactory<>("hospitalname"));
        doctornameid.setCellValueFactory(new PropertyValueFactory<>("doctorname"));
        patientnameid.setCellValueFactory(new PropertyValueFactory<>("patientname"));

        statusid.setCellFactory(param -> new TableCell<>() {

            private final Label statusLabel = new Label("PENDING");
            {
                statusLabel.setStyle("-fx-font-weight: bold");
                setGraphic(new HBox(10, statusLabel));
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
                    buttons.getChildren().addAll(statusLabel);
                    setGraphic(buttons);
                }
            }
        });

        try {

            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "SELECT COUNT(*) FROM appointment";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            result.next();
            int totalappointment = result.getInt(1);
            labeltotalappointment.setText("" + totalappointment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDate() {

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "SELECT * FROM appointment";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Apptmt apptmt1 = new Apptmt(
                        result.getString("hospital"),
                        result.getString("doctor"),
                        result.getString("patient")
                );
                tableviewid.getItems().add(apptmt1);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @FXML
    void admlogout(ActionEvent event) throws IOException {
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
    void appointmentbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardadminappointment.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void doctorbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardadmindoctor.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void hospitalbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardadminhospital.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

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

}
