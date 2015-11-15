
/**
 * Utility class providing methods for converting between bit strings, hex strings and integer value.
 * Also determines the bitwise AND and OR between two bit strings, and determines if a hex string or 
 * a bit string i valid based on string length and content. 
 * 
 * @author Christian Petersen
 *
 */
public class Converter {
	final static int MAX_BIT_STRING_LENGTH = 24;
	final static int MAX_HEX_STRING_LENGTH = 6;

	public Converter() {
	}
	
	/**
	 * Returns a bit string representation of bitwise and between two strings
	 * 
	 * @param 	bitString1	the first string argument	
	 * @param 	bitString2	the second string argument
	 * @return	a 24 char bit string representing the result of (bitString1 & bitString2)  				
	 */
	public static String bitwiseANDBitStrings(String bitString1, String bitString2) {
		
		return intToBits(bitsToInt(bitString1) & bitsToInt(bitString2));		
	}
	
	/**
	 * Returns a bit string representation of bitwise or between two strings
	 * 
	 * @param 	bitString1	the first string argument	
	 * @param 	bitString2	the second string argument
	 * @return	a 24 char bit string representing the result of (bitString1 | bitString2)  				
	 */
	public static String bitwiseORBitStrings(String bitString1, String bitString2) {
		
		return intToBits(bitsToInt(bitString1) | bitsToInt(bitString2));		
	}
	
	/**
	 * Returns an integer value representation of a string containing 0s and 1s.
	 * 
	 * @param 	bitString	string to be converted 	
	 * @return	integer value representation of bitString
	 */
	public static int bitsToInt(String bitString) {
		int intValueOfBits = 0;
		int exp = 0;
		
		if (isValidBitString(bitString)) 				
			for (int pos = 0; pos < bitString.length(); pos++) {
				exp = (bitString.length() - 1) - pos;  
				intValueOfBits += 
						bitString.charAt(pos) == '1' ? Math.pow(2, exp) : 0; // Math.pow(2, exp) determines the 
																		     // value of the bit at position pos
			}
		return intValueOfBits;
	}

	/**
	 * Returns a bit string representation of intValue by performing bitwise AND 
	 * between intValue and a moving mask, starting at the MSB in intValue and moving right.
	 * Only the 24 MSB of intValue will be represented in the bit string.
	 * 
	 * @param 	intValue	integer value to be converted to string of bits
	 * @return  bitString	string representation of in intValue
	 */
	public static String intToBits(int intValue) {
		String bitString = "";
		int mask = 1;
		for (int i = MAX_BIT_STRING_LENGTH - 1; i >= 0; i--) {
			mask = (int)Math.pow(2, i); // Mask bit starts at MSB (2^23) and moves right
			bitString += (((intValue&mask) == mask) ? 1 : 0); 
		}
		return bitString;
	}
	
	/**
	 * Returns a hex string representation of intValue by performing bitwise AND 
	 * between intValue and a moving mask, starting at the MSB in intValue and moving right.
	 * Only the 24 MSB of intValue will be represented in the bit string.
	 * 
	 * @param 	intValue	integer value to be converted to string of bits
	 * @return  hex string representation of in intValue
	 */
	public static String intToHex(int intValue) {
		String hexString = "";
		String hexAsBits = ""; 
		
		String bitString = intToBits(intValue);
		
		for (int i = 0; i < MAX_BIT_STRING_LENGTH; i += 4) {
			hexAsBits = bitString.substring(i, i + 4);
			switch (bitsToInt(hexAsBits)) {
				case 15 : hexString += 'F'; break;
				case 14 : hexString += 'E'; break;
				case 13 : hexString += 'D'; break;
				case 12 : hexString += 'C'; break;
				case 11 : hexString += 'B'; break;
				case 10 : hexString += 'A'; break;
				default : hexString += bitsToInt(hexAsBits);
			}
		}
		return hexString;
	}
	
	/**
	 * Returns an integer value representation of a string containing hex characters.
	 * 
	 * @param 	hexString 		string of hex chars to be converted
	 * @return	intValueOfHex	integer representation of the hex string
	 */
	public static int hexToInt(String hexString) {
		int intValueOfHex = 0;
		
		if (isValidHexString(hexString))			
			for (int i = hexString.length() - 1; i >= 0; i--) {
				switch (Character.toLowerCase(hexString.charAt(i))) {
					case 'a' : {intValueOfHex += 10 * Math.pow(16, i); break;}
					case 'b' : {intValueOfHex += 11 * Math.pow(16, i); break;}
					case 'c' : {intValueOfHex += 12 * Math.pow(16, i); break;}
					case 'd' : {intValueOfHex += 13 * Math.pow(16, i); break;}
					case 'e' : {intValueOfHex += 14 * Math.pow(16, i); break;}
					case 'f' : {intValueOfHex += 15 * Math.pow(16, i); break;}
					default : {intValueOfHex += (hexString.charAt(i) - 48) * Math.pow(16, i);} // UTF-8 value of 0 is 48
				}
			}
		return intValueOfHex;
	}
	
	/**
	 * Checks if hexString contains valid chars only and length of bitString is less or 
	 * equal to MAX_HEX_STRING_LENGTH.
	 * 
	 * @param 	hexString	string of hex to be verified
	 * @return 	true 		if hexString contains only valid chars and bitString length <= MAX_BIT_HEX_LENGTH
	 */
	private static boolean isValidHexString(String hexString) {
		String validChars = "[a-fA-F0-9]*"; // valid chars in hexString 
		
		if (hexString.length() > MAX_HEX_STRING_LENGTH)
			throw new IllegalArgumentException("Argument string invalid: lenght greater than 6 chars");
		else if (!hexString.matches(validChars))
			throw new IllegalArgumentException("Argument string invalid: invalid char in string, only hex chars permitted");
		else
			return true;
	}
	
	/**
	 * Checks if bitString contains valid chars only and length of bitString is less or 
	 * equal to MAX_BIT_STRING_LENGTH.
	 * 
	 * @param 	bitString	String of bits to be verified
	 * @return 	true 		if bitString contains 0's and 1's and bitString length <= MAX_BIT_STRING_LENGTH
	 */
	private static boolean isValidBitString(String bitString) {
		String validChars = "[0-1]*"; // valid chars in bitString is 0 or 1 
		
		if (bitString.length() > MAX_BIT_STRING_LENGTH)
			throw new IllegalArgumentException("Argument string invalid: lenght greater than 24 chars");
		else if (!bitString.matches(validChars))
			throw new IllegalArgumentException("Argument string invalid: invalid char in string, only 0 and 1 permitted");
		else
			return true;
	}
}
