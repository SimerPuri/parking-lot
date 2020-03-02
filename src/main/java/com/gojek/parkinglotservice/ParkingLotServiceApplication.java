package com.gojek.parkinglotservice;

import com.gojek.parkinglotservice.resource.ProcessRequest;
import com.gojek.parkinglotservice.resource.ProcessRequestImpl;
import com.gojek.parkinglotservice.service.ParkingServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.gojek.parkinglotservice.constants.ParkingLotConstants.COMMAND_LINE;
import static com.gojek.parkinglotservice.constants.ParkingLotConstants.FILE_INPUT;
import static com.gojek.parkinglotservice.util.ParkingLotCommandLineOptions.selectOptionsViaCommandLine;


public class ParkingLotServiceApplication {

	public static void main(String[] args) {
		ProcessRequest processor = new ProcessRequestImpl(new ParkingServiceImpl());
		BufferedReader bufferReader = null;
		String request = null;
		try {
			System.out.println("----Parking Lot----");
			switch (args.length) {
				case COMMAND_LINE: {
					processCommandLineRequest(bufferReader, processor, request);
					break;
				}
				case FILE_INPUT: {
					processFileRequest(bufferReader, processor, request, args);
					break;
				}
				default:
					System.out.println("Use java -jar <jar_file_path> <input_file_path>");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (bufferReader != null) {
					bufferReader.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void processCommandLineRequest(BufferedReader bufferReader, ProcessRequest processRequest,
										   String request) throws Exception {
		selectOptionsViaCommandLine();
		System.out.println(" Enter 'exit' for termination");
		while (true) {
			try {
				bufferReader = new BufferedReader(new InputStreamReader(System.in));
				request = bufferReader.readLine().trim();
				if (request.equalsIgnoreCase("exit")) {
					break;
				} else {
					if (processRequest.isValidRequest(request)) {
						try {
							processRequest.executeRequest(request);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} else {
						selectOptionsViaCommandLine();
					}
				}
			} catch (Exception e) {
				throw new Exception("Error occurred");
			}
		}
	}

	private static void processFileRequest(BufferedReader bufferReader, ProcessRequest processRequest,
										   String request, String[] args) throws Exception {
		File inputFile = new File(args[0]);
		try {
			bufferReader = new BufferedReader(new FileReader(inputFile));
			int lineNo = 1;
			while ((request = bufferReader.readLine()) != null)
			{
				request = request.trim();
				if (processRequest.isValidRequest(request)) {
					try {
						processRequest.executeRequest(request);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("Incorrect Command at line: " + lineNo + " ,Input: " + request);
				}
				lineNo++;
			}
		} catch (Exception e) {
			throw new Exception("Error Occurred");
		}
	}

	

}
