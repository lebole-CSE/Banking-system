package banking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.math.RoundingMode;

public class Transaction {

    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private TransactionType type;
    private String accountNumber;

    public Transaction(
            String transactionId,
            BigDecimal amount,
            TransactionType type,
            String accountNumber) {

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                "Transaction amount must be positive."
            );
        }

        this.transactionId = transactionId;
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.type = type;
        this.accountNumber = accountNumber;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}