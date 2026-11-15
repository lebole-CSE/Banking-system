
package gui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.math.BigDecimal;

public class TransactionController {

    @FXML
    private ComboBox<String> cmbTransactionAccount;

    @FXML
    private ComboBox<String> cmbTransactionType;

    @FXML
    private TextField txtTransactionAmount;

    @FXML
    private Label lblTransactionMessage;

    @FXML
    public void initialize() {

        cmbTransactionAccount.getItems().addAll(
            "Savings Account",
            "Investment Account",
            "Cheque Account"
        );

        cmbTransactionType.getItems().addAll(
            "Deposit",
            "Withdrawal"
        );
    }

    @FXML
    private void makeDeposit() {
        processTransaction("Deposit");
    }

    @FXML
    private void makeWithdrawal() {
        processTransaction("Withdrawal");
    }

    private void processTransaction(String type) {

        String account = cmbTransactionAccount.getValue();
        String amountText = txtTransactionAmount.getText();

        if (account == null || amountText.isEmpty()) {

            lblTransactionMessage.setText(
                "Please select an account and enter an amount."
            );
            return;
        }

        try {

            BigDecimal amount = new BigDecimal(amountText);

            if (amount.compareTo(BigDecimal.ZERO) <= 0) {

                lblTransactionMessage.setText(
                    "Amount must be greater than zero."
                );
                return;
            }

            lblTransactionMessage.setText(
                type + " request received for " + account
                + ". Amount: BWP " + amount
                + ". Banking processing will be connected next."
            );

        } catch (NumberFormatException e) {

            lblTransactionMessage.setText(
                "Please enter a valid numeric amount."
            );
        }
    }

    @FXML
    private void clearTransaction() {

        cmbTransactionAccount.setValue(null);
        cmbTransactionType.setValue(null);
        txtTransactionAmount.clear();
        lblTransactionMessage.setText("");
    }

    @FXML
    private void backToDashboard() {
        BankingApplication.changeScene("DashboardView.fxml");
    }
}
