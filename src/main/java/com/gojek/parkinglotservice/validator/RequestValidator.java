package com.gojek.parkinglotservice.validator;

import static com.gojek.parkinglotservice.constants.ParkingLotConstants.VALID_INPUTS;

/**
 * The type Request validator.
 */
public class RequestValidator {

    /**
     * Is valid request boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public static boolean isValidRequest(String request) {
        request = request.toLowerCase();

        String[] requestArray = request.split(" ");
        Integer paramCount = VALID_INPUTS.get(requestArray[0]);

        if (paramCount == null || paramCount != requestArray.length-1) {
            return false;
        }

        return true;
    }
}
