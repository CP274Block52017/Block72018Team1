
public class CrappyHash {
	
	public static final int MAX_INT = (int)Math.pow(2, 32);
	

	
	public static long SpamHashAlgorithm(int input) {
		long output = 0;
		int input_number = input;
		int h0, h1, h2, h3, h4;
		h0 = input_number;
		h1 = input_number * 1024 % MAX_INT;
		h2 = input_number * 4096 % MAX_INT;
		h3 = input_number * 8192 % MAX_INT;
		h4 = input_number * 16384 % MAX_INT;
		int count = 0;
		while(count < 80) {
				h0 = h0 + count;
				h1 = h0 + h1;
				h2 = h1 + h2;
				h3 = h0 + h2 + h3;
				count++;
				output = h0 + h1 + h3 * h4 + h2;
			}

			return output;

		}

}