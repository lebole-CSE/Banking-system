
package banking;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.UUID;

public class DatabaseIntegrationTest {

    public static void main(String[] args) {

        // Create database tables
        DatabaseTables.createTables();

        // Create repository and bank
        BankRepository repository = new BankRepository();

        Bank bank = new Bank(
            "My Banking System",
            "Gaborone",
            repository
        );

        // Create a test customer
       String customerId = "TEST-" +
    UUID.randomUUID().toString().substring(0, 10);


        IndividualCustomer customer =
            new IndividualCustomer(
                customerId,
                "Gaborone",
                "71234567",
                "test@example.com",
                "Test",
                "Customer",
                "TEST123",
                LocalDate.of(2000, 1, 1)
            );

        // Register customer
        bank.registerCustomer(customer);

        // Open savings account
        Account account = bank.openAccount(
            customer,
            AccountType.SAVINGS,
            BigDecimal.ZERO,
            null
        );

        // Create transaction controller
        AccountService accountService = new AccountService();

        TransactionController transactionController =
            new TransactionController(
                accountService,
                repository
            );

        // Deposit money
        transactionController.deposit(
            account,
            new BigDecimal("1000.00")
        );

        // Verify saved information in H2
        try (Connection connection =
                 DatabaseConnection.getConnection()) {

            String accountSQL = """
                SELECT balance
                FROM accounts
                WHERE account_number = ?
            """;

            try (PreparedStatement statement =
                     connection.prepareStatement(accountSQL)) {

                statement.setString(
                    1, account.getAccountNumber());

                try (ResultSet result = statement.executeQuery()) {

                    if (result.next()) {
                        System.out.println(
                            "Database account balance: "
                            + result.getBigDecimal("balance"));
                    }
                }
            }

            String transactionSQL = """
                SELECT COUNT(*)
                FROM transactions
                WHERE account_number = ?
            """;

            try (PreparedStatement statement =
                     connection.prepareStatement(transactionSQL)) {

                statement.setString(
                    1, account.getAccountNumber());

                try (ResultSet result = statement.executeQuery()) {

                    if (result.next()) {
                        System.out.println(
                            "Saved transactions: "
                            + result.getInt(1));
                    }
                }
            }

            System.out.println(
                "DATABASE INTEGRATION TEST COMPLETED.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
