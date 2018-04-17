import java.util.ArrayList;
import java.util.Random;

/**
 * a math-intensive class that generates a secure keypair based on complex rules
 * @author Case Regan
 *
 */
public class KeyPairGenerator {
	public static final int PRIME_SIZE = 100;
	private static int keysGenerated = 0;
	
	/**
	 * generates a pair of keys based on an autoincrementing seed
	 */
	public KeyPairGenerator() {
	}
	
	/**
	 * a class representing a Prime Number
	 * @author Kochi
	 *
	 */
	private static class PrimeNumber {
		int prime;
		int count;
		
		public PrimeNumber(int _prime) {
			prime = _prime;
			count = 1;
		}
		
		public PrimeNumber(int _prime, int _count) {
			prime = _prime;
			count = _count;
		}
		
		public void increment() {
			count++;
		}
		
		public int getPrime() {
			return prime;
		}
		
		public int getCount() {
			return count;
		}
		
		public boolean equals(Object o) {
			if(o instanceof PrimeNumber) {
				PrimeNumber prime = (PrimeNumber) o;
				if(this.getPrime() == prime.getPrime()) {
					return true;
				}
			}
			
			return false;
		}
		
		public int multiply() {
			return (int) Math.pow(prime, count);
		}
	}
	
	/**
	 * generates a list of prime numbers between 0 and maxidx
	 * @param maxidx
	 * @return
	 */
	public ArrayList<Integer> primeNumberGen(int maxidx) {
		ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
		
		int idx = 0;
		int value = 2;
		
		while(idx < maxidx - 1) {
			if(isPrimeNumber(value)) {
				primeNumbers.add(value);
				idx++;
			}
			value++;
		}
		
		return primeNumbers;
	}
	
	/**
	 * checks if a number is prime or not
	 * @param value
	 * @return
	 */
	public boolean isPrimeNumber(int value) {
		int checker = 3;
		
		if(value != 2 && value % 2 == 0) {
			return false;
		}
		
		while(checker < value) {
			if(value % checker == 0) {
				return false;
			}
			checker += 2;
		}
		return true;
	}
	
	/**
	 * returns an ArrayList containing the prime factors of a number
	 * @param x the number to factor
	 * @return
	 */
	public ArrayList<PrimeNumber> primeFactorization(int x) {
		int modx = x;
		
		ArrayList<PrimeNumber> primeFact = new ArrayList<PrimeNumber>();
		
		while(modx != 1) {
			for(int i = 2; i < modx + 1; i++) {
				if(modx % i == 0) {
					modx = modx / i;
					PrimeNumber prime = new PrimeNumber(i);
					for(PrimeNumber e: primeFact) {
						if(e.equals(prime)) {
							e.increment();
							break;
						}
					}
					if(!primeFact.contains(prime)) {
						primeFact.add(prime);
					}
				}
			}
		}
		
		return primeFact;
	}
	
	/**
	 * The least common multiple for two numbers
	 * @param x1_primes the prime facterization arraylist for the x1 prime
	 * @param x2_primes the prime facterization arraylist for the x2 prime
	 * @return the least common multiple of the two numbers.
	 */	
	public int lcm(int x1, int x2) {
		ArrayList<PrimeNumber> x1_primes = primeFactorization(x1);
		ArrayList<PrimeNumber> x2_primes = primeFactorization(x2);
		ArrayList<PrimeNumber> lcmprimes = new ArrayList<PrimeNumber>();
		Object[] x1_primes_array = x1_primes.toArray();
		Object[] x2_primes_array = x2_primes.toArray();
	
		for(Object e1: x1_primes_array) {
			for(Object e2: x2_primes_array) {
				PrimeNumber p1 = (PrimeNumber) e1;
				PrimeNumber p2 = (PrimeNumber) e2;
				if(p1.equals(p2)) {
					if(p1.getCount() > p2.getCount()) {
						lcmprimes.add(new PrimeNumber(p1.getPrime(), p1.getCount()));
					} else {
						lcmprimes.add(new PrimeNumber(p2.getPrime(), p2.getCount()));
					}
					x1_primes.remove(p1);
					x2_primes.remove(p2);
				}
			}
		}
		
		lcmprimes.addAll(x1_primes);
		lcmprimes.addAll(x2_primes);
		int lcm = 1;
		for(PrimeNumber e: lcmprimes) {
			lcm *= e.multiply();
		}	
		return lcm;
	}
	
	/**
	 * Get the lamda of two values. lamda = lcm(prime1 -1, prime2 - 1)
	 * @param prime1 the first prime number
	 * @param prime2 the second prime number
	 * @return the lamda value.
	 */
	public int lambda(int prime1, int prime2) {
		  return lcm(prime1 - 1, prime2 - 1);
	}

/**
 * Checks if two input values are relatively prime
 * @param num1 the first number
 * @param num2 the second number
 * @return true if it is relatively prime, false if not.
 */
	public boolean relativelyPrime(int num1, int num2) {
		ArrayList<PrimeNumber> prime1 = primeFactorization(num1);
		ArrayList<PrimeNumber> prime2 = primeFactorization(num2);

		for(int i = 0; i < prime1.size(); i++) {
			for(int j = 0; j < prime2.size(); j++) {
				if(prime1.get(i).equals(prime2.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
 	* Finds the E values where 1 < e < lamdaValue
 	* @param lamdaValue the biggest value;
 	* @return the ArrayList of E.
 	*/
	public ArrayList<Integer> findE(int lamdaValue) {
	  	ArrayList<Integer> eValues = new ArrayList<Integer>();
	  	for(int i = lamdaValue; i > 1; i--) {
		  	if(relativelyPrime(lamdaValue, i)) {
			  	eValues.add(i);
		  	}
	  	}
	  	return eValues;
	}

	/**
	 * returns the next pair of keys
	 * @return
	 */
	public int[] generateNext() {
		ArrayList<Integer> primes = primeNumberGen(PRIME_SIZE);
		Random random = new Random();
		int p_value = primes.get(random.nextInt(PRIME_SIZE - 1));
		int q_value = primes.get(random.nextInt(PRIME_SIZE - 1));
		 int n_value = p_value * q_value;
		int lambdaValue = lambda(p_value, q_value);
				
		
		ArrayList<Integer> e_numbers = findE(lambdaValue);
		int publicKey = e_numbers.get(random.nextInt(e_numbers.size() - 1));
		
		int count = 1;
		while(((count * publicKey) - 1) % lambdaValue != 0) {
			count++;
		}
		
		int privateKey = count + lambdaValue * random.nextInt(lambdaValue);
		
		int[] keyPair = new int[]{publicKey, privateKey, n_value};
		
		keysGenerated++;
		return keyPair;
	}
}