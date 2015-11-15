import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that provides methods for reading in measurement data from a file and processing the data.
 * 
 * @author Christian Petersen
 *
 */
public class Collector {
	private Map<String, ProcessedDataSet> data; // Data structure for storing of processed data
	private List<String> log ; // A log containing duplicate measurements and data with invalid operation specified
	private FileHandler fileHandler; // Provides file related operations
	private final static String BITWISE_AND = "1";
	private final static String BITWISE_OR = "2";

	/**
	 * Constructs a Collector object. A FileHandler reference is passed as an argument to allow for file
	 * related operations.
	 * 
	 * @param fileHandler	provides file handling operations
	 */
	public Collector(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
		data = new HashMap<String, ProcessedDataSet>();
		log = new ArrayList<String>();
	}
	
	/**
	 * Reads a line of data from file as a string array. Returns string array if array contains the expected
	 * number of elements. If not, IllegalArgumentException is thrown.
	 * 
	 * @return a string array containing the data read from file
	 * @throws IllegalArgumentException if string array length != 4
	 */
	public String[] readDataFromFile() {
		String[] dataFromFile = fileHandler.readLine();
		if (dataFromFile.length != 4)
			throw new IllegalArgumentException("Argument string invalid: incorrect number of arguments");
		return dataFromFile;
	}

	/**
	 * Open a file for reading.
	 * 
	 * @param filename name of file to open
	 * @return true if file opened successfully, else false
	 */
	public boolean openFileForReading(String filename) {
		return fileHandler.openFileForReading(filename);
	}
	
	
	/**
	 * Checks if more data can be read.
	 * 
	 * @return true if more data can be read
	 */
	public boolean isEndOfFile() {
		return fileHandler.hasMoreData();
	}

	/**
	 * Process the measurement data set given in string array dataFromFile according to the operation code
	 * specified in the data set. Valid operation codes are "1" for bitwise AND, and "2" for bitwise OR. An invalid 
	 * operation code will mark the data set as invalid. A ProcessedDataSet object is returned containing both the 
	 * original data set and the result of the operation, both as bit strings and integer values.  
	 *   
	 * @param 	dataFromFile a measurement data set
	 * @return 	a ProcessedDataSet object containing the original data, the result of the specified operation and a flag
	 * 			indicating if the data set is considered valid.
	 */
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
	
	/**
	 * Adds the data set, supplied as input argument, to the internal hash map if data set is valid and does not 
	 * contains an existing key. If the data set is marked as invalid or a duplicate key is found, a log entry is 
	 * created from the data set and the entry is added to the log.
	 * 
	 * @param processedDataSet data set to be stored if valid and contain non-duplicate key, else added to log
	 */
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
	
	/**
	 * @return the map containing the processed data sets
	 */
	public  Map<String, ProcessedDataSet> getDataMap() {
		return data;
	}

	/**
	 * @return log containing invalid data sets and data sets with duplicate keys
	 */
	public List<String> getLog() {
		return log;
	}
}
