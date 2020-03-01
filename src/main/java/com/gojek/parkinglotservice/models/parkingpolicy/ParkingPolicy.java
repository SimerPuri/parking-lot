package com.gojek.parkinglotservice.models.parkingpolicy;

/**
 * The interface Parking policy.
 */
public interface ParkingPolicy {
    /**
     * Add parking slots.
     *
     * @param slot the slot
     */
    void addParkingSlots(int slot);

    /**
     * Gets free slot.
     *
     * @return the free slot
     */
    int getFreeSlot();

    /**
     * Remove slot.
     *
     * @param slot the slot
     */
    void removeSlot(int slot);

    /**
     * Gets remaining slots.
     *
     * @return the remaining slots
     */
    int getRemainingSlots();
}
