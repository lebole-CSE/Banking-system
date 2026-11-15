package banking;

import java.time.LocalDate;

public class IndividualCustomer extends Customer {

    private String firstName;
    private String surname;
    private String nationalId;
    private LocalDate dateOfBirth;

    public IndividualCustomer(
            String customerId,
            String address,
            String phoneNumber,
            String email,
            String firstName,
            String surname,
            String nationalId,
            LocalDate dateOfBirth) {

        super(customerId, address, phoneNumber, email);

        this.firstName = firstName;
        this.surname = surname;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getCustomerName() {
        return firstName + " " + surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getNationalId() {
        return nationalId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}