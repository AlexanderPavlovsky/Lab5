package sample.classes;

import java.io.Serializable;

/**
 * Class Passenger
 */
public class Passenger implements Serializable {
    /**
     * Name of passenger
     */
    private String name;
    /**
     * Last name of passenger
     */
    private String lastName;
    /**
     * Patronymic of passenger
     */
    private String patronymic;
    /**
     * Number flight of passenger
     */
    private String numberFlight;
    /**
     * Level of place
     */
    private String levelOfPlace;
    /**
     * Number baggage of passenger
     */
    private String numberBaggage;
    /**
     * Level of baggage
     */
    private String levelOfBaggage;
    /**
     * Quantity place of baggage of passenger
     */
    private int quantityPlace;
    /**
     * All mass of baggage of passenger
     */
    private int allMassOfBaggage;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getNumberFlight() {
        return numberFlight;
    }

    public String getLevelOfPlace() {
        return levelOfPlace;
    }

    public String getNumberBaggage() {
        return numberBaggage;
    }

    public String getLevelOfBaggage() {
        return levelOfBaggage;
    }

    public int getQuantityPlace() {
        return quantityPlace;
    }

    public int getAllMassOfBaggage() {
        return allMassOfBaggage;
    }

    /**
     * Default construct of Passenger class
     */
    public Passenger() {
    }

    /**
     * Construct of Passenger class
     *
     * @param name             The name of passenger
     * @param lastName         The last name of passenger
     * @param patronymic       The patronymic of passenger
     * @param numberFlight     The number flight of passenger
     * @param levelOfPlace     Level of place
     * @param numberBaggage    The number baggage of passenger
     * @param levelOfBaggage   Level of baggage
     * @param quantityPlace    The quantity place of passenger
     * @param allMassOfBaggage The all mass of baggage of passenger
     */
    public Passenger(final String name, final String lastName, final String patronymic, final String numberFlight, final String levelOfPlace, final String numberBaggage, final String levelOfBaggage, final int quantityPlace, final int allMassOfBaggage) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.numberFlight = numberFlight;
        this.levelOfPlace = levelOfPlace;
        this.numberBaggage = numberBaggage;
        this.levelOfBaggage = levelOfBaggage;
        this.quantityPlace = quantityPlace;
        this.allMassOfBaggage = allMassOfBaggage;
    }

    @Override
    public String toString() {
        return "Name: " + this.lastName + " " + this.name + " " + this.patronymic + "\nNumber Flight: " + this.numberFlight + "\nLevel of place: " + this.levelOfPlace + "\nNumber of baggage: " + this.numberBaggage + "\nLevel of baggage" + this.levelOfBaggage + "\nQuantity place: " + this.quantityPlace + "\nAll mass of baggage: " + this.allMassOfBaggage;
    }
}
