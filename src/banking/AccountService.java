package banking;

import java.math.BigDecimal;

public class AccountService {

    public void deposit(Account account, BigDecimal amount) {
        account.deposit(amount);
    }

    public void withdraw(Account account, BigDecimal amount) {
        account.withdraw(amount);
    }
}