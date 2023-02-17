package cim.calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Calc.fxml"));
        GridPane root = loader.load();
        Scene scene = new Scene(root);
        AppController controller = loader.getController();

        // We set the key actions here as the scene isn't loaded in the controller at initialization.
        root.setOnKeyPressed(controller.setKeyActions());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    

}