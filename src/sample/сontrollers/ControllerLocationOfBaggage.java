package sample.сontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLocationOfBaggage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField numberBaggageTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Button cancelButton;

    static String numberBaggage;

    @FXML
    void initialize() {

    }

    @FXML
    void searchLocationOfBaggage(ActionEvent event) {
        boolean fail = false;
        String failtext = "";
        if (numberBaggageTextField.getText().matches("([A-Z][0-9]{7})")) {
            numberBaggage = numberBaggageTextField.getText();
            numberBaggageTextField.setStyle("-fx-border-color: defult");
        } else {
            numberBaggageTextField.setStyle("-fx-border-color: red");
            fail = true;
            failtext += "\nError number baggage! Example: S5896234, F1523465.";
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
        final Stage stage = (Stage) searchButton.getScene().getWindow();
        stage.close();
    }
}
