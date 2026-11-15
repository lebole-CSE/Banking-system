package banking;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvestmentAccount extends Account
        implements InterestBearing {

    private static final BigDecimal MONTHLY_RATE =
            new BigDecimal("0.05");

    private static final BigDecimal MINIMUM_OPENING_DEPOSIT =
            new BigDecimal("500.00");

    public InvestmentAccount(
            String accountNumber,
            String branch,
            Customer owner,
            BigDecimal openingDeposit) {

        super(accountNumber, branch, owner,
                validateOpeningDeposit(openingDeposit));
    }

    private static BigDecimal validateOpeningDeposit(
            BigDecimal amount) {

        if (amount == null ||
                amount.compareTo(MINIMUM_OPENING_DEPOSIT) < 0) {

            throw new IllegalArgumentException(
                "Investment account requires an opening deposit of at least BWP500.00."
            );
        }

        return amount;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        deduct(amount);
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