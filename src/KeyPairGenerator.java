public class KeyPairGenerator {
	private static int keysGenerated = 0;
	
	/**
	 * generates a pair of keys based on an autoincrementing seed
	 */
	public KeyPairGenerator() {
	}
	
	/**
	 * returns the next pair of keys
	 * @return
	 */
	public int[] generateNext() {
		int[] keyPair = new int[]{keysGenerated, keysGenerated};
		
		keysGenerated++;
		return keyPair;
	}
	
	public int getNextKey(int input) {
		return 0;
	}
}