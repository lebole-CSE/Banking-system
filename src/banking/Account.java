package banking;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Account {

    private String accountNumber;
    private BigDecimal balance;
    private String branch;
    private String status;
    private Customer owner;

    protected Account(
            String accountNumber,
            String branch,
            Customer owner,
            BigDecimal openingBalance) {

        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number is required.");
        }

        if (owner == null) {
            throw new IllegalArgumentException("An account must have an owner.");
        }

        if (openingBalance == null ||
                openingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                "Opening balance cannot be negative."
            );
        }

        this.accountNumber = accountNumber;
        this.branch = branch;
        this.owner = owner;
        this.balance = money(openingBalance);
        this.status = "ACTIVE";

        owner.addAccount(this);
    }

    public void deposit(BigDecimal amount) {

        validatePositiveAmount(amount);

        balance = balance.add(money(amount));
    }

    public abstract void withdraw(BigDecimal amount);

    protected void deduct(BigDecimal amount) {

        validatePositiveAmount(amount);

        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        balance = balance.subtract(money(amount));
    }

    protected void validatePositiveAmount(BigDecimal amount) {

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                "Amount must be greater than zero."
            );
        }
    }

    protected BigDecimal money(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getBranch() {
        return branch;
    }

    public String getStatus() {
        return status;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}