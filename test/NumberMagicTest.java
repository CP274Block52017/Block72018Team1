import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class NumberMagicTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void primeFacterizationTest() {
		NumberMagic number = new NumberMagic();

		ArrayList<NumberMagic.PrimeNumber> primes = number.primeFacterization(54);
		assertTrue(primes.contains(new NumberMagic.PrimeNumber(3)));
		assertTrue(primes.contains(new NumberMagic.PrimeNumber(2)));
		assertFalse(primes.contains(new NumberMagic.PrimeNumber(9)));

	}
	
	@Test
	public void lcmTest() {
		NumberMagic number = new NumberMagic();
		
		assertEquals(650, number.lcm(50, 130));
		
	}
	
	public void lamdaTest() {
		NumberMagic number = new NumberMagic();
		
		int p1 = 41;
		int p2 = 79;
		
		assertEquals(1560, number.lambda(p1, p2));
		
	}


	
	@Test
	public void findETest() {
		NumberMagic number = new NumberMagic();
		ArrayList<Integer> enumbers = number.findE(88);
		
		assertTrue(enumbers.contains(5));
		assertTrue(enumbers.contains(87));
		assertFalse(enumbers.contains(2));
		
	}
	

	@Test
	public void relativelyPrimeTest() {
		NumberMagic number = new NumberMagic();

		assertTrue(number.relativelyPrime(239, 429));
		assertFalse(number.relativelyPrime(143, 242));
	}
	
	
	@Test
	public void isPrimeTest() {
		NumberMagic number = new NumberMagic();
		assertTrue(number.isPrimeNumber(2521));
		assertFalse(number.isPrimeNumber(69));
	}
	
	@Test
	public void multiplyTest() {
		NumberMagic.PrimeNumber prime = new NumberMagic.PrimeNumber(7, 3);
		
		assertEquals(343, prime.multiply());
	}
	
	@Test
	public void keysTest() {
		
	}
}
