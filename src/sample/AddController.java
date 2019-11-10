package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.classes.Passenger;
import sample.classes.Passengers;

import static sample.classes.FunUtils.*;
import static sample.classes.FunUtils.getInt;

public class AddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label massesOfBaggageTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField patronymicTextField;

    @FXML
    private TextField numberFlightTextField;

    @FXML
    private TextField levelOfPlaceTextField;

    @FXML
    private TextField numberBaggageTextField;

    @FXML
    private TextField levelOfBaggageTextField;

    @FXML
    private TextField quantityPlaceTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {

    }

    @FXML
    void createPassenger(ActionEvent event) {
        String name, lastName, patronymic, numberFlight, numberBaggage;
        int quantityPlace, sumMassOfBaggage = 0, numberLevel, numberLevelBaggage;
        LevelOfPlace levelOfPlace;
        LevelOfBaggage levelOfBaggage;
        System.out.println(") Passenger: ");
        System.out.print("Name: ");
        name = nameTextField.getText();
        System.out.print("Last name: ");
        lastName = lastNameTextField.getText();
        System.out.print("Patronymic: ");
        patronymic = patronymicTextField.getText();
        System.out.print("Number flight: ");
        numberFlight = getNumberFlight(numberFlightTextField.getText());
        System.out.println("Level of place:\n1) First class\n2) Business class\n3)Eco class");
        numberLevel = getInt();
        switch (numberLevel) {
            case 1:
                levelOfPlace = LevelOfPlace.FIRST;
                break;
            case 2:
                levelOfPlace = LevelOfPlace.BUSINESS;
                break;
            case 3:
                levelOfPlace = LevelOfPlace.ECONOMY;
                break;
            default:
                System.out.println("Error!!! Your class is eco class");
                levelOfPlace = LevelOfPlace.ECONOMY;
                break;
        }
        System.out.println(levelOfPlace.toString());
        System.out.print("Number baggage: ");
        numberBaggage = getNumberBaggage();
        System.out.println("Level of baggage:\n1) Hand luggage\n2) In luggage");
        numberLevelBaggage = getInt();
        switch (numberLevelBaggage) {
            case 1:
                levelOfBaggage = LevelOfBaggage.HANDLUGGAGE;
                break;
            case 2:
                levelOfBaggage = LevelOfBaggage.INLUGGAGE;
                break;
            default:
                System.out.println("Error!!! Your level of baggage is In luggage");
                levelOfBaggage = LevelOfBaggage.INLUGGAGE;
                break;
        }
        System.out.print("Quantity place: ");
        quantityPlace = getInt();
        for (int j = 0; j < quantityPlace; j++) {
            System.out.print((j + 1) + ") Mass of baggage: ");
            sumMassOfBaggage += getInt();
        }
        passengers.pushBack(new Passenger(name, lastName, patronymic, numberFlight, levelOfPlace.toString(), numberBaggage, levelOfBaggage.toString(), quantityPlace, sumMassOfBaggage));
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
