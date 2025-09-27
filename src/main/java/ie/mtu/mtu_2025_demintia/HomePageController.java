package ie.mtu.mtu_2025_demintia;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class HomePageController {
    @FXML private Button loginButton;
    @FXML private Button registerButton;

    @FXML
    private void loginButtonOnAction() {
        switchTo("Login.fxml");
    }

    @FXML
    private void registerButtonOnAction() {
        switchTo("CreateAccount.fxml");
    }

    private void switchTo(String fxml) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource(fxml));
            // Keep the SAME Scene (and size). Just swap the root:
            Scene scene = loginButton.getScene();
            scene.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}