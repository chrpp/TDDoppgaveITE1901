/**
 * Describes methods for file handling operations.
 * 
 * @author Christian Petersen
 *
 */
public interface FileHandler {
	/**
	 * Open a file for reading.
	 * 
	 * @param filename	name of file to open
	 * @return true if file opened successfully, else false
	 */
	boolean openFileForReading(String filename); 
	
	/**
	 * Read a line of data and return read as a String array.
	 * 
	 * @return data read as a String array
	 */
	String[] readLine(); 
	
	/**
	 * Checks if more data can be read.
	 * 
	 * @return true if more data can be read
	 */
	boolean hasMoreData();
}
