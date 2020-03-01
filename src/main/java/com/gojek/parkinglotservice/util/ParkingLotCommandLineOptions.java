package com.gojek.parkinglotservice.util;

/**
 * The type Parking lot command line options.
 */
public final class ParkingLotCommandLineOptions {

    /**
     * Select options via command line.
     */
    public static void selectOptionsViaCommandLine() {
        StringBuilder options = new StringBuilder();
        options.append("Select from the following options -");
        options.append("create_parking_lot {[capacity]}")
                .append("\n");

        options.append("park {[car_number]} {[car_colour]}")
                .append("\n");

        options.append("leave {[slot_number]}")
                .append("\n");

        options.append("status")
                .append("\n");

        options.append("registration_numbers_for_cars_with_colour {[car_COLOUR]}")
                .append("\n");

        options = options.append("slot_numbers_for_cars_with_colour {[car_COLOUR]}")
                .append("\n");

        options = options.append("slot_number_for_registration_number {[car_number]}")
                .append("\n");

        System.out.println(options.toString());
    }
}
