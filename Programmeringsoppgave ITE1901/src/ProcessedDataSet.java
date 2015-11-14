
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

	public ProcessedDataSet() {
	}
	
	public ProcessedDataSet(String measurementID, String operator, String bitString1, String bitString2,
			String processedResultAsBitString, int bitString1AsInt,
			int bitString2AsInt, int processedResultAsInt, boolean isValidDataSet) {
		//super();
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
