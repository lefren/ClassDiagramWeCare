package OOP.JFRAME;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardAdmin extends Application {

    public DashboardAdmin() {
        
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdmin.class.getResource("View/Login.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load());
        stage.setTitle("We care application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
