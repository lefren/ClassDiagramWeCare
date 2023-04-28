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

public class CRUDDoctorController {

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
    private TextField hospitalid;

    @FXML
    private TextField strnumberid;

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
    void docbuttonreg(ActionEvent event) {

    }

}
