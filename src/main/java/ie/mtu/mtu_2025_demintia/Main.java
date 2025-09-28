package ie.mtu.mtu_2025_demintia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        DatabaseConnection.initSchema(); // if you're using the SQLite setup

        // Load initial root
        var root = FXMLLoader.load(Main.class.getResource("HomePage.fxml"));

        // Create ONE Scene and keep it for the app lifetime
        Scene scene = new Scene((Parent) root, 1280, 800);
        stage.setTitle("Dementia Hub");
        stage.setMinWidth(1280);
        stage.setMinHeight(800);
        // stage.setResizable(false); // optional

        stage.setScene(scene);
        stage.show();

        // Register the scene with the router so controllers can swap roots later
        ViewRouter.attach(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}