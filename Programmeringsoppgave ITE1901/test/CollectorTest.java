import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * A class for testing of the Collector class.
 * 
 * @author Christian Petersen
 *
 */
public class CollectorTest {
	private Collector collector;
	private FileHandler mockFileHandler;
	private String[] dataSet1 = {"03ac0f", "1", "110101000000110111001101", "001000011110011101001111"};
	private String[] dataSet2 = {"ac0e1e", "2", "001000011110011101001111", "000101010101010101111001"};
	private String[] invalidDataSet = {"ac0e1e", "3", "001000011110011101001111", "000101010101010101111001"}; // Invalid operator
	private ProcessedDataSet processedDataSet;

	@Before
	public void setUp() throws Exception {
		mockFileHandler = mock(FileHandler.class);
		collector = new Collector(mockFileHandler);
	}
	
	@Rule
	public ExpectedException expException = ExpectedException.none();

	@Test
	public void readDataFromFile_GivenIllegalNumOfArgumentsRead_ShouldThrowIllegalArgumentException() {
		expException.expect(IllegalArgumentException.class);
		expException.expectMessage("Argument string invalid: incorrect number of arguments");
		String[] invalidArgsArray = new String[1]; // An array length not equal to 4 is invalid
		when(mockFileHandler.readLine()).thenReturn(invalidArgsArray);
		collector.readDataFromFile();
	}
	
	@Test
	public void readDataFromFile_GivenCorrectNumOfArgumentsRead_ShouldReturnStringArrayOfDataRead() {
		when(mockFileHandler.readLine()).thenReturn(dataSet1);
		assertThat(collector.readDataFromFile(), equalTo(dataSet1));
	}
	
	@Test
	public void openFileForReading_GivenFileOpenedForReading_ShouldReturnTrue() {
		when(mockFileHandler.openFileForReading("validFile")).thenReturn(true);
		assertThat(collector.openFileForReading("validFile"), is(true));
		//verify(mockFileHandler, times(1)).openFileForReading("validFile");
	}
	
	@Test
	public void isEndOfFile_GivenMoreDataToRead_ShouldReturnFalse() {
		when(mockFileHandler.hasMoreData()).thenReturn(false);
		assertThat(collector.isEndOfFile(), is(false));
	}
	
	@Test
	public void processData_GivenBitwiseAndOperator_ShouldPerformBitwiseAndOfBitStrings() {
		String correctResultOfBitwiseAndOnDataSet1 = "000000000000010101001101";
		ProcessedDataSet processedDataElement = collector.processData(dataSet1);
		assertThat(processedDataElement.getProcessedResultAsBitString(), is(correctResultOfBitwiseAndOnDataSet1));
	}
	
	@Test
	public void processData_GivenBitwiseOROperator_ShouldPerformBitwiseOrOfBitStrings() {
		String correctResultOfBitwiseOrOnDataSet2 = "001101011111011101111111";
		processedDataSet = collector.processData(dataSet2);
		assertThat(processedDataSet.getProcessedResultAsBitString(), is(correctResultOfBitwiseOrOnDataSet2));
	}
	
	@Test 
	public void storeProcessedData_GivenValidDataSetWithUniqueMeasurementID_ShouldStoreElementToHashMap() {
		processedDataSet = collector.processData(dataSet2);
		collector.storeProcessedData(processedDataSet);
		assertThat(collector.getDataMap().get("ac0e1e"), equalTo(processedDataSet));
	}
	
	@Test 
	public void storeProcessedData_GivenValidDatatSetWithExistingMeasurementID_ShouldCreateNewLogEntry() {
		processedDataSet = collector.processData(dataSet2);
		collector.storeProcessedData(processedDataSet);
		collector.storeProcessedData(processedDataSet); // Trying to store the same data set again should result 
														// in new log entry since measurementID already exist in hash map.
		assertThat(collector.getLog().get(0).split(" "), equalTo(dataSet2));
	}
	
	@Test 
	public void storeProcessedData_GivenInvalidDatatSet_ShouldCreateNewLogEntry() {
		processedDataSet = collector.processData(invalidDataSet);
		collector.storeProcessedData(processedDataSet); // Trying to store the same data set again should result 
														// in new log entry since measurementID already exist in hash map.
		assertThat(collector.getLog().get(0).split(" "), equalTo(invalidDataSet));
	}
}
