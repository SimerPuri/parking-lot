package com.gojek.parkinglotservice.models.vehicles;

import com.gojek.parkinglotservice.enums.VehicleType;

/**
 * The type Vehicle.
 */
public class Vehicle {
    private String registrationNumber;
    private String colour;
    private VehicleType type;

    /**
     * Instantiates a new Vehicle.
     *
     * @param registrationNumber the registration number
     * @param colour             the colour
     * @param type               the type
     */
    public Vehicle(String registrationNumber, String colour, VehicleType type) {
        this.registrationNumber = registrationNumber;
        this.colour = colour;
        this.type = type;
    }

    /**
     * Gets registration number.
     *
     * @return the registration number
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets registration number.
     *
     * @param registrationNumber the registration number
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Gets colour.
     *
     * @return the colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * Sets colour.
     *
     * @param colour the colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(VehicleType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
             return true;

        return this instanceof Vehicle && this.getColour().equals(((Vehicle) obj).getColour())
                && this.getRegistrationNumber().equals(((Vehicle) obj).getRegistrationNumber()) &&
                this.getType().equals(((Vehicle) obj).getType());
    }

    @Override
    public int hashCode() {
        int h = 5381;
        h += (h << 5) + registrationNumber.hashCode();
        h += (h << 5) + colour.hashCode();
        h += (h << 5) + type.hashCode();
        return h;
    }
}
