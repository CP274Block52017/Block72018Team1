
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
	 * @return the number of blocks in this chain
	 */
	public int length() {
		return chain.size();
	}
	
	/**
	 * This method adds one block to the ArrayList chain
	 * @param block
	 */
	public void addBlock(Block block) {
		chain.add(block);
	}
	
	/**
	 * processe a block and adds it to the chain and returns true if it is valid, otherwise returns false
	 * @param block
	 * @return whether the block is valid
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
	 * gets every transaction from every block in this chain
	 * @return
	 */
	public ArrayList<Transaction> getAllTransactions() {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		for(Block block : chain) {
			for(Transaction transaction : block.getTransactions()) {
				transactions.add(transaction);
			}
		}
			
		return transactions;
	}
	
	/**
	 * removes the head block of this chain
	 */
	public void removeHead() {
		chain.remove(chain.size() - 1);
	}
}