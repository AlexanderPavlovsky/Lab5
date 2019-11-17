package sample.сontrollers;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import sample.classes.Passenger;
import sample.classes.Passengers;

public class AddController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField patronymicTextField;

    @FXML
    private TextField numberFlightTextField;

    @FXML
    private TextField numberBaggageTextField;

    @FXML
    private TextField quantityPlaceTextField;

    @FXML
    private TextField massesOfBaggageTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<LevelOfPlace> levelOfPlaceChoiceBox;

    @FXML
    private ChoiceBox<LevelOfBaggage> levelOfBaggageChoiceBox;

    static Passengers passengers = new Passengers();

    @FXML
    void initialize() {
        levelOfPlaceChoiceBox.setItems(FXCollections.observableArrayList(LevelOfPlace.values()));
        levelOfPlaceChoiceBox.setValue(LevelOfPlace.FIRST);
        levelOfBaggageChoiceBox.setItems(FXCollections.observableArrayList(LevelOfBaggage.values()));
        levelOfBaggageChoiceBox.setValue(LevelOfBaggage.HANDLUGGAGE);
    }

    @FXML
    void createPassenger(final ActionEvent event) {
        String name = null, lastName = null, patronymic = null, numberFlight = null, numberBaggage = null;
        int quantityPlace = 0, sumMassOfBaggage = 0;
        boolean fail = false;
        String failtext = "";
        if (nameTextField.getText().matches("([A-Z][a-z]+)")) {
            name = nameTextField.getText();
            nameTextField.setStyle("-fx-border-color: defult");
        } else {
            nameTextField.setStyle("-fx-border-color: red");
            fail = true;
            failtext += "Name isn't correct.";
        }
        if (lastNameTextField.getText().matches("([A-Z][a-z]+)")) {
            lastName = lastNameTextField.getText();
            lastNameTextField.setStyle("-fx-border-color: defult");
        } else {
            lastNameTextField.setStyle("-fx-border-color: red");
            fail = true;
            failtext += "\nLast name isn't correct.";
        }
        if (patronymicTextField.getText().matches("([A-Z][a-z]+)")) {
            patronymic = patronymicTextField.getText();
            patronymicTextField.setStyle("-fx-border-color: defult");
        } else {
            patronymicTextField.setStyle("-fx-border-color: red");
            fail = true;
            failtext += "\nPatronymic isn't correct.";
        }
        if (numberFlightTextField.getText().matches("(([A-Z]{2}|[A-Z][0-9])-[0-9]{3,5})")) {
            numberFlight = numberFlightTextField.getText();
            numberFlightTextField.setStyle("-fx-border-color: defult");
        } else {
            numberFlightTextField.setStyle("-fx-border-color: red");
            fail = true;
            failtext += "\nError number flight! Example: S7-206, EK-7892.";
        }
        if (numberBaggageTextField.getText().matches("([A-Z][0-9]{7})")) {
            numberBaggage = numberBaggageTextField.getText();
            numberBaggageTextField.setStyle("-fx-border-color: defult");
        } else {
            numberBaggageTextField.setStyle("-fx-border-color: red");
            fail = true;
            failtext += "\nError number baggage! Example: S5896234, F1523465.";
        }
        try {
            quantityPlace = Integer.parseInt(quantityPlaceTextField.getText());
            if (quantityPlace < 0) {
                quantityPlaceTextField.setStyle("-fx-border-color: red");
                fail = true;
                failtext += "\nQuantity place is less than zero.";
            } else {
                quantityPlaceTextField.setStyle("-fx-border-color: defult");
            }
        } catch (Exception exc) {
            quantityPlaceTextField.setStyle("-fx-border-color: red");
            fail = true;
            failtext += "\nQuantity place isn't Integer.";
        }
        final ArrayList<String> mass = new ArrayList<>(Arrays.asList(massesOfBaggageTextField.getText().split(";")));
        if (mass.size() == quantityPlace) {
            for (final String s : mass) {
                try {
                    final int Mass = Integer.parseInt(s);
                    if (Mass < 0) {
                        massesOfBaggageTextField.setStyle("-fx-border-color: red");
                        fail = true;
                        failtext += "\nMass is less than zero.";
                        break;
                    } else {
                        massesOfBaggageTextField.setStyle("-fx-border-color: defult");
                        sumMassOfBaggage += Mass;
                    }
                } catch (Exception exc) {
                    massesOfBaggageTextField.setStyle("-fx-border-color: red");
                    fail = true;
                    failtext += "\nMass isn't Integer.";
                    break;
                }
            }
        } else {
            massesOfBaggageTextField.setStyle("-fx-border-color: red");
            fail = true;
            failtext += "\nQuantity all baggage should be equal quantity place.";
        }
        if (fail) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(failtext);
            alert.show();
        } else {
            passengers.pushBack(new Passenger(name, lastName, patronymic, numberFlight, levelOfPlaceChoiceBox.getValue().toString(), numberBaggage, levelOfBaggageChoiceBox.getValue().toString(), quantityPlace, sumMassOfBaggage));
            closeScene();
        }
    }

    /**
     * Enum Level of place
     */
    enum LevelOfPlace {
        FIRST,
        BUSINESS,
        ECONOMY
    }

    /**
     * Enum Level of baggage
     */
    enum LevelOfBaggage {
        HANDLUGGAGE,
        INLUGGAGE
    }

    @FXML
    void closeScene(final ActionEvent event) {
        final Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void closeScene() {
        final Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
}
