package OOP.JFRAME.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Registercontroller {

    @FXML
    private Button regsigninbtn;

    @FXML
    private Button regsignupbtn;

    @FXML
    private TextField regtxtage;

    @FXML
    private PasswordField regtxtconfpass;

    @FXML
    private TextField regtxtname;

    @FXML
    private TextField regtxtnik;

    @FXML
    private PasswordField regtxtpass;

    @FXML
    private TextField regtxtphonenum;

    @FXML
    void regsignin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Login.fxml")));
        Scene scene = new Scene(root);

        Registercontroller controller = new Registercontroller();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        loader.setController(controller);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void regsignup(ActionEvent event) {

    }
}
