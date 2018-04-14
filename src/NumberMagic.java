import java.util.ArrayList;

public class NumberMagic{

  public NumberMagic(){

  }
  
  
  public class PrimeNumber{
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
			  return false;
		  }
		  return false;
	  }
	  
	  public int multiply() {
		  return (int) Math.pow(prime, count);
	  }
  }
  
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
  
  public int lcm(ArrayList<PrimeNumber> x1_primes, ArrayList<PrimeNumber> x2_primes) {
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
  
  public int lambda(int prime1, int prime2) {
	  return lcm(primeFacterization(prime1 - 1), primeFacterization(prime2 -1));
  }
  
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
  
  public ArrayList<Integer> findE(int lamdaValue) {
	  ArrayList<Integer> eValues = new ArrayList<Integer>();
	  ArrayList<PrimeNumber> lamdaPrimes = primeFacterization(lamdaValue); 
	  for(int i = lamdaValue; i > 1; i--) {
		  for(PrimeNumber prime : lamdaPrimes) {
			  if(i % prime.getPrime() == 0) {
				  break;
			  }
			  eValues.add(i);

		  }
	  }
	  return eValues;
  }

  
  public static void main(String[] args) {
	  int e1 = 239;
	  int e2 = 349;
	  NumberMagic e = new NumberMagic();
//	  ArrayList<PrimeNumber> e1primes = e.primeFacterization(e1 - 1);
//	  ArrayList<PrimeNumber> e2primes = e.primeFacterization(e2 - 1);
//	  int lamdaValue = e.lcm(e1primes , e2primes);
	  int lamdaValue = e.lambda(e1, e2);
	  System.out.println(lamdaValue);
	  System.out.println("x1 prime: " + e1 + " x2 prime: " + e2 + " lamda is " + lamdaValue);
	  ArrayList<Integer> eValues = e.findE(lamdaValue);
//	  for(int i = 0; i < eValues.size(); i++) {
//		  System.out.println("e" + i + ": " + eValues.get(i));
//	  }
//	  int lcm = e.lcm(e.primeFacterization(e1), e.primeFacterization(e2));
//	  System.out.println(lcm);
  }
}
