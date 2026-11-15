
package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class AccountController {

    @FXML
    private TableView<?> tblAccounts;

    @FXML
    private Label lblAccountNumber;

    @FXML
    private Label lblAccountType;

    @FXML
    private Label lblAccountBalance;

    @FXML
    private Label lblAccountBranch;

    @FXML
    public void initialize() {

        // Default information before accounts are loaded
        lblAccountNumber.setText("-");
        lblAccountType.setText("-");
        lblAccountBalance.setText("BWP 0.00");
        lblAccountBranch.setText("-");
    }

    @FXML
    private void backToDashboard() {
        BankingApplication.changeScene("DashboardView.fxml");
    }

    @FXML
    private void refreshAccounts() {
        // Account information will be loaded from the banking system.
    }

    @FXML
    private void deposit() {
        BankingApplication.changeScene("TransactionView.fxml");
    }

    @FXML
    private void withdraw() {
        BankingApplication.changeScene("TransactionView.fxml");
    }
}
