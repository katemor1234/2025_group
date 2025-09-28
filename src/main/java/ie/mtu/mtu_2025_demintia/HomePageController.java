package ie.mtu.mtu_2025_demintia;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomePageController {
    @FXML private Button loginButton;
    @FXML private Button registerButton;

    @FXML
    private void loginButtonOnAction() {
        ViewRouter.go("Login.fxml");
    }

    @FXML
    private void registerButtonOnAction() {
        ViewRouter.go("CreateAccount.fxml");
    }
}