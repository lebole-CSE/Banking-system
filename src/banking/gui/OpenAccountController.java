
package gui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.math.BigDecimal;

public class OpenAccountController {

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtSurname;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private ComboBox<String> cmbAccountType;

    @FXML
    private TextField txtInitialDeposit;

    @FXML
    private TextField txtBranch;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtCompanyAddress;

    @FXML
    private Label lblAccountMessage;

    @FXML
    public void initialize() {

        cmbAccountType.getItems().addAll(
            "Savings Account",
            "Investment Account",
            "Cheque Account"
        );
    }

    @FXML
    private void createAccount() {

        if (txtFirstName.getText().isEmpty()
                || txtSurname.getText().isEmpty()
                || txtCustomerId.getText().isEmpty()
                || cmbAccountType.getValue() == null
                || txtInitialDeposit.getText().isEmpty()
                || txtBranch.getText().isEmpty()) {

            lblAccountMessage.setText(
                "Please complete all required fields."
            );
            return;
        }

        try {

            BigDecimal deposit =
                new BigDecimal(txtInitialDeposit.getText());

            if (deposit.compareTo(BigDecimal.ZERO) < 0) {

                lblAccountMessage.setText(
                    "Deposit cannot be negative."
                );
                return;
            }

            String accountType = cmbAccountType.getValue();

            if (accountType.equals("Investment Account")
                    && deposit.compareTo(new BigDecimal("500")) < 0) {

                lblAccountMessage.setText(
                    "Investment accounts require at least BWP500."
                );
                return;
            }

            if (accountType.equals("Cheque Account")
                    && (txtCompanyName.getText().isEmpty()
                    || txtCompanyAddress.getText().isEmpty())) {

                lblAccountMessage.setText(
                    "Enter your company name and address."
                );
                return;
            }

            lblAccountMessage.setText(
                "Information validated. Account creation "
                + "will be connected to the banking system next."
            );

        } catch (NumberFormatException e) {

            lblAccountMessage.setText(
                "Please enter a valid deposit amount."
            );
        }
    }

    @FXML
    private void clearAccount() {

        txtFirstName.clear();
        txtSurname.clear();
        txtCustomerId.clear();
        txtPhoneNumber.clear();
        cmbAccountType.setValue(null);
        txtInitialDeposit.clear();
        txtBranch.clear();
        txtCompanyName.clear();
        txtCompanyAddress.clear();

        lblAccountMessage.setText("");
    }

    @FXML
    private void backToDashboard() {
        BankingApplication.changeScene("DashboardView.fxml");
    }
}
