package com.gojek.parkinglotservice.service;

import com.gojek.parkinglotservice.models.vehicles.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * The type Parking service impl test.
 */
public class ParkingServiceImplTest {

        private final ByteArrayOutputStream outContent	= new ByteArrayOutputStream();
        private static ParkingService parkingService = null;


    /**
     * Initialize.
     */
    @Before
        public void initialize() {
            System.setOut(new PrintStream(outContent));
        }

    /**
     * Clean up.
     */
    @After
        public void cleanUp() {
            parkingService.clean();
        }

    /**
     * Create parking lot with capacity.
     */
    @Test
        public void createParkingLotWithCapacity() {
            parkingService = new ParkingServiceImpl();

            parkingService.createParkingLotWithCapacity(6);
            assertTrue("Created a parking lot with 6 slots\n".equalsIgnoreCase(outContent.toString()));
        }

    /**
     * Existing parking lot.
     */
    @Test
        public void existingParkingLot() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(6);
            assertTrue("Created a parking lot with 6 slots\n".equalsIgnoreCase(outContent.toString()));
            parkingService.createParkingLotWithCapacity(6);
            System.setOut(null);
            assertTrue(outContent.toString().contains("Parking Lot already exists\n"));

        }

    /**
     * Test park vehicle.
     *
     * @throws Exception the exception
     */
    @Test
        public void testParkVehicle() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(6);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));

        }

    /**
     * Test park vehicle when capacity reaches.
     *
     * @throws Exception the exception
     */
    @Test
        public void testParkVehicleWhenCapacityReaches() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));
            parkingService.parkVehicle(new Car("KA-01-HH-678", "White"));
            assertTrue(outContent.toString().contains("Sorry, parking lot is full\n"));

        }

    /**
     * Test park vehicle when vehicle exists.
     *
     * @throws Exception the exception
     */
    @Test
        public void testParkVehicleWhenVehicleExists() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(2);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Vehicle is already in lot.\n"));

        }

    /**
     * Test park vehiclenearest slot.
     *
     * @throws Exception the exception
     */
    @Test
        public void testParkVehiclenearestSlot() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(2);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));
            parkingService.unParkVehicle(1);
            parkingService.parkVehicle(new Car("AB-01-HH-3456", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));

        }

    /**
     * Test un park vehicle when vehicle exists.
     *
     * @throws Exception the exception
     */
    @Test
        public void testUnParkVehicleWhenVehicleExists() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.unParkVehicle(1);
            assertTrue(outContent.toString().contains("Slot number 1 is free\n"));

        }

    /**
     * Test un park vehicle when vehicle doesnot exists.
     *
     * @throws Exception the exception
     */
    @Test
        public void testUnParkVehicleWhenVehicleDoesnotExists() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(6);
            parkingService.unParkVehicle(1);
            assertTrue(outContent.toString().contains("Slot number 1 is already empty\n"));

        }

    /**
     * Test status of vehicles.
     *
     * @throws Exception the exception
     */
    @Test
        public void testStatusOfVehicles() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));
            parkingService.getStatus();
            assertTrue(outContent.toString().contains("1\t\t\tKA-01-HH-1234\t\tWhite\n"));

        }

    /**
     * Test status of vehicles when parking is empty.
     *
     * @throws Exception the exception
     */
    @Test
        public void testStatusOfVehiclesWhenParkingIsEmpty() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.getStatus();
            assertTrue(outContent.toString().contains("Sorry, parking lot is empty.\n"));

        }

    /**
     * Test regis number with colour.
     *
     * @throws Exception the exception
     */
    @Test
        public void testRegisNumberWithColour() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getRegisNumberForCarsWithColour("White");
            assertTrue(outContent.toString().contains("KA-01-HH-1234\n"));
        }

    /**
     * Test regis number with colour when car not found.
     *
     * @throws Exception the exception
     */
    @Test
        public void testRegisNumberWithColourWhenCarNotFound() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getRegisNumberForCarsWithColour("Black");
            assertTrue(outContent.toString().contains("Not found\n"));
        }

    /**
     * Test regis number with colour with case insesitive.
     *
     * @throws Exception the exception
     */
    @Test
        public void testRegisNumberWithColourWithCaseInsesitive() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getRegisNumberForCarsWithColour("white");
            assertTrue(outContent.toString().contains("KA-01-HH-1234\n"));
        }

    /**
     * Test slot number with colour.
     *
     * @throws Exception the exception
     */
    @Test
        public void testSlotNumberWithColour() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getSlotNumbersForCarsWithColour("White");
            assertTrue(outContent.toString().contains("1\n"));
        }

    /**
     * Test slot number with colour when car not found.
     *
     * @throws Exception the exception
     */
    @Test
        public void testSlotNumberWithColourWhenCarNotFound() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getSlotNumbersForCarsWithColour("Black");
            assertTrue(outContent.toString().contains("Not found\n"));
        }

    /**
     * Test slot number from regis number.
     *
     * @throws Exception the exception
     */
    @Test
        public void testSlotNumberFromRegisNumber() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getSlotNumberFromRegisNumber("KA-01-HH-1234");
            assertTrue(outContent.toString().contains("1\n"));
        }

    /**
     * Test slot number from regis number when car not found.
     *
     * @throws Exception the exception
     */
    @Test
        public void testSlotNumberFromRegisNumberWhenCarNotFound() throws Exception {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getSlotNumberFromRegisNumber("KA-01-HH-1");
            assertTrue(outContent.toString().contains("Not found\n"));
        }

}
