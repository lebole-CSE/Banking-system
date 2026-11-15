package banking;

import java.math.BigDecimal;

public class BankController {

    private final Bank bank;
    private final TransactionController transactionController;

    public BankController(
            Bank bank,
            TransactionController transactionController) {

        this.bank = bank;
        this.transactionController = transactionController;
    }

    public void registerCustomer(Customer customer) {
        bank.registerCustomer(customer);
    }

    public Account openAccount(
            Customer customer,
            AccountType type) {

        return bank.openAccount(customer, type);
    }

    public Account openAccount(
            Customer customer,
            AccountType type,
            BigDecimal openingBalance,
            EmploymentDetails employmentDetails) {

        return bank.openAccount(
            customer, type, openingBalance, employmentDetails
        );
    }

    public void processDeposit(
            Account account,
            BigDecimal amount) {

        transactionController.deposit(account, amount);
    }

    public void processWithdrawal(
            Account account,
            BigDecimal amount) {

        transactionController.withdraw(account, amount);
    }
}