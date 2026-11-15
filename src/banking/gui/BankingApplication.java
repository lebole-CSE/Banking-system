
package gui;

import banking.Bank;
import banking.BankRepository;
import banking.AccountService;
import banking.DatabaseTables;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BankingApplication extends Application {

    private static Stage primaryStage;

    // Shared banking objects
    private static BankRepository repository;
    private static Bank bank;
    private static banking.TransactionController transactionController;

    @Override
    public void start(Stage stage) throws IOException {

        primaryStage = stage;

        // Create database tables
        DatabaseTables.createTables();

        // Create shared banking services
        repository = new BankRepository();

        bank = new Bank(
            "Banking System",
            "Main Branch",
            repository
        );

        AccountService accountService = new AccountService();

        transactionController = new banking.TransactionController(
            accountService,
            repository
        );

        // Load login screen
        changeScene("LoginView.fxml");

        primaryStage.setTitle("Banking System");
        primaryStage.show();
    }

    public static Bank getBank() {
        return bank;
    }

    public static BankRepository getRepository() {
        return repository;
    }

    public static banking.TransactionController getTransactionController() {
        return transactionController;
    }

    public static void changeScene(String fxmlFile) {

        try {

            FXMLLoader loader = new FXMLLoader(
                BankingApplication.class.getResource(fxmlFile)
            );

            Parent root = loader.load();

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Banking System");
            primaryStage.centerOnScreen();

        } catch (IOException | RuntimeException e) {

            e.printStackTrace();

            System.out.println(
                "Could not load screen: " + fxmlFile
            );
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
