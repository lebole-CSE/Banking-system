package banking;

import java.math.BigDecimal;

public interface InterestBearing {

    BigDecimal calculateInterest();

    void applyInterest();
}