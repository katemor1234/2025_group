package ie.mtu.mtu_2025_demintia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        if (user.isEmpty() || pass.isEmpty()) {
            loginMessageLabel.setText("Please enter username and password!");
            return;
        }
        loginMessageLabel.setText("Checking...");
        validateLogin(user, pass);
    }

    @FXML
    public void cancelButtonOnAction(ActionEvent e){
        // navigate back to Home instead of closing the app
        ViewRouter.go("HomePage.fxml");
    }

    /** Navigate to CreateAccount.fxml when Register Here is clicked */
    @FXML
    public void onRegisterHereClick() {
        ViewRouter.go("CreateAccount.fxml");
    }

    private void validateLogin(String user, String pass) {
        String sql = "SELECT 1 FROM users WHERE username = ? AND password_hash = ? LIMIT 1";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, DatabaseConnection.hashPassword(pass));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    loginMessageLabel.setText("Welcome!");
                    ViewRouter.go("HomePage.fxml");
                } else {
                    loginMessageLabel.setText("Invalid Login. Please try again");
                    passwordPasswordField.clear();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            loginMessageLabel.setText("Error connecting to database");
        }
    }
}