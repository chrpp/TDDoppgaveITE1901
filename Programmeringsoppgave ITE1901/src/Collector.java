import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Collector {
	private Map<String, ProcessedDataSet> data = new HashMap<String, ProcessedDataSet>(); // Data structure for storing of processed data
	private List<String> log = new ArrayList<String>(); // A log containing duplicate measurements and data with invalid operation
	private FileHandler fileHandler;
	private final static String BITWISE_AND = "1";
	private final static String BITWISE_OR = "2";

	public Collector(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}
	
	// open file for reading
	// while (more data to read)
	// 		read a line of data from file
	// 		if (valid data line)
	// 			process data
	//		else
	//			mark as invalid operation
	// 		if data id already exist OR invalid operation
	//			write data line to log
	//		else
	//			store data in map
	// 
	
	public String[] readDataFromFile() {
		String[] dataFromFile = fileHandler.readLine();
		if (dataFromFile.length != 4)
			throw new IllegalArgumentException("Argument string invalid: incorrect number of arguments");
		return dataFromFile;
	}

	public boolean isOpenForReading(String filename) {
		return fileHandler.openFileForReading(filename);
	}

	public boolean isEndOfFile() {
		return fileHandler.hasMoreData();
	}

	public ProcessedDataSet processData(String[] dataFromFile) {
		boolean isValidDataSet = true;
		String processedResultAsBitString = "";
		int processedResultAsInt = -1;
		
		String measurementID = dataFromFile[0];
		String operator = dataFromFile[1];
		String bitString1  = dataFromFile[2];
		int bitString1AsInt = Converter.bitsToInt(bitString1);
		String bitString2  = dataFromFile[3];
		int bitString2AsInt = Converter.bitsToInt(bitString2);
		
		if (operator == BITWISE_AND) {
			processedResultAsBitString = Converter.bitwiseANDBitStrings(bitString1, bitString2);
			processedResultAsInt = Converter.bitsToInt(processedResultAsBitString);	
		}
		else if (operator == BITWISE_OR) {
			processedResultAsBitString = Converter.bitwiseORBitStrings(bitString1, bitString2);
			processedResultAsInt = Converter.bitsToInt(processedResultAsBitString);	
		}
		else 
			isValidDataSet = false; 
		
		return new ProcessedDataSet(
				measurementID,
				operator,
				bitString1,
				bitString2,
				processedResultAsBitString,
				bitString1AsInt,
				bitString2AsInt,
				processedResultAsInt,
				isValidDataSet);
	}
	
	public void storeProcessedData(ProcessedDataSet processedDataElement) {
		//Check if data set is invalid or data set contains duplicate measurement ID
		if ((processedDataElement.getIsValidDataSet() != true) || 
				(data.putIfAbsent(processedDataElement.getMeasurementID(), processedDataElement) != null)) {

				// Invalid dataset OR method putIfAbsent returned null ergo duplicate measurement ID, create log entry with measurement data
				String logEntry = 
						processedDataElement.getMeasurementID() + " " + 
								processedDataElement.getOperator() + " " +
								processedDataElement.getBitString1() + " " +
								processedDataElement.getBitString2();
				log.add(logEntry);
				System.out.println("Added to log: " + logEntry);
			}
	}
	
	
	// Get methods
	
	public  Map<String, ProcessedDataSet> getDataMap() {
		return data;
	}

	public List<String> getLog() {
		return log;
	}
}
