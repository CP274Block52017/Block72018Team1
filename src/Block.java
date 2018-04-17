import java.util.Date;
import java.util.ArrayList;

public class Block {
	//mining rewards and similar transactions come from or go to the imaginary
	// user represented by this key
	public static int GENERATOR_KEY = -1;
	
	private ArrayList<Transaction> transactions;
	private String hash;
	private String previousHash;
	
	private long timestamp;
	
	private long nonce;
	
	public Block(String previousHash, Transaction...transactionsVararg) {
		transactions = new ArrayList<Transaction>();
		for(Transaction transaction : transactionsVararg) {
			transactions.add(transaction);
		}
		
		this.hash = generateHash();
		this.previousHash = previousHash;
		
		timestamp = new Date().getTime();
		
		nonce = 0;
	}
	
	/**
	 * generates and returns a SHA256 hash that is unique to this block
	 * @return this block's hash
	 */
	public String generateHash() {
		String transactionsString = "";
		for(Transaction transaction : transactions) {
			transactionsString += transaction.getSenderKey() + "|" + transaction.getRecieverKey() + "|" + transaction.getAmount() + "\n";
		}
		hash = new SHA256Hasher().hash(previousHash + nonce + transactionsString + timestamp);
		return hash;
	}

	/**
	 * 
	 * @param zeroCount
	 * @return
	 */
	public boolean passesProofOfWork(int zeroCount) {
		generateHash();
		for(int i = 0; i < zeroCount; i++) {
			if(hash.charAt(i) != '0') {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * adds a new tranaction to this block's storage
	 * @param transaction
	 */
	public void processNewTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
	
	/**
	 * getter for the hash of the previous block in this block's chain
	 * @return the hash of the previous block in this block's chain
	 */
	public String getPreviousHash() {
		return previousHash;
	}

	/**
	 * gets every transaction stored by this block
	 * @return
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	
	
	/**
	 * a function to slightly chance the state of this block
	 * since hashing functions generate very different hashes with
	 * similar but not identical data, this slight change will result
	 * in a very different hash when the generateHash function is run
	 */
	public void nextState() {
		nonce++;
	}
	
	/**
	 * returns a genesis block, which has no transactions and no previous key
	 * this is usually the first block in a chain
	 * @return a genesis block
	 */
	public static Block createGenesisBlock() {
		Block genesis = new Block("");
		genesis.timestamp = 0;
		return genesis;
	}
}