import java.util.ArrayList;

/**
 * A node that belongs to a network
 * @author Case Regan
 *
 */
public class Node {
	private BlockChain localChain;
	private Block workingBlock;
	
	private int publicKey;
	private int privateKey;
	
	private Network network;
	
	public Node(int publicKey, int secretKey) {
		localChain = new BlockChain();
		
		this.publicKey = publicKey;
		this.privateKey = secretKey;
		
		resetWorkingBlock();
	}

	/**
	 * resets the block that this node is trying to complete a proof of work for
	 */
	private void resetWorkingBlock() {
		workingBlock = new Block(localChain.getHead().generateHash(), new Transaction(Block.GENERATOR_KEY, publicKey, 0, 0, 1.0));
	}
	
	/**
	 * sets up this node's connection to a network
	 * @param network
	 */
	public void addToNetwork(Network network) {
		this.network = network;
		this.network.addNode(this);
		System.out.println(this + " added to " + network);
	}
	
	/**
	 * a getter method for this node's public key
	 * @return the public key
	 */
	public int getPublicKey() {
		return publicKey;
	}
	
	/**
	 * a getter method for this node's private key
	 * @return the private key
	 */
	public int getPrivateKey() {
		return privateKey;
	}
	
	public BlockChain getLocalChain() {
		return localChain;
	}
	
	public double getBalance() {
		double balance = 0.0;
		ArrayList<Transaction> transactions = localChain.getAllTransactions();
	
		for(Transaction transaction : transactions) {
			if(transaction.getRecieverKey() == publicKey) {
				balance += transaction.getAmount();
			} else if(transaction.getSenderKey() == publicKey) {
				balance -= transaction.getAmount();
			}
		}
		
		return balance;
	}
	
	/**
	 * stores new data in this node
	 * @param datum
	 */
	public void processNewTransaction(Transaction transaction) {
		workingBlock.processNewTransaction(transaction);
		System.out.println("the following transaction was processed: " + transaction);
	}
	
	/**
	 * processes a new block and determines whether or not it should be added to this node's chain
	 * @param block
	 */
	public void processNewBlock(Block block) {
		if(localChain.processNewBlock(block)) {
			System.out.println(this + " has accepted a new block! Their chain now has a length of " + localChain.length());
			System.out.println(block.getLatestTransaction().toString());
			Database.addTransaction(publicKey,block.getLatestTransaction().toString());
			resetWorkingBlock();
			System.out.println(this + " has a balance of " + getBalance());
			Database.addBalance(getPublicKey(),getBalance());
		} else {
			System.out.println(this + " has rejected a new block.");
		}
	}
	
	/**
	 * tests the current block to see if it passes the proof of work algorithm
	 * if it doesn't, change the state of the block slightly so that it it might
	 * if it does, push the block to the network
	 * @return whether the working block passed
	 */
	public boolean work() {
		if(workingBlock.passesProofOfWork(network.getRequiredZeros())) {
			System.out.println(this + " has completed work!");
			pushWorkingBlock();
			
			return true;
		} 

		workingBlock.nextState();
		return false;
	}
	
	/**
	 * pushes the working block to the network
	 */
	public void pushWorkingBlock() {
		System.out.println(this + " is pushing a block to the network...");
		network.broadcastNewBlock(workingBlock);
		
	}
	
	/**
	 * broadcasts a transaction to the network
	 * @param transaction
	 */
	public void pushTransaction(Transaction transaction) {
		network.broadcastNewTransaction(transaction);
	}
}