import java.util.*;

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
		
		for(int i = 1; i < chain.size(); i++) {
			currentBlock = chain.get(i);
			previousBlock = chain.get(i - 1);
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
			
			if(keyBalancePairs.containsKey(transaction.getRecieverKey())) {
				recieverBalance = keyBalancePairs.get(transaction.getRecieverKey());
			}
			
			keyBalancePairs.put(transaction.getSenderKey(), senderBalance - transaction.getAmount());
			keyBalancePairs.put(transaction.getRecieverKey(), recieverBalance + transaction.getAmount());
		}
		
		for(Map.Entry<Integer, Double> keyBalancePair : keyBalancePairs.entrySet()) {
			if(keyBalancePair.getKey() != Block.GENERATOR_KEY && keyBalancePair.getValue() < 0) {
				return false;
			}
		}
		
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
		
		if(!isValid()) {
			removeHead();
			return false;
		}
		
		return true;
	}
	
	public Block getHead() {
		return chain.get(chain.size() - 1);
	}
	
	public ArrayList<Transaction> getAllTransactions() {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		for(Block block : chain) {
			for(Transaction transaction : block.getTransactions()) {
				transactions.add(transaction);
			}
		}
			
		return transactions;
	}
	
	public void removeHead() {
		chain.remove(chain.size() - 1);
	}
}