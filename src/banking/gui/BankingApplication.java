
package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BankingApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {

        primaryStage = stage;

        changeScene("LoginView.fxml");

        primaryStage.setTitle("Banking System");
        primaryStage.show();
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
