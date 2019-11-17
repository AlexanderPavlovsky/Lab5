package sample.сontrollers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.ResourceBundle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Main;
import sample.classes.BackUp;
import sample.classes.Passenger;
import sample.classes.Passengers;
import sample.classes.Table;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Table> tableInfoPassengers;

    @FXML
    private TableColumn<Table, String> passengerFullName;

    @FXML
    private TableColumn<Table, String> passengerNumberFlight;

    @FXML
    private TableColumn<Table, String> passengerLevelOfPlace;

    @FXML
    private TableColumn<Table, String> passengerNumberBaggage;

    @FXML
    private TableColumn<Table, String> passengerLevelOfBaggage;

    @FXML
    private TableColumn<Table, Integer> passengerQuantityPlace;

    @FXML
    private TableColumn<Table, Integer> passengerAllMassOfBaggage;

    @FXML
    private MenuBar myMenuBar;

    @FXML
    private MenuItem save;

    @FXML
    private MenuItem open;

    @FXML
    private MenuItem openBackUp;

    private static Passengers passengers = new Passengers();
    private final BackUp backUp = new BackUp();


    private ObservableList<Table> data = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        passengerFullName.setCellValueFactory(new PropertyValueFactory<Table, String>("passengerFullName"));
        passengerNumberFlight.setCellValueFactory(new PropertyValueFactory<Table, String>("passengerNumberFlight"));
        passengerLevelOfPlace.setCellValueFactory(new PropertyValueFactory<Table, String>("passengerLevelOfPlace"));
        passengerNumberBaggage.setCellValueFactory(new PropertyValueFactory<Table, String>("passengerNumberBaggage"));
        passengerLevelOfBaggage.setCellValueFactory(new PropertyValueFactory<Table, String>("passengerLevelOfBaggage"));
        passengerQuantityPlace.setCellValueFactory(new PropertyValueFactory<Table, Integer>("passengerQuantityPlace"));
        passengerAllMassOfBaggage.setCellValueFactory(new PropertyValueFactory<Table, Integer>("passengerAllMassOfBaggage"));
        backUp.start();
    }

    @FXML
    void about(ActionEvent event) {
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("About");
        alert.setContentText("Airplanes passengers\nAlexander Pavlovsky\n2019 \u00A9 copyright");
        alert.show();
    }

    private void creatTable() {
        for (int i = 0; i < passengers.Size(); i++) {
            data.add(new Table(passengers.getPassenger(i)));
        }
        if (!passengers.massOver30().isEmpty()) {
            final Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Warning. This passengers have all mass of baggage over 30.");
            alert.setContentText(passengers.massOver30());
            alert.show();
        }
        tableInfoPassengers.setItems(data);
    }


    @FXML
    void locationOfBaggage(final ActionEvent event) {
        Main.locationOfBaggage();
        if (ControllerLocationOfBaggage.numberBaggage != null) {
            final String numberFlight = passengers.locationOfBaggage(ControllerLocationOfBaggage.numberBaggage);
            final Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Number flight");
            if (numberFlight != null) {
                alert.setContentText(numberFlight.toString());
            } else {
                alert.setContentText("Number baggage doesn't exist.");
            }
            alert.show();
        } else {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("String is empty");
            alert.show();
        }
    }

    @FXML
    void removeByLastName(final ActionEvent event) {
        Main.removeByLastName();
        if (ControllerRemovePassengerByLastName.lastName != null) {
            final Passenger passenger = passengers.removeByLastName(ControllerRemovePassengerByLastName.lastName);
            final Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Remove passenger");
            if (passenger != null) {
                alert.setContentText(passenger.toString());
            } else {
                alert.setContentText("Passenger with the last name doesn't exist.");
            }
            alert.show();
            data.clear();
            creatTable();
        } else {
            stringEmpty();
        }

    }

    @FXML
    void allMasses(final ActionEvent event) {
        if (!passengers.allMassOfBaggage().isEmpty()) {
            final Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("All mass of passengers' baggage");
            alert.setContentText(passengers.allMassOfBaggage());
            alert.show();
        }
    }

    public void stringEmpty() {
        final Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText("String is empty");
        alert.show();
    }

    @FXML
    void addPassenger(final ActionEvent event) {
        Main.addToList();
        if (AddController.passengers.Size() != 0) {
            passengers = AddController.passengers;
        }
        creatTable();
    }

    @FXML
    private void save(final ActionEvent event) {
        if (passengers.Size() != 0) {
            final Stage stage = (Stage) myMenuBar.getScene().getWindow();
            final FileChooser fileChooser = new FileChooser();
            final FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setTitle("Choose file");
            try {
                final ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                final File file = fileChooser.showSaveDialog(stage);
                objectMapper.writeValue(file, passengers);
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    @FXML
    private void load(final ActionEvent event) {
        final Stage stage = (Stage) myMenuBar.getScene().getWindow();
        final FileChooser fileChooser = new FileChooser();
        final FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setTitle("Choose file");
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            final File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                passengers = objectMapper.readValue(file, Passengers.class);
                creatTable();
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public static void backUp() {
        final String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss").format(Calendar.getInstance().getTime());
        if (passengers.Size() != 0) {
            System.out.println("===BackUp===");
            try {
                final ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                if (Objects.requireNonNull(new File("backup").listFiles()).length > 2) {
                    final File[] name = Objects.requireNonNull(new File("backup").listFiles());
                    final boolean delete = name[0].delete();
                    if (delete) {
                        objectMapper.writeValue(new File("backup/BackUp " + timeStamp + ".json"), passengers);
                    }
                } else {
                    objectMapper.writeValue(new File("backup/BackUp " + timeStamp + ".json"), passengers);
                }
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    @FXML
    private void loadBackUp(final ActionEvent event) {
        final Stage stage = (Stage) myMenuBar.getScene().getWindow();
        final FileChooser fileChooser = new FileChooser();
        final FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setInitialDirectory(new File("backup"));
        fileChooser.setTitle("Choose file");
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            final File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                passengers = objectMapper.readValue(file, Passengers.class);
                creatTable();
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
