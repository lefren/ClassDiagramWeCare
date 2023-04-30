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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminUserListController implements Initializable {
    @FXML
    private Hyperlink admlogoutbtn;
    @FXML
    private Label labeltotaluser;
    @FXML
    private Label labeltotalhospital;
    @FXML
    private Label labeltotaldoctor;
    @FXML
    private TableColumn<User, String> listageuser;

    @FXML
    private TableColumn<User, String>  listnameuser;

    @FXML
    private TableColumn<User, String>  listnikuser;

    @FXML
    private TableColumn<User, String>  listphonenumuser;

    @FXML
    private Button settingsbutton;

    @FXML
    private TableView<User> userstable;

    User user = null;
    final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASS = "";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        loadDate();

        listnameuser.setCellValueFactory(new PropertyValueFactory<>("name"));
        listageuser.setCellValueFactory(new PropertyValueFactory<>("age"));
        listphonenumuser.setCellValueFactory(new PropertyValueFactory<>("phone"));
        listnikuser.setCellValueFactory(new PropertyValueFactory<>("nik"));

        try {

            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "SELECT COUNT(*) FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            result.next();
            int totalUsers = result.getInt(1);
            labeltotaluser.setText("" + totalUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "SELECT COUNT(*) FROM doctor";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            result.next();
            int totaldoctor = result.getInt(1);
            labeltotaldoctor.setText("" + totaldoctor);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            String query = "SELECT COUNT(*) FROM hospital";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            result.next();
            int totalhospital = result.getInt(1);
            labeltotalhospital.setText("" + totalhospital);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   private void loadDate(){

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
               userstable.getItems().add(user);
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
