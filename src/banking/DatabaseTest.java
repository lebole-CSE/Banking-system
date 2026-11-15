
package banking;

public class DatabaseTest {

    public static void main(String[] args) {

        System.out.println("Testing database connection...");

        DatabaseTables.createTables();

        System.out.println("Database test completed.");
    }
}
