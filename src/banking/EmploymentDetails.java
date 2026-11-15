package banking;

public class EmploymentDetails {

    private String company;
    private String companyAddress;
    private String jobTitle;

    // Constructor overloading
    public EmploymentDetails(
            String company,
            String companyAddress) {

        this(company, companyAddress, "Not specified");
    }

    public EmploymentDetails(
            String company,
            String companyAddress,
            String jobTitle) {

        if (company == null || company.trim().isEmpty()
                || companyAddress == null
                || companyAddress.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Company and company address are required."
            );
        }

        this.company = company;
        this.companyAddress = companyAddress;
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}