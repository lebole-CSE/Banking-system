
package banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountDAO {

    public void saveAccount(Account account) {

        String employmentSQL = """
            INSERT INTO employment_details
            (company, company_address, job_title)
            VALUES (?, ?, ?)
        """;

        String accountSQL = """
            INSERT INTO accounts
            (account_number, branch, balance, status,
             account_type, customer_id, employment_id)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            try {
                Long employmentId = null;

                if (account instanceof ChequeAccount cheque) {

                    EmploymentDetails employment =
                        cheque.getEmploymentDetails();

                    try (PreparedStatement statement =
                             connection.prepareStatement(
                                 employmentSQL,
                                 Statement.RETURN_GENERATED_KEYS)) {

                        statement.setString(
                            1, employment.getCompany());

                        statement.setString(
                            2, employment.getCompanyAddress());

                        statement.setString(
                            3, employment.getJobTitle());

                        statement.executeUpdate();

                        try (ResultSet keys =
                                 statement.getGeneratedKeys()) {

                            if (keys.next()) {
                                employmentId = keys.getLong(1);
                            } else {
                                throw new SQLException(
                                    "Could not generate employment ID.");
                            }
                        }
                    }
                }

                try (PreparedStatement statement =
                         connection.prepareStatement(accountSQL)) {

                    statement.setString(
                        1, account.getAccountNumber());

                    statement.setString(
                        2, account.getBranch());

                    statement.setBigDecimal(
                        3, account.getBalance());

                    statement.setString(
                        4, account.getStatus());

                    statement.setString(
                        5, account instanceof SavingsAccount
                            ? "SAVINGS"
                            : account instanceof InvestmentAccount
                                ? "INVESTMENT" : "CHEQUE");

                    statement.setString(
                        6, account.getOwner().getCustomerId());

                    if (employmentId == null) {
                        statement.setNull(7, java.sql.Types.BIGINT);
                    } else {
                        statement.setLong(7, employmentId);
                    }

                    statement.executeUpdate();
                }

                connection.commit();

                System.out.println(
                    "Account saved successfully.");

            } catch (SQLException | RuntimeException e) {

                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                "Failed to save account: " + e.getMessage(), e);
        }
    }


public void updateBalance(Account account) {

    String sql = """
        UPDATE accounts
        SET balance = ?
        WHERE account_number = ?
    """;

    try (Connection connection =
                 DatabaseConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setBigDecimal(
            1, account.getBalance());

        statement.setString(
            2, account.getAccountNumber());

        int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated == 0) {
            throw new SQLException(
                "Account was not found in the database.");
        }

    } catch (SQLException e) {

        throw new RuntimeException(
            "Failed to update account balance: "
            + e.getMessage(), e);
    }
}

}
