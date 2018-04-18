import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing environement
 * @author Case Regan
 *
 */
public class BlockChainTests {

	private BlockChain genesisChain;
	
	private BlockChain testChain;

	private BlockChain invalidChain;
	
	public void setup() {
		genesisChain = new BlockChain();
	
		testChain = new BlockChain();
		
		invalidChain = new BlockChain();
		invalidChain.addBlock(new Block("invalid hash"));
	}
	
	@Test
	public void testValidChains() {
		setup();
		assertTrue(genesisChain.isValid());
	}
	
	@Test
	public void testAddingBlock() {
		setup();
		assertFalse(testChain.processNewBlock(new Block("invalid previous hash")));
	}
	
	@Test
	public void testInvalidChain() {
		setup();
		assertFalse(invalidChain.isValid());
	}
	
	@Test
	public void testProofOfWork() {
		setup();
		
		while(!genesisChain.getHead().passesProofOfWork(Runner.GLOBAL_NETWORK.getRequiredZeros())) {
			genesisChain.getHead().nextState();
		}
		
		assertTrue(genesisChain.getHead().passesProofOfWork(Runner.GLOBAL_NETWORK.getRequiredZeros()));
	}
}