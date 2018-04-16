// KON'S JOB
// V V V V V
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
		NumberMagic nm = new NumberMagic();
		int[] keyPair = nm.generateKeyPair();
		keysGenerated++;
		return keyPair;
	}
	
	public int getNextKey(int input) {
		return 0;
	}
}