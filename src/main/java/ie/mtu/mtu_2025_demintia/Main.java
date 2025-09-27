package ie.mtu.mtu_2025_demintia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
// xdh
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseConnection.initSchema();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
        stage.setTitle("Dementia Hub");
        stage.setMinWidth(1200);
        stage.setMinHeight(800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}