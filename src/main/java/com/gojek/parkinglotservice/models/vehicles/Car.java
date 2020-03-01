package com.gojek.parkinglotservice.models.vehicles;

import com.gojek.parkinglotservice.enums.VehicleType;

/**
 * The type Car.
 */
public class Car extends Vehicle {

    /**
     * Instantiates a new Car.
     *
     * @param registrationNumber the registration number
     * @param colour             the colour
     */
    public Car(String registrationNumber, String colour) {
        super(registrationNumber, colour, VehicleType.CAR);
    }
}
