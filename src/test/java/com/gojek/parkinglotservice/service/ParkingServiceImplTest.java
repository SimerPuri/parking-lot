package com.gojek.parkinglotservice.service;

import com.gojek.parkinglotservice.models.vehicles.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ParkingServiceImplTest {

        private final ByteArrayOutputStream outContent	= new ByteArrayOutputStream();
        private static ParkingService parkingService = null;


        @Before
        public void initialize() {
            System.setOut(new PrintStream(outContent));
        }

        @After
        public void cleanUp() {
            parkingService.clean();
        }

        @Test
        public void createParkingLotWithCapacity() {
            parkingService = new ParkingServiceImpl();

            parkingService.createParkingLotWithCapacity(6);
            assertTrue("Created a parking lot with 6 slots\n".equalsIgnoreCase(outContent.toString()));
        }

        @Test
        public void existingParkingLot() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(6);
            assertTrue("Created a parking lot with 6 slots\n".equalsIgnoreCase(outContent.toString()));
            parkingService.createParkingLotWithCapacity(6);
            System.setOut(null);
            assertTrue(outContent.toString().contains("Parking Lot already exists\n"));

        }
        
        @Test
        public void testParkVehicle() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(6);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));

        }

        @Test
        public void testParkVehicleWhenCapacityReaches() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));
            parkingService.parkVehicle(new Car("KA-01-HH-678", "White"));
            assertTrue(outContent.toString().contains("Sorry, parking lot is full\n"));

        }

        @Test
        public void testParkVehicleWhenVehicleExists() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(2);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Vehicle is already in lot.\n"));

        }

        @Test
        public void testParkVehiclenearestSlot() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(2);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));
            parkingService.unParkVehicle(1);
            parkingService.parkVehicle(new Car("AB-01-HH-3456", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));

        }

        @Test
        public void testUnParkVehicleWhenVehicleExists() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.unParkVehicle(1);
            assertTrue(outContent.toString().contains("Slot number 1 is free\n"));

        }

        @Test
        public void testUnParkVehicleWhenVehicleDoesnotExists() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(6);
            parkingService.unParkVehicle(1);
            assertTrue(outContent.toString().contains("Slot number 1 is already empty\n"));

        }

        @Test
        public void testStatusOfVehicles() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            assertTrue(outContent.toString().contains("Allocated slot number: 1\n"));
            parkingService.getStatus();
            assertTrue(outContent.toString().contains("1\t\t\tKA-01-HH-1234\t\tWhite\n"));

        }

        @Test
        public void testStatusOfVehiclesWhenParkingIsEmpty() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.getStatus();
            assertTrue(outContent.toString().contains("Sorry, parking lot is empty.\n"));

        }

        @Test
        public void testRegisNumberWithColour() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getRegisNumberForCarsWithColour("White");
            assertTrue(outContent.toString().contains("KA-01-HH-1234\n"));
        }

        @Test
        public void testRegisNumberWithColourWhenCarNotFound() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getRegisNumberForCarsWithColour("Black");
            assertTrue(outContent.toString().contains("Not found\n"));
        }

        @Test
        public void testRegisNumberWithColourWithCaseInsesitive() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getRegisNumberForCarsWithColour("white");
            assertTrue(outContent.toString().contains("KA-01-HH-1234\n"));
        }

        @Test
        public void testSlotNumberWithColour() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getSlotNumbersForCarsWithColour("White");
            assertTrue(outContent.toString().contains("1\n"));
        }

        @Test
        public void testSlotNumberWithColourWhenCarNotFound() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getSlotNumbersForCarsWithColour("Black");
            assertTrue(outContent.toString().contains("Not found\n"));
        }

        @Test
        public void testSlotNumberFromRegisNumber() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getSlotNumberFromRegisNumber("KA-01-HH-1234");
            assertTrue(outContent.toString().contains("1\n"));
        }

        @Test
        public void testSlotNumberFromRegisNumberWhenCarNotFound() {
            parkingService = new ParkingServiceImpl();
            parkingService.createParkingLotWithCapacity(1);
            parkingService.parkVehicle(new Car("KA-01-HH-1234", "White"));
            parkingService.getSlotNumberFromRegisNumber("KA-01-HH-1");
            assertTrue(outContent.toString().contains("Not found\n"));
        }

}
