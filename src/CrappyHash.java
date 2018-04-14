
public class CrappyHash {
	
	private int[] prime_numbers = new int[1000];

	public static final int MAX_INT = (int)Math.pow(2, 32);
	
	
	public void primeNumberGen() {
		int idx = 0;
		while(idx < 100) {
			for(int i = 2; i < 10000; i++) {
				if(isPrimeNumber(i)) {
					prime_numbers[idx] = i;
					idx++;
				}
			}
		}
	}
	
	public boolean isPrimeNumber(int value) {
		if(value != 2 && value % 2 == 0) {
			return false;
		}
		for(int i = 3; i < value; i++) {
			if(value % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public int primeNumberGen(int index) {
		
		return 1;
	}
	

	
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
	
	
	public static void main(String[] args) {
		CrappyHash h = new CrappyHash();
		h.primeNumberGen();
		for(int i = 0; i < h.prime_numbers.length; i++) {
			System.out.println(h.prime_numbers[i]);
		}
//		System.out.println(Hash.SpamHashAlgorithm(test));
		
	}
}
