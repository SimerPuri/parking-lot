package com.gojek.parkinglotservice.service;

import com.gojek.parkinglotservice.constants.ParkingLotConstants;
import com.gojek.parkinglotservice.manager.ParkingLotDataManagerImpl;
import com.gojek.parkinglotservice.models.parkingpolicy.NearestSlot;
import com.gojek.parkinglotservice.models.vehicles.Vehicle;

import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The type Parking service.
 */
public class ParkingServiceImpl implements ParkingService {
    private ParkingLotDataManagerImpl parkingLotDataManager;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void createParkingLotWithCapacity(int capacity) {
        if (parkingLotDataManager != null) {
            System.out.println("Parking Lot already exists");
            return;
        }
        this.parkingLotDataManager = ParkingLotDataManagerImpl.getInstance(capacity, new NearestSlot());

        System.out.println("Created a parking lot with " + capacity + " slots");
    }

    @Override
    public Optional<Integer> parkVehicle(Vehicle vehicle) throws Exception {
        lock.writeLock().lock();
        if (isInvalidParkingLotDataManager()) {
            return Optional.empty();
        }
        try {
            Optional<Integer> status = Optional.of(parkingLotDataManager.parkVehicle(vehicle));

            if (status.get() == ParkingLotConstants.PARKING_FULL)
                System.out.println("Sorry, parking lot is full");
            else if (status.get() == ParkingLotConstants.VEHICLE_EXISTS)
                System.out.println("Vehicle is already in lot.");
            else
                System.out.println("Allocated slot number: " + status.get());

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            lock.writeLock().unlock();
        }


        return Optional.empty();
    }

    @Override
    public void unParkVehicle(int slotNumber) throws Exception {
        lock.writeLock().lock();

        try{
            if (isInvalidParkingLotDataManager()) {
                return;
            }

            if (parkingLotDataManager.unPark(slotNumber)) {
                System.out.println("Slot number " + slotNumber + " is free");
            } else {
                System.out.println("Slot number " + slotNumber + " is already empty");
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void getStatus() throws Exception {
        lock.writeLock().lock();
        try {
            System.out.println("Slot No.\tRegistration No\t\tColour");
            Map<Integer, Vehicle> vehicles = parkingLotDataManager.status();
            TreeSet<Integer> vehicleParkingSpots = new TreeSet<>(vehicles.keySet());

            if (vehicles.size() == 0) {
                System.out.println("Sorry, parking lot is empty.");
            }

            vehicleParkingSpots.forEach(vehicleParkingSpot ->
                    System.out.println(vehicleParkingSpot + "\t\t\t" + vehicles.get(vehicleParkingSpot).getRegistrationNumber()
                            + "\t\t" + vehicles.get(vehicleParkingSpot).getColour())
            );
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            lock.writeLock().unlock();
        }

    }


    @Override
    public void getRegisNumberForCarsWithColour(String colour) throws Exception {
        lock.writeLock().lock();
        try {
            if (isInvalidParkingLotDataManager()) {
                return;
            }
            TreeSet<String> regisNumbers = new TreeSet<>(parkingLotDataManager
                    .getRegisNumberofVehicleWithColour(colour));

            if (regisNumbers.size() == 0) {
                System.out.println("Not found");
                return;
            }

            System.out.println(String.join(", ", regisNumbers));
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            lock.writeLock().unlock();
        }

    }

    @Override
    public void getSlotNumbersForCarsWithColour(String colour) throws Exception {
        lock.writeLock().lock();

        try {
            if (isInvalidParkingLotDataManager()) {
                return;
            }

            TreeSet<Integer> slotNumbers = new TreeSet<>(parkingLotDataManager
                    .getSlotNumberofVehicleWithColour(colour));

            if (slotNumbers.size() == 0) {
                System.out.println("Not found");
                return;
            }

            StringJoiner joiner = new StringJoiner(", ");

            slotNumbers.forEach(slotNumber -> joiner.add(slotNumber.toString()));

            System.out.println(joiner.toString());
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            lock.writeLock().unlock();
        }

    }

    @Override
    public void getSlotNumberFromRegisNumber(String registrationNo) throws Exception {
        lock.writeLock().lock();

        try {
            if (isInvalidParkingLotDataManager()) {
                return;
            }
            Optional<Integer> slotNumber = parkingLotDataManager.getSlotNumberFromRegisNumber(registrationNo);

            if (slotNumber.isPresent()) {
                System.out.println(slotNumber.get());
            } else {
                System.out.println("Not found");
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            lock.writeLock().unlock();
        }

    }

    private boolean isInvalidParkingLotDataManager() {
        if (parkingLotDataManager == null) {
            System.out.println(" Create Parking Lot first");

            return true;
        }

        return false;
    }

    @Override
    public void clean() {
        if (parkingLotDataManager != null) {
            parkingLotDataManager.clean();
        }
    }
}
