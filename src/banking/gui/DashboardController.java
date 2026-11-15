
package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblSavingsBalance;

    @FXML
    private Label lblInvestmentBalance;

    @FXML
    private Label lblChequeBalance;

    @FXML
    public void initialize() {

        lblCustomerName.setText("Welcome, Customer");

        // Temporary display values
        lblSavingsBalance.setText("BWP 0.00");
        lblInvestmentBalance.setText("BWP 0.00");
        lblChequeBalance.setText("BWP 0.00");
    }

    @FXML
    private void openAccounts() {
        BankingApplication.changeScene("AccountView.fxml");
    }

    @FXML
    private void openTransactions() {
        BankingApplication.changeScene("TransactionView.fxml");
    }

    @FXML
    private void openNewAccount() {
        BankingApplication.changeScene("OpenAccountView.fxml");
    }

    @FXML
    private void logout() {
        BankingApplication.changeScene("LoginView.fxml");
    }
}
