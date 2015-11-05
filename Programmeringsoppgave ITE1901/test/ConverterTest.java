import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;



public class ConverterTest {
	String bitString1;
	int intValue;

	@Before
	public void setUp() throws Exception {
		
	}
	
	@Rule
	public ExpectedException expException = ExpectedException.none();

	@Test
	public void bitsToInt_GivenEmptyBitString_ShouldReturnZero() {
		String emptyString = "";
		assertThat(Converter.bitsToInt(emptyString), is(0));
	}
	
	@Test
	public void bitsToInt_GivenBitStringLongerThenMax_ShouldThrowIllegalArgumentException() {
		expException.expect(IllegalArgumentException.class);
		expException.expectMessage("Argument string invalid: lenght greater than 24 chars");
		String toLongBitString = "1010101010101010101010101"; // Length is 25 chars, max allowed is 24
		
		Converter.bitsToInt(toLongBitString);
	}
	
	@Test
	public void bitsToInt_GivenBitStringWithInvalidChar_ShouldThrowIllegalArgumentException() {
		expException.expect(IllegalArgumentException.class);
		expException.expectMessage("Argument string invalid: invalid char in string, only 0 and 1 permitted");
		String invalidCharInBitString = "0101a"; // String contains invalid char (a)
		
		Converter.bitsToInt(invalidCharInBitString);
	}
	
	@Test
	public void bitsToInt_GivenBitString_1_ShouldReturn_1() {
		String bitString = "1";
		assertThat(Converter.bitsToInt(bitString), is(1));
	}
	
	@Test
	public void bitsToInt_GivenBitString_0_ShouldReturn_0() {
		String bitString = "0";
		assertThat(Converter.bitsToInt(bitString), is(0));
	}
	
	@Test
	public void intToBits_GivenMaxValue24BitInt_ShouldReturn24BitBitStringWithAll1s() {
		int intValue = 16777215; // Max value that can be represented by 24 bits
		assertThat(Converter.intToBits(intValue), is("111111111111111111111111"));
	}
	
	@Test
	public void intToBits_GivenIntValue_8_ShouldReturn_000000000000000000001000() {
		int intValue = 8; // Max value that can be represented by 24 bits
		assertThat(Converter.intToBits(intValue), is("000000000000000000001000"));
	}
	
	@Test
	public void intToBits_GivenMinValueInt_ShouldReturn24BitBitStringWithAll0s() {
		int intValue = 0; // Minimum integer value
		assertThat(Converter.intToBits(intValue), is("000000000000000000000000"));
	}
}
