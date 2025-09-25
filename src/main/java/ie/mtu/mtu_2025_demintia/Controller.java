package ie.mtu.mtu_2025_demintia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
// import org.controlsfx.control.action.Action;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction(ActionEvent e){
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            loginMessageLabel.setText("You try to login!");
            validateLogin();
        }
        else {
            loginMessageLabel.setText("Please enter username and password!");
        }
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        //DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = DatabaseConnection.getInstance().getConnection();

        String verifyLogin = "SELECT count(1) FROM userAccounts WHERE Username = '" + usernameTextField.getText() + "' AND Password = '" + passwordPasswordField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryesult = statement.executeQuery(verifyLogin);

            while(queryesult.next()){
                if (queryesult.getInt(1) == 1){ // should be only one unique username
                    loginMessageLabel.setText("Welcome!");
                }
                else {
                    loginMessageLabel.setText("Invalid Login. Please try again");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
