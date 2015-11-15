/**
 * A class for modeling a processed measurement data set.
 * 
 * @author Christian Petersen
 *
 */
public class ProcessedDataSet {
	private String measurementID;
	private String operator;
	private String bitString1;
	private String bitString2;
	private String processedResultAsBitString;
	private int bitString1AsInt;
	private int bitString2AsInt;
	private int processedResultAsInt;
	private boolean isValidDataSet;
	
	/**
	 * Constructs ProcessedDataSet object.
	 * 
	 * @param measurementID					string representation of a 6 digit hex value representing the measurement ID
	 * @param operator						operation to be performed on bitString1 and bitString2, "1" for bitwise and, "2" for bitwise or
	 * @param bitString1					measurement data 1 as a bit string
	 * @param bitString2					measurement data 2 as a bit string
	 * @param processedResultAsBitString	the result as a bit string after specified operation has been performed on the data 
	 * @param bitString1AsInt				measurement data 1 as an integer
	 * @param bitString2AsInt				measurement data 2 as an integer
	 * @param processedResultAsInt			the result as an integer after specified operation has been performed on the data
	 * @param isValidDataSet				true if data set species a valid operator, else false 
	 */
	public ProcessedDataSet(String measurementID, String operator, String bitString1, String bitString2,
			String processedResultAsBitString, int bitString1AsInt,
			int bitString2AsInt, int processedResultAsInt, boolean isValidDataSet) {
		this.measurementID = measurementID;
		this.operator = operator;
		this.bitString1 = bitString1;
		this.bitString2 = bitString2;
		this.processedResultAsBitString = processedResultAsBitString;
		this.bitString1AsInt = bitString1AsInt;
		this.bitString2AsInt = bitString2AsInt;
		this.processedResultAsInt = processedResultAsInt;
		this.isValidDataSet = isValidDataSet;
	}
	
	// Get methods
	
	public String getMeasurementID() {
		return measurementID;
	}
	
	public String getOperator() {
		return operator;
	}
		
	public String getBitString1() {
		return bitString1;
	}

	public String getBitString2() {
		return bitString2;
	}

	public String getProcessedResultAsBitString() {
		return processedResultAsBitString;
	}

	public int getBitString1AsInt() {
		return bitString1AsInt;
	}

	public int getBitString2AsInt() {
		return bitString2AsInt;
	}

	public int getProcessedResultAsInt() {
		return processedResultAsInt;
	}
	
	public boolean getIsValidDataSet() {
		return isValidDataSet;
	}
}
