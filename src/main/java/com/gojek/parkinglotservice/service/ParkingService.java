package com.gojek.parkinglotservice.service;

import com.gojek.parkinglotservice.models.vehicles.Vehicle;

import java.util.Optional;

/**
 * The interface Parking service.
 */
public interface ParkingService {

    /**
     * Create parking lot with capacity.
     *
     * @param capacity the capacity
     */
    void createParkingLotWithCapacity(int capacity);

    /**
     * Park vehicle optional.
     *
     * @param vehicle the vehicle
     * @return the optional
     */
    Optional<Integer> parkVehicle(Vehicle vehicle) throws Exception;

    /**
     * Un park vehicle.
     *
     * @param slotNumber the slot number
     */
    void unParkVehicle(int slotNumber) throws Exception;

    /**
     * Gets status.
     */
    void getStatus() throws Exception;

    /**
     * Gets regis number for cars with colour.
     *
     * @param colour the colour
     */
    void getRegisNumberForCarsWithColour(String colour) throws Exception;

    /**
     * Gets slot numbers for cars with colour.
     *
     * @param colour the colour
     */
    void getSlotNumbersForCarsWithColour(String colour) throws Exception;

    /**
     * Gets slot number from regis number.
     *
     * @param registrationNo the registration no
     */
    void getSlotNumberFromRegisNumber(String registrationNo) throws Exception;

    /**
     * Clean.
     */
    void clean();

}
