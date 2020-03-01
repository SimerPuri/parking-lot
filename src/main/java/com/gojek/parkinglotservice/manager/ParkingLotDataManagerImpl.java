package com.gojek.parkinglotservice.manager;

import com.gojek.parkinglotservice.constants.ParkingLotConstants;
import com.gojek.parkinglotservice.models.parkingpolicy.ParkingPolicy;
import com.gojek.parkinglotservice.models.vehicles.Vehicle;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * The type Parking lot data manager.
 */
public class ParkingLotDataManagerImpl implements ParkingLotDataManager {

    private static ParkingLotDataManagerImpl parkingLotDataManager = null;
    private ParkingPolicy parkingPolicy = null;
    private Map<Integer, Vehicle> vehicleSlotMapping;

    private ParkingLotDataManagerImpl(int capacity, ParkingPolicy parkingPolicy) {
        this.parkingPolicy = parkingPolicy;
        this.vehicleSlotMapping = new ConcurrentHashMap<Integer, Vehicle>();
        for (int i = 1; i <= capacity; i++) {
            parkingPolicy.addParkingSlots(i);
        }
    }

    /**
     * Gets instance.
     *
     * @param capacity      the capacity
     * @param parkingPolicy the parking policy
     * @return the instance
     */
    public static ParkingLotDataManagerImpl getInstance(int capacity, ParkingPolicy parkingPolicy) {
        if (parkingLotDataManager == null) {
            synchronized (ParkingLotDataManagerImpl.class) {
                if (parkingLotDataManager == null) {
                    parkingLotDataManager = new ParkingLotDataManagerImpl(capacity, parkingPolicy);
                }
            }
        }
        return parkingLotDataManager;
    }

    @Override
    public int parkVehicle(Vehicle vehicle) {
        if (parkingPolicy.getRemainingSlots() == 0) {
            return ParkingLotConstants.PARKING_FULL;
        }
        if (vehicleSlotMapping.containsValue(vehicle)) {
            return ParkingLotConstants.VEHICLE_EXISTS;
        }
        // add to vehicle Map
        int slot = parkingPolicy.getFreeSlot();
        parkingPolicy.removeSlot(slot);
        vehicleSlotMapping.put(slot, vehicle);

        return slot;
    }

    @Override
    public boolean unPark(int slotNumber) {
        if (vehicleSlotMapping.containsKey(slotNumber)) {
            parkingPolicy.removeSlot(slotNumber);
            parkingPolicy.addParkingSlots(slotNumber);
            vehicleSlotMapping.remove(slotNumber);

            return  true;
        }

        return false;
    }

    @Override
    public Map<Integer, Vehicle> status() {
        return vehicleSlotMapping;
    }

    @Override
    public Set<String> getRegisNumberofVehicleWithColour(String colour) {

        return vehicleSlotMapping.entrySet().stream().filter(map -> colour
                .equalsIgnoreCase(map.getValue().getColour()))
                .map(map -> map.getValue().getRegistrationNumber())
                .collect(Collectors.toSet());

    }

    @Override
    public Set<Integer> getSlotNumberofVehicleWithColour(String colour) {

        return vehicleSlotMapping.entrySet().stream().filter(map -> colour.equalsIgnoreCase(map.getValue().getColour()))
                .map(map -> map.getKey())
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Integer> getSlotNumberFromRegisNumber(String regisNo) {

        return vehicleSlotMapping.entrySet().stream().filter(map ->regisNo.equalsIgnoreCase(
                map.getValue().getRegistrationNumber())).map(map -> map.getKey()).findFirst();
    }

    @Override
    public void clean() {
        parkingLotDataManager = null;
    }


}
