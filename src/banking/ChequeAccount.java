package banking;

import java.math.BigDecimal;

public class ChequeAccount extends Account {

    private EmploymentDetails employmentDetails;

    public ChequeAccount(
            String accountNumber,
            String branch,
            Customer owner,
            BigDecimal openingBalance,
            EmploymentDetails employmentDetails) {

        super(accountNumber, branch, owner, openingBalance);

        if (!(owner instanceof IndividualCustomer)) {
            throw new IllegalArgumentException(
                "A cheque account must be opened by an employed individual."
            );
        }

        if (employmentDetails == null) {
            throw new IllegalArgumentException(
                "Employment details are required."
            );
        }

        this.employmentDetails = employmentDetails;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        deduct(amount);
    }

    public EmploymentDetails getEmploymentDetails() {
        return employmentDetails;
    }
}