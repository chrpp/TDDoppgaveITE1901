import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Class for running a parameterized test on static method Converter.bitwiseANDBitStrings(String, String).
 * Static methods Converter.intToBits(int) and Converter.bitToInt(String) are also tested as these methods 
 * are called from Converter.bitwiseANDBitStrings(String, String). 
 * 
 * @author Christian Petersen
 *
 */
@RunWith(Parameterized.class)
public class ParameterizedConverterTest {
	private String bitString1;
	private String bitString2;
	private String resultBitString;
	
	
	@Parameters(name = "{index}: bitwiseANDBitStrings({0} & {1}) = {2}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(
				new Object[][]{
						{inputBitString("111100001111000011110000"), inputBitString("000011110000111100001111"), resultBitString("000000000000000000000000")},
						{inputBitString("1"), inputBitString("1"), resultBitString("000000000000000000000001")},
						{inputBitString("0"), inputBitString("0"), resultBitString("000000000000000000000000")},
						{inputBitString("1"), inputBitString("0"), resultBitString("000000000000000000000000")},
						{inputBitString("0"), inputBitString("1"), resultBitString("000000000000000000000000")}
						
				}
		);
	}
	
	public ParameterizedConverterTest(String inputBitString1, String inputBitString2, String resultBitString) {
		this.bitString1 = inputBitString1;
		this.bitString2 = inputBitString2;
		this.resultBitString = resultBitString;
	}
	
	@Test
	public void bitwiseAndBitStrings_parameterized_test() {
		assertThat(Converter.bitwiseANDBitStrings(bitString1, bitString2), is(resultBitString));
	}
	
	private static String resultBitString(String resultBitString) {
		return resultBitString;
	}
	
	private static String inputBitString(String inputBitString) {
		return inputBitString;
	}
}
