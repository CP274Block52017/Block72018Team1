import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BlockChainTest {
	BlockChain bc;
	Block b;
	@Before
	public void setUp() throws Exception {
		bc = new BlockChain();
		b.testing=true; //used to bypass hashing security measures
	}

	@Test
	public void chainProcessingTrue1() {
		b = new Block("test", "test prev hash");
		b.setHash("0234111");
		bc.processNewBlock(b); //current required number of 0's within the LocalNetwork is 1 the test hash should be valid
		assertTrue(bc.getHead().getData().equals(b.getData()));
	}
	@Test
	public void chainProcessingTrue2() {
		b = new Block("as dsf dfasdflnkj", "test prev hash");
		b.setHash("0211");
		bc.processNewBlock(b); //current required number of 0's within the LocalNetwork is 1 the test hash should be valid
		assertTrue(bc.getHead().getData().equals(b.getData()));
	}
	@Test
	public void chainProcessingFalse1() {
		b = new Block("test", "test prev hash");
		b.setHash("12234111");
		bc.processNewBlock(b); // the hash is not valid so the block should not be added to the chain
		assertFalse(bc.getHead().getData().equals(b.getData()));
	}

}
