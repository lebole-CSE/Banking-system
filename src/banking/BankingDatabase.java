package banking;

public class BankingDatabase {

    private final BankRepository repository =
            new BankRepository();

    public BankRepository getRepository() {
        return repository;
    }
}