package ie.mtu.mtu_2025_demintia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML private Button cancelButton;
    @FXML private Label loginMessageLabel;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;

    @FXML
    public void loginButtonOnAction(ActionEvent e) {
        String user = usernameTextField.getText().trim();
        String pass = passwordPasswordField.getText();
        if (!user.isEmpty() && !pass.isEmpty()) {
            loginMessageLabel.setText("Checking...");
            validateLogin(user, pass);
        } else {
            loginMessageLabel.setText("Please enter username and password!");
        }
    }

    @FXML
    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void validateLogin(String user, String pass) {
        String sql = "SELECT 1 FROM users WHERE username = ? AND password_hash = ? LIMIT 1";

        try (Connection connectDB = DatabaseConnection.getConnection();
             PreparedStatement ps = connectDB.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, DatabaseConnection.hashPassword(pass));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    loginMessageLabel.setText("Welcome!");
                    // Keep SAME Scene (and size) — just swap the root:
                    Parent newRoot = FXMLLoader.load(Main.class.getResource("HomePage.fxml"));
                    Scene scene = loginMessageLabel.getScene();
                    scene.setRoot(newRoot);
                } else {
                    loginMessageLabel.setText("Invalid Login. Please try again");
                    passwordPasswordField.clear(); // optional UX
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            loginMessageLabel.setText("Error connecting to database");
        }
    }
}