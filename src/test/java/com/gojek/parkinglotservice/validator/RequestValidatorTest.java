package com.gojek.parkinglotservice.validator;

import com.gojek.parkinglotservice.constants.ParkingLotConstants;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The type Request validator test.
 */
public class RequestValidatorTest {


    /**
     * Test whenrequest not valid.
     */
    @Test
        public void testWhenrequestNotValid() {
            assertFalse(RequestValidator.isValidRequest("not supported."));
        }

    /**
     * Test parking lot created execute request called.
     */
    @Test
        public void testParkingLotCreatedExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.CREATE_PARKING_LOT + " 1"));
        }

    /**
     * Test vehicle park execute request called.
     */
    @Test
        public void testVehicleParkExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.PARK + " ABC Colour"));
        }

    /**
     * Test un park execute request called.
     */
    @Test
        public void testUnParkExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.LEAVE + " 1"));
        }

    /**
     * Test status execute request called.
     */
    @Test
        public void testStatusExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.STATUS));
        }

    /**
     * Test regis number with colour execute request called.
     */
    @Test
        public void testRegisNumberWithColourExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.REGIS_NUMBER_FOR_CARS_WITH_COLOUR + " Colour"));
        }

    /**
     * Test slot number with colour execute request called.
     */
    @Test
        public void testSlotNumberWithColourExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.SLOT_NUMBER_FOR_CARS_WITH_COLOUR + " Colour"));
        }

    /**
     * Test slot number with regis number execute request called.
     */
    @Test
        public void testSlotNumberWithRegisNumberExecuteRequestCalled() {
            assertTrue(RequestValidator.isValidRequest(ParkingLotConstants.SLOT_NUMBER_FOR_REGIS_NUMBER + " ABC"));
        }

    /**
     * Test execute request when operation not supported.
     */
    @Test
        public void testExecuteRequestWhenOperationNotSupported() {
            assertFalse(RequestValidator.isValidRequest(ParkingLotConstants.PARK + " ABC"));
        }

}
