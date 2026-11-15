package banking;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingsAccount extends Account
        implements InterestBearing {

    private static final BigDecimal MONTHLY_RATE =
            new BigDecimal("0.0005");

    public SavingsAccount(
            String accountNumber,
            String branch,
            Customer owner,
            BigDecimal openingBalance) {

        super(accountNumber, branch, owner, openingBalance);
    }

    @Override
    public void withdraw(BigDecimal amount) {

        throw new UnsupportedOperationException(
            "Withdrawals are not permitted on a savings account."
        );
    }

    @Override
    public BigDecimal calculateInterest() {

        return getBalance()
                .multiply(MONTHLY_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void applyInterest() {

        BigDecimal interest = calculateInterest();

        if (interest.compareTo(BigDecimal.ZERO) > 0) {
            deposit(interest);
        }
    }
}