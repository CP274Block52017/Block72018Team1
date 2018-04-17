// KON'S JOB
// V V V V V
public class KeyPairGenerator {
	private static int keysGenerated = 0;

	public KeyPairGenerator() {
	}

	public int[] generateNext() {
		// TODO have Kon implement this
		NumberMagic number = new NumberMagic();
		int[] keys = number.generateKeyPair();
		keysGenerated++;
		return new int[]{keys[0], keys[1]};
	}
}
