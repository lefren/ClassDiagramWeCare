package OOP.JFRAME.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Logincontroller {

    @FXML
    private TextField lognik;

    @FXML
    private PasswordField logpass = new PasswordField();

    @FXML
    private Button logsigninbtn;

    @FXML
    private Hyperlink logsignup;
    private Window loginStage;

    @FXML
    void logregister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/register.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    @FXML
    void logsignin(ActionEvent event) throws IOException {
        String nik = lognik.getText();
        String pass = logpass.getText();

        String role = getAuthenticatedUser(nik,pass);

        if(role != null){
            Parent root;
            if (role.equals("admin")) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardadminoverview.fxml")));
            } else if(role.equals("doctor")) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboarddoctor.fxml")));
            }else{
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardpatient.fxml")));
            }

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(loginStage);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid NIK or password");
            alert.setContentText("Please try again");

            alert.showAndWait();
        }
    }

    private String getAuthenticatedUser(String nik, String pass){
//        User user = null;
        String role = null;
        final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASS = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

            Statement statement = conn.createStatement();
            String sql = "SELECT roles FROM users WHERE nik=? AND password=?" ;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nik);
            preparedStatement.setString(2, pass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                role = resultSet.getString("roles");
            }

            statement.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return role;
    }

}
