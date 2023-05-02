package OOP.JFRAME.controller;

import OOP.JFRAME.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Payment2 {

    @FXML
    private Button statusbtn;

    public Appointment appointment;
    final String DB_URL = "jdbc:mysql://localhost/projectoop?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASS = "";
    private Window stage;
    private ActionEvent event;
    @FXML
    void statusbtnclick(ActionEvent event) throws IOException {
        int appointmentid = 3;
        String newStatus = "success";

        Appointment updatedAppointment = updateAppointmentStatus(appointmentid, newStatus);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/dashboardpatientappointment.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    private Appointment updateAppointmentStatus(int id, String status) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASS);

            String sql = "UPDATE appointment SET status = ? WHERE id = ?";
            PreparedStatement updatestmt = conn.prepareStatement(sql);
            updatestmt.setString(1, status);
            updatestmt.setInt(2, id);

            int updatedRows = updatestmt.executeUpdate();

            if (updatedRows > 0) {
                return new Appointment(id, null,null, status, null);
            }

            updatestmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}