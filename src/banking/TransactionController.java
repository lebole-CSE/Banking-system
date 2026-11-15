package banking;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionController {

    private final AccountService accountService;
    private final BankRepository repository;

    public TransactionController(
            AccountService accountService,
            BankRepository repository) {

        this.accountService = accountService;
        this.repository = repository;
    }

    public void deposit(Account account, BigDecimal amount) {

        accountService.deposit(account, amount);

        record(account, amount, TransactionType.DEPOSIT);
    }

    public void withdraw(Account account, BigDecimal amount) {

        accountService.withdraw(account, amount);

        record(account, amount, TransactionType.WITHDRAWAL);
    }

    public void record(
            Account account,
            BigDecimal amount,
            TransactionType type) {

        Transaction transaction = new Transaction(
            UUID.randomUUID().toString(),
            amount,
            type,
            account.getAccountNumber()
        );

        repository.saveTransaction(transaction);
    }
}