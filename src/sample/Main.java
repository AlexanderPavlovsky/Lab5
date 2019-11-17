package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("FXML/sample.fxml"));
        primaryStage.setTitle("Airplanes passengers");
        primaryStage.setScene(new Scene(root, 850, 400));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });

    }

    public static void addToList() {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/sample/FXML/addPassenger.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Parent root = loader.getRoot();
        final Stage stage = new Stage();
        stage.setScene(new Scene(root, 600, 400));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
    public static void removeByLastName () {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/sample/FXML/removePassengerByLastName.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Parent root = loader.getRoot();
        final Stage stage = new Stage();
        stage.setScene(new Scene(root, 260, 140));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
    public static void locationOfBaggage (){
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/sample/FXML/locationOfBaggage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Parent root = loader.getRoot();
        final Stage stage = new Stage();
        stage.setScene(new Scene(root, 300, 140));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }


    public static void main(final String[] args) {
        launch(args);
    }
}
