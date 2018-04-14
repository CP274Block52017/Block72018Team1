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
		
		assertEquals(1560, number.lambda(41, 79));
		
	}
	

	@Test
	public void relativelyPrimeTest() {
		NumberMagic number = new NumberMagic();

		assertTrue(number.relativelyPrime(239, 429));
		assertFalse(number.relativelyPrime(143, 242));
	}
}
