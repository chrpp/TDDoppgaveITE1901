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
	public void intToBits_GivenMaxValue24BitInt_ShouldReturn24CharStringWithAll1s() {
		int intValue = 16777215; // Max value that can be represented by 24 bits
		assertThat(Converter.intToBits(intValue), is("111111111111111111111111"));
	}
	
	@Test
	public void intToBits_GivenIntValue_8_ShouldReturn_000000000000000000001000() {
		int intValue = 8; // Max value that can be represented by 24 bits
		assertThat(Converter.intToBits(intValue), is("000000000000000000001000"));
	}
	
	@Test
	public void intToBits_GivenMinValueInt_ShouldReturn24CharStringWithAll0s() {
		int intValue = 0; // Minimum integer value
		assertThat(Converter.intToBits(intValue), is("000000000000000000000000"));
	}
	
	@Test
	public void intToHex_GivenMaxValueInt_ShouldReturn_FFFFFF() {
		int intValue = 16777215; // Max value that can be represented by 6 hex digits
		assertThat(Converter.intToHex(intValue), is("FFFFFF"));
	}
	
	@Test
	public void intToHex_GivenIntValue15_ShouldReturn_00000F() {
		int intValue = 254; // Max value that can be represented by 6 hex digits
		assertThat(Converter.intToHex(intValue), is("0000FE"));
	}
	
	@Test
	public void hexToInt_GivenEmptyHexString_ShouldReturnZero() {
		String emptyHexString = "";
		assertThat(Converter.hexToInt(emptyHexString), is(0));
	}
	
	@Test
	public void hexToInt_GivenHexString_F_ShouldReturn_15() {
		String hexString = "F"; // Integer value of F is 15
		assertThat(Converter.hexToInt(hexString), is(15));
	}
	
	@Test
	public void hexToInt_GivenHexString_0_ShouldReturn_0() {
		String hexString = "0";
		assertThat(Converter.hexToInt(hexString), is(0));
	}
	
	@Test
	public void hexToInt_GivenHexStringLongerThenMax_ShouldThrowIllegalArgumentException() {
		expException.expect(IllegalArgumentException.class);
		expException.expectMessage("Argument string invalid: lenght greater than 6 chars");
		String toLongHexString = "ABC123A"; // Length is 7 chars, max allowed is 6
		Converter.hexToInt(toLongHexString);
	}
	
	@Test
	public void hexToInt_GivenHexStringWithInvalidChar_ShouldThrowIllegalArgumentException() {
		expException.expect(IllegalArgumentException.class);
		expException.expectMessage("Argument string invalid: invalid char in string, only hex chars permitted");
		String invalidCharInHexString = "0101G"; // String contains invalid char (G)
		Converter.hexToInt(invalidCharInHexString);
	}
	
	@Test 
	public void bitwiseANDBitStrings_GivenBitStrings_1_and_0_ShouldReturn24CharStrigEqualToDecimal_0() {
		String bitString1 = "1";
		String bitString2 = "0";
		assertThat(Converter.bitwiseANDBitStrings(bitString1, bitString2), is("000000000000000000000000"));
	}
	
	@Test 
	public void bitwiseANDBitStrings_GivenBitStrings_1_and_1_ShouldReturn24CharStrigEqualToDecimal_1() {
		String bitString1 = "1";
		String bitString2 = "1";
		assertThat(Converter.bitwiseANDBitStrings(bitString1, bitString2), is("000000000000000000000001"));
	}
	
	@Test 
	public void bitwiseANDBitStrings_GivenBitStrings_1010_and_0101_ShouldReturn24CharStrigEqualToDecimal_0() {
		String bitString1 = "1010";
		String bitString2 = "0101";
		assertThat(Converter.bitwiseANDBitStrings(bitString1, bitString2), is("000000000000000000000000"));
	}
	
	@Test 
	public void bitwiseORBitStrings_GivenBitStrings_1_and_0_ShouldReturn24CharStrigEqualToDecimal_1() {
		String bitString1 = "1";
		String bitString2 = "0";
		assertThat(Converter.bitwiseORBitStrings(bitString1, bitString2), is("000000000000000000000001"));
	}
	
	@Test 
	public void bitwiseORBitStrings_GivenBitStrings_0_and_0_ShouldReturn24CharStrigEqualToDecimal_0() {
		String bitString1 = "0";
		String bitString2 = "0";
		assertThat(Converter.bitwiseORBitStrings(bitString1, bitString2), is("000000000000000000000000"));
	}
	
	@Test 
	public void bitwiseORBitStrings_GivenBitStrings_1010_and_0101_ShouldReturn24CharStrigEqualToDecimal_15() {
		String bitString1 = "1010";
		String bitString2 = "0101";
		assertThat(Converter.bitwiseORBitStrings(bitString1, bitString2), is("000000000000000000001111"));
	}
}
