package banking;

public class CompanyCustomer extends Customer {

    private String companyName;
    private String registrationNumber;
    private String taxIdentificationNumber;
    private String businessType;

    public CompanyCustomer(
            String customerId,
            String address,
            String phoneNumber,
            String email,
            String companyName,
            String registrationNumber,
            String taxIdentificationNumber,
            String businessType) {

        super(customerId, address, phoneNumber, email);

        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.businessType = businessType;
    }

    @Override
    public String getCustomerName() {
        return companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public String getBusinessType() {
        return businessType;
    }
}