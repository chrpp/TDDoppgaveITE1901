
public class Converter {
	final static int MAX_BIT_STRING_LENGTH = 24;

	public Converter() {
	}
	
	/**
	 * Returns an integer value representation of bitString.
	 * 
	 * @param 	bitString	String to be converted 	
	 * @return	int			Integer value representation of bitString
	 */
	public static int bitsToInt(String bitString) {
		int intValueOfBits = 0;
		
		if (isValidBitString(bitString)) 				
			for (int i = bitString.length() - 1; i >= 0; i--) 
				intValueOfBits += bitString.charAt(i) == '1' ? Math.pow(2, i) : 0; 			
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
