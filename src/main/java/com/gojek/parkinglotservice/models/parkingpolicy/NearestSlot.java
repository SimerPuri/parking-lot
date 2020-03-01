package com.gojek.parkinglotservice.models.parkingpolicy;

import java.util.TreeSet;

/**
 * The type Nearest slot.
 */
public class NearestSlot implements ParkingPolicy {

    /**
     * The Free parking slots.
     */
    TreeSet<Integer> freeParkingSlots;

    /**
     * Instantiates a new Nearest slot.
     */
    public NearestSlot() {
        this.freeParkingSlots = new TreeSet<>();
    }


    @Override
    public void addParkingSlots(int slot) {
        freeParkingSlots.add(slot);
    }

    @Override
    public int getFreeSlot() {
        return freeParkingSlots.first();
    }

    @Override
    public void removeSlot(int slot) {
        freeParkingSlots.remove(slot);
    }

    @Override
    public int getRemainingSlots() {
        return freeParkingSlots.size();
    }
}
