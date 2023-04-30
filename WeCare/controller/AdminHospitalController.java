package OOP.JFRAME.controller;

import OOP.JFRAME.Hospital;
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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminHospitalController implements Initializable {

    @FXML
    private Button addnewhospital;

    @FXML
    private Hyperlink admlogoutbtn;

    @FXML
    private Button appointmentbutton;

    @FXML
    private Button doctorbutton;
    @FXML
    private TableView<Hospital> tableviewid;
    @FXML
    private TableColumn<Hospital, String> hospitaladdressid;
    @FXML
    private TableColumn<Hospital, String>  hospitalnameid;
    @FXML
    private TableColumn<Hospital, String>  hospitalphoneid;
    @FXML
    private TableColumn<Hospital, Void>  hospitalcontrol;

    @FXML
    private Button hospitalbutton;

    @FXML
    private Label labeltotalhospitalid;

    @FXML
    private Button overviewbutton;

    @FXML
    private Button patientbutton;

    @FXML
    private Button settingsbtn;
    Hospital hospital = null;
    final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASS = "";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        loaddate();

        hospitalnameid.setCellValueFactory(new PropertyValueFactory<>("hospital_name"));
        hospitaladdressid.setCellValueFactory(new PropertyValueFactory<>("address"));
        hospitalphoneid.setCellValueFactory(new PropertyValueFactory<>("no_telp"));

        hospitalcontrol.setCellFactory(param -> new TableCell<>() {

            private final Button deleteButton = new Button("DELETE");
            private final Button editButton = new Button("EDIT");

            {
                deleteButton.setOnAction(event -> {
                    Hospital hospital = getTableView().getItems().get(getIndex());
                    deletehospital(hospital);
                });

                editButton.setOnAction(event -> {
                    Hospital hospital = getTableView().getItems().get(getIndex());
                    edithospital(hospital);
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
            String query = "SELECT COUNT(*) FROM hospital";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            result.next();
            int totalhospital = result.getInt(1);
            labeltotalhospitalid.setText("" + totalhospital);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loaddate(){
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "SELECT * FROM hospital";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Hospital hospital = new Hospital(
                        result.getString("hospital_name")
                        , result.getString("address")
                        , result.getString("notelp_rs"));
                tableviewid.getItems().add(hospital);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void edithospital(Hospital hospital){

    }

    private void deletehospital(Hospital hospital) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "DELETE FROM hospital WHERE address = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, hospital.getAddress());
            statement.executeUpdate();
            tableviewid.getItems().remove(hospital);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addnewhospitalbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/adminregisterhospital.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
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
