import java.io.BufferedReader;


public interface FileHandler {
	//public FileReader openFileForReading(String filename); // open a file for reading, return a FileReader if successful
	public boolean openFileForReading(String filename); // open a file for reading, return true if successful
	public String[] readLine(); // read a line of data
	public boolean hasMoreData(); // true if more data can be read from file
}
