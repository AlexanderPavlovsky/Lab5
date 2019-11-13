package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import sample.classes.Passenger;
import sample.classes.Passengers;

import static sample.classes.FunUtils.*;
import static sample.classes.FunUtils.getInt;

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


    @FXML
    void initialize() {
        levelOfPlaceChoiceBox.setItems(FXCollections.observableArrayList(LevelOfPlace.values()));
        levelOfBaggageChoiceBox.setItems(FXCollections.observableArrayList(LevelOfBaggage.values()));
    }

    @FXML
    void createPassenger(ActionEvent event) {
        Passengers passengers = new Passengers();
        String name = null, lastName = null, patronymic = null, numberFlight = null, numberBaggage = null;
        int quantityPlace, sumMassOfBaggage = 0, numberLevel, numberLevelBaggage;
        boolean fail = false;
        String failtext = "";
        if (nameTextField.getText().matches("([A-Z][a-z]+)")) {
            name = nameTextField.getText();
            nameTextField.setStyle("-fx-border-color: defult");
        } else {
            nameTextField.setStyle("-fx-border-color: red");
            fail = true;
        }
        if (lastNameTextField.getText().matches("([A-Z][a-z]+)")) {
            lastName = lastNameTextField.getText();
            lastNameTextField.setStyle("-fx-border-color: defult");
        } else {
            lastNameTextField.setStyle("-fx-border-color: red");
            fail = true;
        }
        if (patronymicTextField.getText().matches("([A-Z][a-z]+)")) {
            patronymic = patronymicTextField.getText();
            patronymicTextField.setStyle("-fx-border-color: defult");
        } else {
            patronymicTextField.setStyle("-fx-border-color: red");
            fail = true;
        }
        if (numberFlightTextField.getText().matches("(([A-Z]{2}|[A-Z][0-9])-[0-9]{3,5})")) {
            numberFlight = numberFlightTextField.getText();
            numberFlightTextField.setStyle("-fx-border-color: defult");
        } else {
            numberFlightTextField.setStyle("-fx-border-color: red");
            fail = true;
            failtext += "Error number flight! Example: S7-206, EK-7892";
        }
        if (numberBaggageTextField.getText().matches("([A-Z][0-9]{7})")) {
            numberBaggage = numberBaggageTextField.getText();
            numberBaggageTextField.setStyle("-fx-border-color: defult");
        } else {
            numberBaggageTextField.setStyle("-fx-border-color: red");
            fail = true;
            failtext += "\nError number baggage! Example: S5896234, F1523465";
        }
//        System.out.print("Quantity place: ");
//        quantityPlace = getInt();

//        for (int j = 0; j < quantityPlace; j++) {
//            System.out.print((j + 1) + ") Mass of baggage: ");
//            sumMassOfBaggage += getInt();
//        }
        if (fail) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(failtext);
            alert.show();
        } else {
            passengers.pushBack(new Passenger(name, lastName, patronymic, numberFlight, levelOfPlaceChoiceBox.getItems().toString(), numberBaggage, levelOfBaggageChoiceBox.getItems().toString(), 1, 12));
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(passengers.toString());
            alert.show();
        }
//        }
//        int num = 0;
//        boolean check = false;
//        do {
//            try {
//                final String number = str.nextLine();
//                num = Integer.parseInt(number);
//                if (num > 0)
//                    check = true;
//            } catch (Exception exc) {
//                System.out.println(exc.getLocalizedMessage());
//            }
//        } while (!check);
//        return num;
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
}
