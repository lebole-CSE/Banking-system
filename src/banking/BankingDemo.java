package banking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class BankingDemo {

    public static void main(String[] args) {

        // Create repository
        BankRepository repository = new BankRepository();

        // Create bank
        Bank bank = new Bank(
            "Example Bank",
            "Gaborone Branch",
            repository
        );

        // Create services and controllers
        AccountService accountService = new AccountService();

        TransactionController transactions =
            new TransactionController(
                accountService,
                repository
            );

        BankController bankController =
            new BankController(bank, transactions);

        // Create an individual customer
        IndividualCustomer customer =
            new IndividualCustomer(
                "CUST001",
                "Gaborone",
                "71234567",
                "customer@example.com",
                "Mpho",
                "Molefe",
                "123456789",
                LocalDate.of(2000, 5, 10)
            );

        // Register customer
        bankController.registerCustomer(customer);

        // Open savings account
        Account savings =
            bankController.openAccount(
                customer,
                AccountType.SAVINGS,
                new BigDecimal("1000.00"),
                null
            );

        // Open investment account
        Account investment =
            bankController.openAccount(
                customer,
                AccountType.INVESTMENT,
                new BigDecimal("500.00"),
                null
            );

        // Employment details for cheque account
        EmploymentDetails job =
            new EmploymentDetails(
                "ABC Company",
                "Plot 123, Gaborone",
                "Accountant"
            );

        // Open cheque account
        Account cheque =
            bankController.openAccount(
                customer,
                AccountType.CHEQUE,
                new BigDecimal("0.00"),
                job
            );

        // Make deposits
        bankController.processDeposit(
            savings,
            new BigDecimal("200.00")
        );

        bankController.processDeposit(
            investment,
            new BigDecimal("100.00")
        );

        bankController.processDeposit(
            cheque,
            new BigDecimal("2500.00")
        );

        // Make withdrawal
        bankController.processWithdrawal(
            investment,
            new BigDecimal("50.00")
        );

        // Apply monthly interest
        InterestController interestController =
            new InterestController();

        interestController.applyInterest(
            Arrays.asList(savings, investment, cheque)
        );

        // Display account information
        for (Account account : customer.getAccounts()) {

            System.out.println(
                account.getClass().getSimpleName()
                + " | Account: "
                + account.getAccountNumber()
                + " | Balance: BWP "
                + account.getBalance()
            );
        }

        // Display transaction count
        System.out.println(
            "Recorded transactions: "
            + repository.getTransactions().size()
        );
    }
}