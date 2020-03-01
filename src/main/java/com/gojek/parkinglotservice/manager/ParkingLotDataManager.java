package com.gojek.parkinglotservice.manager;

import com.gojek.parkinglotservice.models.vehicles.Vehicle;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * The interface Parking lot data manager.
 */
public interface ParkingLotDataManager {
    /**
     * Park vehicle int.
     *
     * @param vehicle the vehicle
     * @return the int
     */
    int parkVehicle(Vehicle vehicle);

    /**
     * Un park boolean.
     *
     * @param slotNumber the slot number
     * @return the boolean
     */
    boolean unPark(int slotNumber);

    /**
     * Status map.
     *
     * @return the map
     */
    Map<Integer, Vehicle> status();

    /**
     * Gets regis numberof vehicle with colour.
     *
     * @param colour the colour
     * @return the regis numberof vehicle with colour
     */
    Set<String> getRegisNumberofVehicleWithColour(String colour);

    /**
     * Gets slot numberof vehicle with colour.
     *
     * @param colour the colour
     * @return the slot numberof vehicle with colour
     */
    Set<Integer> getSlotNumberofVehicleWithColour(String colour);

    /**
     * Gets slot number fromregis number.
     *
     * @param regisNo the regis no
     * @return the slot number from regis number
     */
    Optional<Integer> getSlotNumberFromRegisNumber(String regisNo);

    /**
     * Clean.
     */
    void clean();

}
