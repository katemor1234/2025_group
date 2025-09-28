package ie.mtu.mtu_2025_demintia;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreateAccountController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    private void onCreateClick() {
        String u = usernameField.getText().trim();
        String p = passwordField.getText();
        if (u.isEmpty() || p.isEmpty()) {
            messageLabel.setText("Enter username and password");
            return;
        }
        String sql = "INSERT INTO users(username, password_hash) VALUES(?, ?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u);
            ps.setString(2, DatabaseConnection.hashPassword(p));
            ps.executeUpdate();
            messageLabel.setText("Account created ✓");
            ViewRouter.go("Login.fxml");
        } catch (Exception ex) {
            messageLabel.setText("Username exists or DB error");
        }
    }

    @FXML
    private void onBackToLogin() {
        ViewRouter.go("Login.fxml");
    }
}