
package banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {

    public void saveTransaction(Transaction transaction) {

        String sql = """
            INSERT INTO transactions
            (transaction_id, amount, transaction_type,
             account_number, transaction_timestamp)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                1, transaction.getTransactionId());

            statement.setBigDecimal(
                2, transaction.getAmount());

            statement.setString(
                3, transaction.getType().name());

            statement.setString(
                4, transaction.getAccountNumber());

            statement.setTimestamp(
                5, java.sql.Timestamp.valueOf(
                    transaction.getTimestamp()));

            statement.executeUpdate();

            System.out.println(
                "Transaction saved successfully.");

        } catch (SQLException e) {

            throw new RuntimeException(
                "Failed to save transaction: " + e.getMessage(), e);
        }
    }
}
