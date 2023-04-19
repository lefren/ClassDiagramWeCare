package OOP.JFRAME.controller;
import OOP.JFRAME.User;
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

        Registercontroller controller = new Registercontroller();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/register.fxml"));
        loader.setController(controller);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void logsignin(ActionEvent event) throws IOException {
        String nik = lognik.getText();
        String pass = logpass.getText();

        user = getAuthenticatedUser(nik,pass);

        if(user != null){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardadmin.fxml")));
            Scene scene = new Scene(root);

            Registercontroller controller = new Registercontroller();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/dashboardadmin.fxml"));
            loader.setController(controller);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(loginStage);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid NIK or password");
            alert.setContentText("Please try again");

            alert.showAndWait();
        }
    }

    public User user;
    private User getAuthenticatedUser(String nik, String pass){
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASS = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM users WHERE nik=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nik);
            preparedStatement.setString(2, pass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.age = resultSet.getString("age");
                user.phone = resultSet.getString("phone");
                user.nik = resultSet.getString("nik");
                user.password = resultSet.getString("password");
            }

            statement.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

}
