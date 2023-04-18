package OOP.JFRAME.controller;

import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class controller {
    @FXML
    private Button btnupdatepr;

    @FXML
    private TextField txtadminid;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtfullname;

    @FXML
    private PasswordField txtnewpass;

    @FXML
    private PasswordField txtpass;

    @FXML
    private TextField txtphonenum;
    @FXML
    void updateprofile(ActionEvent event) {
        System.out.println("Test");
    }
}
