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
		ProcessRequestImpl processor = new ProcessRequestImpl(new ParkingServiceImpl());
		BufferedReader bufferReader = null;
		String input = null;
		try {
			System.out.println("----Parking Lot----");
			switch (args.length) {
				case COMMAND_LINE: {
					selectOptionsViaCommandLine();
					System.out.println(" Enter 'exit' for termination");
					while (true) {
						try {
							bufferReader = new BufferedReader(new InputStreamReader(System.in));
							input = bufferReader.readLine().trim();
							if (input.equalsIgnoreCase("exit")) {
								break;
							} else {
								if (processor.isValidRequest(input)) {
									try {
										processor.executeRequest(input);
									} catch (Exception e) {
										System.out.println(e.getMessage());
									}
								} else {
									selectOptionsViaCommandLine();
								}
							}
						} catch (Exception e) {
							throw new Exception("Error occured");
						}
					}
					break;
				}
				case FILE_INPUT:
				{
					File inputFile = new File(args[0]);
					try {
						bufferReader = new BufferedReader(new FileReader(inputFile));
						int lineNo = 1;
						while ((input = bufferReader.readLine()) != null)
						{
							input = input.trim();
							if (processor.isValidRequest(input)) {
								try {
									processor.executeRequest(input);
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							} else {
								System.out.println("Incorrect Command at line: " + lineNo + " ,Input: " + input);
							}
							lineNo++;
						}
					} catch (Exception e) {
						throw new Exception("Error Occurred");
					}
					break;
				}
				default:
					System.out.println("Use java -jar <jar_file_path> <input_file_path>");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (bufferReader != null) {
					bufferReader.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	

}
