package OOP.JFRAME.controller;

import OOP.JFRAME.User;
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

public class AdminPatientController implements Initializable {

    @FXML
    private Hyperlink admlogoutbtn;
    @FXML
    private TableView<User> tableviewid;

    @FXML
    private TableColumn<User, String> ageid;

    @FXML
    private TableColumn<User, String> nameid;

    @FXML
    private TableColumn<User, String> nikid;

    @FXML
    private TableColumn<User, Void> controld;

    @FXML
    private TableColumn<User, String> phonenumid;

    @FXML
    private Button appointmentbutton;

    @FXML
    private Button doctorbutton;

    @FXML
    private Button hospitalbutton;

    @FXML
    private Label labeltotalpatientid;

    @FXML
    private Button overviewbutton;

    @FXML
    private Button patientbutton;
    @FXML
    private Button settingsbtn;


    User user = null;
    final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASS = "";

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

        nameid.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageid.setCellValueFactory(new PropertyValueFactory<>("age"));
        phonenumid.setCellValueFactory(new PropertyValueFactory<>("phone"));
        nikid.setCellValueFactory(new PropertyValueFactory<>("nik"));

        controld.setCellFactory(param -> new TableCell<>() {

            private final Button deleteButton = new Button("DELETE");
            private final Button editButton = new Button("EDIT");

            {
                deleteButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    deletePatient(user);
                });

                editButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    editPatient(user);
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
            String query = "SELECT COUNT(*) FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            result.next();
            int totalUsers = result.getInt(1);
            labeltotalpatientid.setText("" + totalUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deletePatient(User user) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "DELETE FROM users WHERE nik = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getNik());
            statement.executeUpdate();
            tableviewid.getItems().remove(user);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editPatient(User user){

    }
    private void loadDate() {

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "SELECT * FROM users";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                user = new User();
                user.name = result.getString("name");
                user.age = result.getString("age");
                user.phone = result.getString("phone");
                user.nik = result.getString("nik");
                tableviewid.getItems().add(user);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
