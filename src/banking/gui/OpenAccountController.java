
package gui;

import banking.Account;
import banking.AccountType;
import banking.IndividualCustomer;
import banking.EmploymentDetails;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OpenAccountController {

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtSurname;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtNationalId;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtAddress;

    @FXML
    private DatePicker dpDateOfBirth;

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
    private TextField txtJobTitle;

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

        // Check required customer and account details
        if (txtFirstName.getText().isBlank()
                || txtSurname.getText().isBlank()
                || txtCustomerId.getText().isBlank()
                || txtNationalId.getText().isBlank()
                || txtPhoneNumber.getText().isBlank()
                || txtEmail.getText().isBlank()
                || txtAddress.getText().isBlank()
                || dpDateOfBirth.getValue() == null
                || cmbAccountType.getValue() == null
                || txtInitialDeposit.getText().isBlank()
                || txtBranch.getText().isBlank()) {

            lblAccountMessage.setText(
                "Please complete all required fields."
            );
            return;
        }

        try {

            // Get the opening deposit
            BigDecimal deposit =
                new BigDecimal(txtInitialDeposit.getText().trim());

            if (deposit.compareTo(BigDecimal.ZERO) < 0) {
                lblAccountMessage.setText(
                    "Deposit cannot be negative."
                );
                return;
            }

            // Check date of birth
            LocalDate dateOfBirth = dpDateOfBirth.getValue();

            if (dateOfBirth.isAfter(LocalDate.now())) {
                lblAccountMessage.setText(
                    "Date of birth cannot be in the future."
                );
                return;
            }

            // Identify the selected account type
            String selectedType = cmbAccountType.getValue();

            AccountType accountType;

            switch (selectedType) {

                case "Savings Account":
                    accountType = AccountType.SAVINGS;
                    break;

                case "Investment Account":
                    accountType = AccountType.INVESTMENT;

                    if (deposit.compareTo(
                            new BigDecimal("500")) < 0) {

                        lblAccountMessage.setText(
                            "Investment accounts require at least BWP500."
                        );
                        return;
                    }
                    break;

                case "Cheque Account":
                    accountType = AccountType.CHEQUE;

                    if (txtCompanyName.getText().isBlank()
                            || txtCompanyAddress.getText().isBlank()) {

                        lblAccountMessage.setText(
                            "Enter your company name and address."
                        );
                        return;
                    }
                    break;

                default:
                    lblAccountMessage.setText(
                        "Invalid account type selected."
                    );
                    return;
            }

            // Create the customer
            IndividualCustomer customer =
                new IndividualCustomer(
                    txtCustomerId.getText().trim(),
                    txtAddress.getText().trim(),
                    txtPhoneNumber.getText().trim(),
                    txtEmail.getText().trim(),
                    txtFirstName.getText().trim(),
                    txtSurname.getText().trim(),
                    txtNationalId.getText().trim(),
                    dateOfBirth
                );

            // Create employment details for cheque accounts
            EmploymentDetails employmentDetails = null;

            if (accountType == AccountType.CHEQUE) {

                employmentDetails = new EmploymentDetails(
                    txtCompanyName.getText().trim(),
                    txtCompanyAddress.getText().trim(),
                    txtJobTitle.getText().isBlank()
                        ? "Not specified"
                        : txtJobTitle.getText().trim()
                );
            }

            // Open and save the account through the Bank
            Account account = BankingApplication.getBank().openAccount(
                customer,
                accountType,
                deposit,
                employmentDetails
            );

            // Display confirmation
            lblAccountMessage.setText(
                "Account created successfully!\n"
                + "Customer: " + customer.getCustomerName() + "\n"
                + "Account Number: " + account.getAccountNumber() + "\n"
                + "Account Type: " + selectedType + "\n"
                + "Opening Balance: BWP "
                + account.getBalance()
            );

        } catch (NumberFormatException e) {

            lblAccountMessage.setText(
                "Please enter a valid deposit amount."
            );

        } catch (Exception e) {

            e.printStackTrace();

            lblAccountMessage.setText(
                "Account creation failed: " + e.getMessage()
            );
        }
    }

    @FXML
    private void clearAccount() {

        txtFirstName.clear();
        txtSurname.clear();
        txtCustomerId.clear();
        txtNationalId.clear();
        txtPhoneNumber.clear();
        txtEmail.clear();
        txtAddress.clear();

        dpDateOfBirth.setValue(null);

        cmbAccountType.setValue(null);

        txtInitialDeposit.clear();
        txtBranch.clear();

        txtCompanyName.clear();
        txtCompanyAddress.clear();
        txtJobTitle.clear();

        lblAccountMessage.setText("");
    }

    @FXML
    private void backToDashboard() {

        BankingApplication.changeScene("DashboardView.fxml");
    }
}
