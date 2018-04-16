public class KeyPairGenerator {
	private static int keysGenerated = 0;
	
	public KeyPairGenerator() {
	}
	
	public int[] generateNext() {
		// TODO have Kon implement this
		keysGenerated++;
		return new int[]{keysGenerated - 1, keysGenerated - 1};
	} 
}