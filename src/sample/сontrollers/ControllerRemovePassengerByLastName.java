package sample.сontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerRemovePassengerByLastName {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField lastNameTextField;


    @FXML
    private Button removeButton;

    @FXML
    private Button cancelButton;

    static String lastName;


    @FXML
    void initialize() {

    }

    @FXML
    void removePassenger(final ActionEvent event) {
        boolean fail = false;
        String failtext = "";
        if (lastNameTextField.getText().matches("([A-Z][a-z]+)")) {
            lastName = lastNameTextField.getText();
            lastNameTextField.setStyle("-fx-border-color: defult");
        } else {
            lastNameTextField.setStyle("-fx-border-color: red");
            fail = true;
            failtext += "\nLast name isn't correct.";
        }
        if (fail) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(failtext);
            alert.show();
        } else {
            closeScene();
        }
    }

    @FXML
    void closeScene(final ActionEvent event) {
        final Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void closeScene() {
        final Stage stage = (Stage) removeButton.getScene().getWindow();
        stage.close();
    }
}

