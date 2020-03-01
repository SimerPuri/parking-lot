package com.gojek.parkinglotservice.validator;

import com.gojek.parkinglotservice.constants.ParkingLotConstants;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequestValidatorTest {


        @Test
        public void testWhenrequestNotValid() {
            assertFalse(RequestValidator.isValidRequest("not supported."));
        }

        @Test
        public void testParkingLotCreatedExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.CREATE_PARKING_LOT + " 1"));
        }

        @Test
        public void testVehicleParkExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.PARK + " ABC Colour"));
        }

        @Test
        public void testUnParkExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.LEAVE + " 1"));
        }

        @Test
        public void testStatusExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.STATUS));
        }

        @Test
        public void testRegisNumberWithColourExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.REGIS_NUMBER_FOR_CARS_WITH_COLOUR + " Colour"));
        }

        @Test
        public void testSlotNumberWithColourExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.SLOT_NUMBER_FOR_CARS_WITH_COLOUR + " Colour"));
        }

        @Test
        public void testSlotNumberWithRegisNumberExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.SLOT_NUMBER_FOR_REGIS_NUMBER + " ABC"));
        }

        @Test
        public void testExecuteRequestWhenOperationNotSupported() {
            assertFalse(RequestValidator.isValidRequest(ParkingLotConstants.PARK + " ABC"));
        }

}
