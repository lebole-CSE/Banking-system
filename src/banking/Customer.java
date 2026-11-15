package banking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Customer {

    private String customerId;
    private String address;
    private String phoneNumber;
    private String email;

    private final List<Account> accounts = new ArrayList<>();

    public Customer(String customerId, String address,
                    String phoneNumber, String email) {

        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID is required.");
        }

        this.customerId = customerId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void addAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }

        if (account.getOwner() != this) {
            throw new IllegalArgumentException(
                "Account must belong to this customer."
            );
        }

        if (!accounts.contains(account)) {
            accounts.add(account);
        }
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public abstract String getCustomerName();
}