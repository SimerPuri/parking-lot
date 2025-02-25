package com.gojek.parkinglotservice.resource;

import com.gojek.parkinglotservice.constants.ParkingLotConstants;
import com.gojek.parkinglotservice.models.vehicles.Car;
import com.gojek.parkinglotservice.service.ParkingService;
import com.gojek.parkinglotservice.service.ParkingServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * The type Process request impl test.
 */
public class ProcessRequestImplTest {

        private final ByteArrayOutputStream outContent	= new ByteArrayOutputStream();
        private static ProcessRequest processRequest = null;
        @Mock
        private static ParkingServiceImpl parkingService;

    /**
     * Test execute request when capacity format wrong.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
        public void testExecuteRequestWhenCapacityFormatWrong() throws Exception {
            processRequest = new ProcessRequestImpl(parkingService);
            processRequest.executeRequest(ParkingLotConstants.CREATE_PARKING_LOT + " s");
        }

    /**
     * Test parking lot created execute request called.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
        public void testParkingLotCreatedExecuteRequestCalled() throws Exception {
            processRequest = new ProcessRequestImpl(parkingService);
            processRequest.executeRequest(ParkingLotConstants.CREATE_PARKING_LOT + " 1");
            verify(parkingService, times(1)).createParkingLotWithCapacity(1);
        }

    /**
     * Test vehicle park execute request called.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
        public void testVehicleParkExecuteRequestCalled() throws Exception {
            processRequest = new ProcessRequestImpl(parkingService);
            processRequest.executeRequest(ParkingLotConstants.PARK + " ABC Colour");
            verify(parkingService, times(1)).parkVehicle(new Car("ABC", "Colour"));
        }

    /**
     * Test un park execute request called.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
        public void testUnParkExecuteRequestCalled() throws Exception {
            processRequest = new ProcessRequestImpl(parkingService);
            processRequest.executeRequest(ParkingLotConstants.LEAVE + " 1");
            verify(parkingService, times(1)).unParkVehicle(1);
        }

    /**
     * Test status execute request called.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
        public void testStatusExecuteRequestCalled() throws Exception {
            processRequest = new ProcessRequestImpl(parkingService);
            processRequest.executeRequest(ParkingLotConstants.STATUS);
            verify(parkingService, times(1)).getStatus();
        }

    /**
     * Test regis number with colour execute request called.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
        public void testRegisNumberWithColourExecuteRequestCalled() throws Exception {
            processRequest = new ProcessRequestImpl(parkingService);
            processRequest.executeRequest(ParkingLotConstants.REGIS_NUMBER_FOR_CARS_WITH_COLOUR + " Colour");
            verify(parkingService, times(1)).getRegisNumberForCarsWithColour("Colour");
        }

    /**
     * Test slot number with colour execute request called.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
        public void testSlotNumberWithColourExecuteRequestCalled() throws Exception {
            processRequest = new ProcessRequestImpl(parkingService);
            processRequest.executeRequest(ParkingLotConstants.SLOT_NUMBER_FOR_CARS_WITH_COLOUR + " Colour");
            verify(parkingService, times(1)).getSlotNumbersForCarsWithColour("Colour");
        }

    /**
     * Test slot number with regis number execute request called.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
        public void testSlotNumberWithRegisNumberExecuteRequestCalled() throws Exception {
            processRequest = new ProcessRequestImpl(parkingService);
            processRequest.executeRequest(ParkingLotConstants.SLOT_NUMBER_FOR_REGIS_NUMBER + " ABC");
            verify(parkingService, times(1)).getSlotNumberFromRegisNumber("ABC");
        }

    /**
     * Test execute request when operation not supported.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
        public void testExecuteRequestWhenOperationNotSupported() throws Exception {
            processRequest = new ProcessRequestImpl(parkingService);
            processRequest.executeRequest("Operation not supported");
        }

    /**
     * Test execute request when leave format wrong.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
        public void testExecuteRequestWhenLeaveFormatWrong() throws Exception {
            processRequest = new ProcessRequestImpl(parkingService);
            processRequest.executeRequest(ParkingLotConstants.LEAVE + " s");
        }



}
