package OOP.JFRAME.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class controller {

    @FXML
    private Button admcancelbtn;

    @FXML
    private Hyperlink admlogoutbtn;

    @FXML
    private TextField admtxtid;

    @FXML
    private TextField admtxtname;

    @FXML
    private PasswordField admtxtnewpass;

    @FXML
    private PasswordField admtxtpass;

    @FXML
    private TextField admtxtphonenum;

    @FXML
    private Button admupdatebtn;

    @FXML
    private Button appointmentbutton;

    @FXML
    private Button doctorbutton;

    @FXML
    private Button hospitalbutton;

    @FXML
    private Button overviewbutton;

    @FXML
    private Button patientbutton;

    @FXML
    void admcancel(ActionEvent event) {

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
    void admupdate(ActionEvent event) {

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
    void patientbtn(ActionEvent event) {

    }
}
