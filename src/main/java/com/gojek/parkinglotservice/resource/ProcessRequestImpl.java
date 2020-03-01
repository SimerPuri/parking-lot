package com.gojek.parkinglotservice.resource;

import com.gojek.parkinglotservice.models.vehicles.Car;
import com.gojek.parkinglotservice.service.ParkingService;
import com.gojek.parkinglotservice.validator.RequestValidator;

import static com.gojek.parkinglotservice.constants.ParkingLotConstants.CREATE_PARKING_LOT;
import static com.gojek.parkinglotservice.constants.ParkingLotConstants.LEAVE;
import static com.gojek.parkinglotservice.constants.ParkingLotConstants.PARK;
import static com.gojek.parkinglotservice.constants.ParkingLotConstants.REGIS_NUMBER_FOR_CARS_WITH_COLOUR;
import static com.gojek.parkinglotservice.constants.ParkingLotConstants.SLOT_NUMBER_FOR_CARS_WITH_COLOUR;
import static com.gojek.parkinglotservice.constants.ParkingLotConstants.SLOT_NUMBER_FOR_REGIS_NUMBER;
import static com.gojek.parkinglotservice.constants.ParkingLotConstants.STATUS;

/**
 * The type Process request.
 */
public class ProcessRequestImpl implements ProcessRequest {

    /**
     * The Parking service.
     */
    public final ParkingService parkingService;

    /**
     * Instantiates a new Process request.
     *
     * @param parkingService the parking service
     */
    public ProcessRequestImpl(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @Override
    public boolean isValidRequest(String request) {
        return RequestValidator.isValidRequest(request);
    }

    @Override
    public void executeRequest(String request) throws Exception {
        String[] requestArray = request.split(" ");
        String requestFor = requestArray[0];
        switch (requestFor) {
            case CREATE_PARKING_LOT:
                try {
                    int capacity = Integer.parseInt(requestArray[1]);
                    parkingService.createParkingLotWithCapacity(capacity);
                } catch (NumberFormatException e) {
                    throw new Exception("Error while decoding capacity");
                }
                break;
            case PARK:
                parkingService.parkVehicle(new Car(requestArray[1], requestArray[2]));
                break;
            case LEAVE:
                try {
                    int slotNumber = Integer.parseInt(requestArray[1]);
                    parkingService.unParkVehicle(slotNumber);
                } catch (NumberFormatException e) {
                    throw new Exception("error for slot number");
                }
                break;
            case STATUS:
                parkingService.getStatus();
                break;
            case REGIS_NUMBER_FOR_CARS_WITH_COLOUR:
                parkingService.getRegisNumberForCarsWithColour(requestArray[1]);
                break;
            case SLOT_NUMBER_FOR_CARS_WITH_COLOUR:
                parkingService.getSlotNumbersForCarsWithColour(requestArray[1]);
                break;
            case SLOT_NUMBER_FOR_REGIS_NUMBER:
                parkingService.getSlotNumberFromRegisNumber(requestArray[1]);
                break;
            default:
                throw new Exception("Operation Not supported");
        }
    }

}
