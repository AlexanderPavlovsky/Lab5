package sample;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.classes.BackUp;
import sample.classes.Passenger;
import sample.classes.Passengers;
import sample.classes.Table;
import sample.Main;

import static sample.classes.FunUtils.*;

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

    private void creatTable() {
        for (int i = 0; i < passengers.Size(); i++) {
            data.add(new Table(passengers.getPassenger(i)));
        }
    }

    @FXML
    void addPassenger(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/addPassenger.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root,600, 400));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    @FXML
    private void save(ActionEvent event) {
        if (passengers.Size() != 0) {
            Stage stage = (Stage) myMenuBar.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setTitle("Choose file");
            try {
                final ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                File file = fileChooser.showSaveDialog(stage);
                objectMapper.writeValue(file, passengers);
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    @FXML
    private void load(ActionEvent event) {
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setTitle("Choose file");
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                passengers = objectMapper.readValue(file, Passengers.class);
                creatTable();
                tableInfoPassengers.setItems(data);
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public static void BackUp() {
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
    private void loadBackUp(ActionEvent event) {
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setInitialDirectory(new File("backup"));
        fileChooser.setTitle("Choose file");
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                passengers = objectMapper.readValue(file, Passengers.class);
                creatTable();
                tableInfoPassengers.setItems(data);
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
