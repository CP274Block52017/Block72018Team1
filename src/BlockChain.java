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
	
	public int length() {
		return chain.size();
	}
	
	private void addBlock(Block block) {
		chain.add(block);
	}
	
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
		
		
		if(!isValid()) {
			removeHead();
		}
		
		return true;
	}
	
	public Block getHead() {
		return chain.get(chain.size() - 1);
	}
	
	public void removeHead() {
		chain.remove(chain.size() - 1);
	}
}