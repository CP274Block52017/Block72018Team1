import java.util.*;

/**
 * represents a chain of blocks that store transactions
 * @author Case Regan
 *
 */
public class BlockChain {	
	private ArrayList<Block> chain;
	
	public BlockChain() {
		chain = new ArrayList<Block>();
		
		addBlock(Block.createGenesisBlock());
	}
	
	/**
	 * checks that this chain is valid
	 * for a chain to be valid, each block's previous hash must equal the hash of the previous block and no
	 * sender (except for the generator) can have a negative balance
	 * @return
	 */
	public boolean isValid() {
		Block currentBlock;
		Block previousBlock;
		
		for(int i = 0; i < chain.size() - 1; i++) {
			currentBlock = chain.get(i + 1);
			previousBlock = chain.get(i);

			
			if(!(previousBlock.generateHash().equals(currentBlock.getPreviousHash()))) {

				
				return false;
			}
		}
		
		Map<Integer, Double> keyBalancePairs = new HashMap<Integer, Double>();
		
		for(Transaction transaction : getAllTransactions()) {			
			double senderBalance = 0.0;
			double recieverBalance = 0.0;
			
			if(keyBalancePairs.containsKey(transaction.getSenderKey())) {
				senderBalance = keyBalancePairs.get(transaction.getSenderKey());
			} 
			
			if(keyBalancePairs.containsKey(transaction.getReceiverKey())) {
				recieverBalance = keyBalancePairs.get(transaction.getReceiverKey());
			}
			
			keyBalancePairs.put(transaction.getSenderKey(), senderBalance - transaction.getAmount());
			keyBalancePairs.put(transaction.getReceiverKey(), recieverBalance + transaction.getAmount());
		}
		
		for(Map.Entry<Integer, Double> keyBalancePair : keyBalancePairs.entrySet()) {
			if(keyBalancePair.getKey() != Block.GENERATOR_KEY && keyBalancePair.getValue() < 0) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @return the number of blocks in this chain
	 */
	public int length() {
		return chain.size();
	}
	
	/**
	 * adds a block to this chain
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
		if(!block.passesProofOfWork(ApplicationUI.GLOBAL_NETWORK.getRequiredZeros())) {
			return false;
		}
		
		addBlock(block);

		if(!isValid()) {
			removeHead();
			return false;
		}
		BlockIO.save(this);
		return true;
	}
	
	/**
	 * returns the head of this chain
	 * @return
	 */
	public Block getHead() {
		return chain.get(chain.size() - 1);
	}
	
	public Block get(int i) {
		return chain.get(i);
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