package banking;

import java.util.List;

public class InterestController {

    public void applyInterest(List<Account> accounts) {

        for (Account account : accounts) {

            if (account instanceof InterestBearing) {

                ((InterestBearing) account).applyInterest();
            }
        }
    }
}