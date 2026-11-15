
package banking;

import java.math.BigDecimal;
import java.util.UUID;

public class Bank {

    private String name;
    private String branch;

    private final BankRepository repository;
    private final CustomerDAO customerDAO;
    private final AccountDAO accountDAO;

    public Bank(
            String name,
            String branch,
            BankRepository repository) {

        this.name = name;
        this.branch = branch;
        this.repository = repository;
        this.customerDAO = new CustomerDAO();
        this.accountDAO = new AccountDAO();
    }

    public void registerCustomer(Customer customer) {

        customerDAO.saveCustomer(customer);
        repository.saveCustomer(customer);
    }

    // Method overloading
    public Account openAccount(
            Customer customer,
            AccountType type) {

        return openAccount(
            customer, type, BigDecimal.ZERO, null
        );
    }

    public Account openAccount(
            Customer customer,
            AccountType type,
            BigDecimal openingBalance,
            EmploymentDetails employmentDetails) {

        if (repository.findCustomer(
                customer.getCustomerId()) == null) {

            registerCustomer(customer);
        }

        String accountNumber = UUID.randomUUID()
                .toString().substring(0, 12).toUpperCase();

        Account account;

        switch (type) {

            case SAVINGS:
                account = new SavingsAccount(
                    accountNumber,
                    branch,
                    customer,
                    openingBalance
                );
                break;

            case INVESTMENT:
                account = new InvestmentAccount(
                    accountNumber,
                    branch,
                    customer,
                    openingBalance
                );
                break;

            case CHEQUE:
                account = new ChequeAccount(
                    accountNumber,
                    branch,
                    customer,
                    openingBalance,
                    employmentDetails
                );
                break;

            default:
                throw new IllegalArgumentException(
                    "Unsupported account type."
                );
        }

        accountDAO.saveAccount(account);
        repository.saveAccount(account);

        return account;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }
}
