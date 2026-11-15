package banking;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class BankRepository {

    private final Map<String, Customer> customers =
            new LinkedHashMap<>();

    private final Map<String, Account> accounts =
            new LinkedHashMap<>();

    private final Map<String, Transaction> transactions =
            new LinkedHashMap<>();

    public void saveCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public Customer findCustomer(String customerId) {
        return customers.get(customerId);
    }

    public void saveAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void saveTransaction(Transaction transaction) {
        transactions.put(
            transaction.getTransactionId(), transaction
        );
    }

    public Collection<Customer> getCustomers() {
        return customers.values();
    }

    public Collection<Account> getAccounts() {
        return accounts.values();
    }

    public Collection<Transaction> getTransactions() {
        return transactions.values();
    }
}