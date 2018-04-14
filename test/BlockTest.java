import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BlockTest {
	Block b;
	@Before
	public void setUp() throws Exception {
		b = new Block("test", "test prev hash");
		b.testing=true; //used to bypass hashing security measures
	}

	@Test
	public void proofOfWorkTestTrue1() {
		b.setHash("00111");
		assertTrue(b.passesProofOfWork(2));
	}
	@Test
	public void proofOfWorkTestTrue2() {
		b.setHash("000000111");
		assertTrue(b.passesProofOfWork(6));
	}
	@Test
	public void proofOfWorkTestFalse1() {
		b.setHash("101081");
		assertFalse(b.passesProofOfWork(2));
	}
	@Test
	public void proofOfWorkTestFalse2() {
		b.setHash("234111");
		assertFalse(b.passesProofOfWork(5));
	}

}
