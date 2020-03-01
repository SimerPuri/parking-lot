package com.gojek.parkinglotservice.constants;

import java.util.HashMap;

/**
 * The type Parking lot constants.
 */
public class ParkingLotConstants {
    /**
     * The constant COMMAND_LINE.
     */
    public static final int COMMAND_LINE = 0;
    /**
     * The constant FILE_INPUT.
     */
    public static final int FILE_INPUT = 1;
    /**
     * The constant CREATE_PARKING_LOT.
     */
    public static final String	CREATE_PARKING_LOT = "create_parking_lot";
    /**
     * The constant REGIS_NUMBER_FOR_CARS_WITH_COLOUR.
     */
    public static final String	REGIS_NUMBER_FOR_CARS_WITH_COLOUR = "registration_numbers_for_cars_with_colour";
    /**
     * The constant SLOT_NUMBER_FOR_CARS_WITH_COLOUR.
     */
    public static final String	SLOT_NUMBER_FOR_CARS_WITH_COLOUR = "slot_numbers_for_cars_with_colour";
    /**
     * The constant SLOT_NUMBER_FOR_REGIS_NUMBER.
     */
    public static final String	SLOT_NUMBER_FOR_REGIS_NUMBER	= "slot_number_for_registration_number";
    /**
     * The constant PARK.
     */
    public static final String	PARK = "park";
    /**
     * The constant LEAVE.
     */
    public static final String	LEAVE = "leave";
    /**
     * The constant STATUS.
     */
    public static final String	STATUS = "status";
    /**
     * The constant VALID_INPUTS.
     */
    public static final HashMap<String, Integer> VALID_INPUTS = new HashMap<>();
    /**
     * The constant PARKING_FULL.
     */
    public static final Integer PARKING_FULL = -1;
    /**
     * The constant VEHICLE_EXISTS.
     */
    public static final Integer VEHICLE_EXISTS = -2;
    /**
     * The constant TWO.
     */
    public static final Integer TWO = 2;
    /**
     * The constant THREE.
     */
    public static final Integer THREE = 3;

    static {
        VALID_INPUTS.put(CREATE_PARKING_LOT, 1);
        VALID_INPUTS.put(PARK, 2);
        VALID_INPUTS.put(LEAVE, 1);
        VALID_INPUTS.put(STATUS, 0);
        VALID_INPUTS.put(REGIS_NUMBER_FOR_CARS_WITH_COLOUR, 1);
        VALID_INPUTS.put(SLOT_NUMBER_FOR_CARS_WITH_COLOUR, 1);
        VALID_INPUTS.put(SLOT_NUMBER_FOR_REGIS_NUMBER, 1);
    }

}
