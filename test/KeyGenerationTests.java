import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class KeyGenerationTests {
	KeyPairGenerator generator = new KeyPairGenerator();
	
	@Test
	public void primeFactorizationTest() {
		ArrayList<KeyPairGenerator.PrimeNumber> primes = generator.primeFactorization(54);
		assertTrue(primes.contains(new KeyPairGenerator.PrimeNumber(3)));
		assertTrue(primes.contains(new KeyPairGenerator.PrimeNumber(2)));
		assertFalse(primes.contains(new KeyPairGenerator.PrimeNumber(54)));
	}

	@Test
	public void lcmTest() {
		assertEquals(generator.lcm(50,  130), 650);
		assertEquals(generator.lcm(1, 1), 1);
		assertEquals(generator.lcm(3,  17), 51);
	}
	
	@Test
	public void lamdaTest() {
		assertEquals(generator.lambda(41, 79), 1560);
	}
	
	@Test
	public void isPrimeTest() {
		assertTrue(generator.isPrimeNumber(2521));
		assertFalse(generator.isPrimeNumber(64));
	}
	
	@Test
	public void multiplyTest() {
		KeyPairGenerator.PrimeNumber prime = new KeyPairGenerator.PrimeNumber(7, 3);
	
		assertEquals(343, prime.multiply());
	}
}
