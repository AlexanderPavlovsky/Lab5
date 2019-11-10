package sample.classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Table {

    private final SimpleStringProperty passengerFullName;
    private final SimpleStringProperty passengerNumberFlight;
    private final SimpleStringProperty passengerLevelOfPlace;
    private final SimpleStringProperty passengerNumberBaggage;
    private final SimpleStringProperty passengerLevelOfBaggage;
    private final SimpleIntegerProperty passengerQuantityPlace;
    private final SimpleIntegerProperty passengerAllMassOfBaggage;

    public Table(Passenger passenger) {
        this.passengerFullName = new SimpleStringProperty(passenger.getLastName() + " " + passenger.getName() + " " + passenger.getPatronymic());
        this.passengerNumberFlight = new SimpleStringProperty(passenger.getNumberFlight());
        this.passengerLevelOfPlace = new SimpleStringProperty(passenger.getLevelOfPlace());
        this.passengerNumberBaggage = new SimpleStringProperty(passenger.getNumberBaggage());
        this.passengerLevelOfBaggage = new SimpleStringProperty(passenger.getLevelOfBaggage());
        this.passengerQuantityPlace = new SimpleIntegerProperty(passenger.getQuantityPlace());
        this.passengerAllMassOfBaggage = new SimpleIntegerProperty(passenger.getAllMassOfBaggage());

    }

    public String getPassengerFullName() {
        return passengerFullName.get();
    }

    public SimpleStringProperty passengerFullNameProperty() {
        return passengerFullName;
    }

    public void setPassengerFullName(String passengerFullName) {
        this.passengerFullName.set(passengerFullName);
    }

    public String getPassengerNumberFlight() {
        return passengerNumberFlight.get();
    }

    public SimpleStringProperty passengerNumberFlightProperty() {
        return passengerNumberFlight;
    }

    public void setPassengerNumberFlight(String passengerNumberFlight) {
        this.passengerNumberFlight.set(passengerNumberFlight);
    }

    public String getPassengerLevelOfPlace() {
        return passengerLevelOfPlace.get();
    }

    public SimpleStringProperty passengerLevelOfPlaceProperty() {
        return passengerLevelOfPlace;
    }

    public void setPassengerLevelOfPlace(String passengerLevelOfPlace) {
        this.passengerLevelOfPlace.set(passengerLevelOfPlace);
    }

    public String getPassengerNumberBaggage() {
        return passengerNumberBaggage.get();
    }

    public SimpleStringProperty passengerNumberBaggageProperty() {
        return passengerNumberBaggage;
    }

    public void setPassengerNumberBaggage(String passengerNumberBaggage) {
        this.passengerNumberBaggage.set(passengerNumberBaggage);
    }

    public String getPassengerLevelOfBaggage() {
        return passengerLevelOfBaggage.get();
    }

    public SimpleStringProperty passengerLevelOfBaggageProperty() {
        return passengerLevelOfBaggage;
    }

    public void setPassengerLevelOfBaggage(String passengerLevelOfBaggage) {
        this.passengerLevelOfBaggage.set(passengerLevelOfBaggage);
    }

    public int getPassengerQuantityPlace() {
        return passengerQuantityPlace.get();
    }

    public SimpleIntegerProperty passengerQuantityPlaceProperty() {
        return passengerQuantityPlace;
    }

    public void setPassengerQuantityPlace(int passengerQuantityPlace) {
        this.passengerQuantityPlace.set(passengerQuantityPlace);
    }

    public int getPassengerAllMassOfBaggage() {
        return passengerAllMassOfBaggage.get();
    }

    public SimpleIntegerProperty passengerAllMassOfBaggageProperty() {
        return passengerAllMassOfBaggage;
    }

    public void setPassengerAllMassOfBaggage(int passengerAllMassOfBaggage) {
        this.passengerAllMassOfBaggage.set(passengerAllMassOfBaggage);
    }
}
