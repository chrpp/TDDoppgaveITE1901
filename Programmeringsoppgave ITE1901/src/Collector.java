import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Collector {
	private Map<String, ProcessedDataSet> data; // Data structure for storing of processed data
	private List<String> log ; // A log containing duplicate measurements and data with invalid operation specified
	private FileHandler fileHandler; // Provides file related operations
	private final static String BITWISE_AND = "1";
	private final static String BITWISE_OR = "2";

	public Collector(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
		data = new HashMap<String, ProcessedDataSet>();
		log = new ArrayList<String>();
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
	
	public void storeProcessedData(ProcessedDataSet processedDataSet) {
		//Check if data set is invalid or data set contains duplicate measurement ID
		if ((processedDataSet.getIsValidDataSet() != true) || 
				(data.containsKey(processedDataSet.getMeasurementID()))) {

			// Create log entry with measurement data and add to log
			String logEntry = 
					processedDataSet.getMeasurementID() + " " + 
							processedDataSet.getOperator() + " " +
							processedDataSet.getBitString1() + " " +
							processedDataSet.getBitString2();
			log.add(logEntry);
		}
		else
			data.put(processedDataSet.getMeasurementID(), processedDataSet);
	}
	
	
	// Get methods
	
	public  Map<String, ProcessedDataSet> getDataMap() {
		return data;
	}

	public List<String> getLog() {
		return log;
	}
}
