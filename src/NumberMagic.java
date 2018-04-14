import java.util.ArrayList;

public class NumberMagic{  
  
  /**
   * A class for prime number that will be used in prime facterization
   * @author kochi
   *
   */
  public static class PrimeNumber{
	  int prime;
	  int count;
	  
	  /**
	   * Constructor 1 for prime number intializes the count with 0;
	   * @param _prime the prime number
	   */
	  public PrimeNumber(int _prime) {
		  prime = _prime;
		  count = 1;
	  }
	  
	  /**
	   * Constructor 2 for prime number initializes the count with the input
	   * @param _prime the prime number
	   * @param _count the count for prime faterization
	   */
	  public PrimeNumber(int _prime, int _count) {
		  prime = _prime;
		  count = _count;
	  }
	  
	  /**
	   * increment a count by 1
	   */
	  public void increment() {
		  count++;
	  }
	  
	  
	  public int getPrime() {
		  return prime;
	  }
	  
	  public int getCount() {
		  return count;
	  }
	  
	  /**
	   * Equals method for prime number, this only considers the number itself not the count.
	   * @param o the other object
	   * @return true if its same number, false if not
	   */
	  @Override
	  public boolean equals(Object o) {
		  if(o instanceof PrimeNumber) {
			  PrimeNumber prime = (PrimeNumber) o;
			  if(this.getPrime() == prime.getPrime()) {
				  return true;
			  }
			  return false;
		  }
		  return false;
	  }
	  
	  /**
	   * This multiplies the prime numbers and the count to get the total multiple of this number.
	   * @return the total multiple.
	   */
	  public int multiply() {
		  return (int) Math.pow(prime, count);
	  }
  }
  
  /**
   * Generates the prime numbers
   * @param maxidx the number of prime numbers that you want.
   * @return The arrayList of prime numbers.
   */
	public ArrayList<Integer> primeNumberGen(int maxidx) {
		
		ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
		int idx = 0;
		int value = 2;
		while(idx < maxidx) {
			if(isPrimeNumber(value)) {
				primeNumbers.set(idx, value);
				idx++;
			}
			value++;
		}
		return primeNumbers;
	}
	
	/**
	 * Checks if the number is prime or not
	 * @param value the number to be checked
	 * @return true if it is prime number, false if not.
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
   * Prime Facterization 
   * @param x the x value to do the prime facterization
   * @return The arrayList that has all the prime numbers with count.
   */
  public ArrayList<PrimeNumber> primeFacterization(int x){
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
				  break;
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
	  ArrayList<PrimeNumber> x1_primes = primeFacterization(x1);
	  ArrayList<PrimeNumber> x2_primes = primeFacterization(x2);
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
					  
				  }else {
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
	  ArrayList<PrimeNumber> prime1 = primeFacterization(num1);
	  ArrayList<PrimeNumber> prime2 = primeFacterization(num2);
	  
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
   * Testing with main method
   * @param args args
   */
  public static void main(String[] args) {
	  int e1 = 239;
	  int e2 = 349;
	  NumberMagic e = new NumberMagic();
	  int lamdaValue = e.lambda(e1, e2);
	  System.out.println(lamdaValue);
	  System.out.println("x1 prime: " + e1 + " x2 prime: " + e2 + " lamda is " + lamdaValue);
	  ArrayList<Integer> eValues = e.findE(lamdaValue);
	  for(int i = 0; i < eValues.size(); i++) {
		  System.out.println("e" + i + ": " + eValues.get(i));
	  }
  }
}
