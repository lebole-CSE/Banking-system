
package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblLoginMessage;

    @FXML
    private void handleLogin() {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        // Temporary login details for testing
        if (username.equals("admin") && password.equals("1234")) {

            BankingApplication.changeScene("DashboardView.fxml");

        } else {

            lblLoginMessage.setText(
                "Incorrect username or password. Try again."
            );
        }
    }
}
