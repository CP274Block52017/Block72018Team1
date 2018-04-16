import java.util.ArrayList;

public class BlockChain {	
	private ArrayList<Block> chain;
	
	public BlockChain() {
		chain = new ArrayList<Block>();
		
		addBlock(new Block("GENESIS BLOCK", ""));
	}
	
	/**
	 * This method verifies if a chain block is valid 
	 * @return true if the block is verified by others
	 */
	public boolean isValid() {
		Block currentBlock;
		Block previousBlock;
		
		for(int i = 1; i < chain.size(); i++) {
			currentBlock = chain.get(i);
			previousBlock = chain.get(i - 1);
			if(!(previousBlock.generateHash().equals(currentBlock.getPreviousHash()))) {
				return false;
			}
		}
		
		// check that every public key in the chain ends up with a positive balance
		
		return true;
	}
	
	/**
	 * This method returns the length of the block chain
	 * @return length of block chain
	 */
	public int length() {
		return chain.size();
	}
	
	/**
	 * This method adds one block to the ArrayList chain
	 * @param block
	 */
	private void addBlock(Block block) {
		chain.add(block);
	}
	
	/**
	 * This method processes a block by checking if it passes proof of work and is also valid.
	 * @return true if the block passes proof of work and is valid
	 */
	public boolean processNewBlock(Block block) {
		if(!block.passesProofOfWork(Runner.GLOBAL_NETWORK.getRequiredZeros())) {
			return false;
		}
		
		addBlock(block);
		if(!block.testing)
		{
			if(!isValid()) {
				removeHead();
			}
		}
		
		
		return true;
	}
	
	/**
	 * This method returns the last block of the block chain
	 * @return last block
	 */
	public Block getHead() {
		return chain.get(chain.size() - 1);
	}
	
	/**
	 * This method removes the last block of the block chain when it is invalid
	 */
	public void removeHead() {
		chain.remove(chain.size() - 1);
	}
}