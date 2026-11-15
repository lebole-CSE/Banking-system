
package banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class CustomerDAO {

    public void saveCustomer(Customer customer) {

        String customerSQL = """
            INSERT INTO customers
            (customer_id, address, phone_number, email, customer_type)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            try {
                String customerType =
                    customer instanceof IndividualCustomer
                    ? "INDIVIDUAL" : "COMPANY";

                try (PreparedStatement statement =
                         connection.prepareStatement(customerSQL)) {

                    statement.setString(
                        1, customer.getCustomerId());

                    statement.setString(
                        2, customer.getAddress());

                    statement.setString(
                        3, customer.getPhoneNumber());

                    statement.setString(
                        4, customer.getEmail());

                    statement.setString(
                        5, customerType);

                    statement.executeUpdate();
                }

                if (customer instanceof IndividualCustomer individual) {

                    String individualSQL = """
                        INSERT INTO individual_customers
                        (customer_id, first_name, surname,
                         national_id, date_of_birth)
                        VALUES (?, ?, ?, ?, ?)
                    """;

                    try (PreparedStatement statement =
                             connection.prepareStatement(individualSQL)) {

                        statement.setString(
                            1, individual.getCustomerId());

                        statement.setString(
                            2, individual.getFirstName());

                        statement.setString(
                            3, individual.getSurname());

                        statement.setString(
                            4, individual.getNationalId());

                        statement.setDate(
                            5, individual.getDateOfBirth() == null
                               ? null
                               : Date.valueOf(
                                   individual.getDateOfBirth()));

                        statement.executeUpdate();
                    }

                } else if (customer instanceof CompanyCustomer company) {

                    String companySQL = """
                        INSERT INTO company_customers
                        (customer_id, company_name,
                         registration_number,
                         tax_identification_number, business_type)
                        VALUES (?, ?, ?, ?, ?)
                    """;

                    try (PreparedStatement statement =
                             connection.prepareStatement(companySQL)) {

                        statement.setString(
                            1, company.getCustomerId());

                        statement.setString(
                            2, company.getCompanyName());

                        statement.setString(
                            3, company.getRegistrationNumber());

                        statement.setString(
                            4, company.getTaxIdentificationNumber());

                        statement.setString(
                            5, company.getBusinessType());

                        statement.executeUpdate();
                    }
                }

                connection.commit();

                System.out.println(
                    "Customer saved successfully.");

            } catch (SQLException | RuntimeException e) {

                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                "Failed to save customer: " + e.getMessage(), e);
        }
    }
}
