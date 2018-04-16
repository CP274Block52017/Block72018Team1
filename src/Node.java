/**
 * A node that belongs to a network
 * @author Case Regan
 *
 */
public class Node implements Runnable {
	private String data;
	
	private BlockChain localChain;
	private Block workingBlock;
	private boolean working;
	
	private int publicKey;
	private int secretKey;
	
	private Network network;
	
	public Node(int publicKey, int secretKey) {
		data = "";
		
		working = false;
		localChain = new BlockChain();
		
		this.publicKey = publicKey;
		this.secretKey = secretKey;
		
		setupWorkingBlock();
		new Thread(this).start();
	}
	
	/**
	 * nodes can either be mining or not mining (represented by the boolean "working")
	 * this function runs in the background and ensures that nodes are always ready to
	 * start mining
	 */
	public void run() {
		while(true) {
			if(working) {
				System.out.println(this + " is working...");
				working = !work();
			}
		}
	}

	/**
	 * resets the block that this node is trying to complete a proof of work for
	 */
	private void setupWorkingBlock() {
		workingBlock = new Block(data, localChain.getHead().generateHash());
	}
	
	/**
	 * sets up this node's connection to a network
	 * @param network
	 */
	public void addToNetwork(Network network) {
		this.network = network;
		this.network.addNode(this);
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
	public int getSecretKey() {
		return secretKey;
	}
	
	/**
	 * a getter method for this node's data
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * stores new data in this node
	 * @param datum
	 */
	public void processNewData(String datum) {
		data += "\n" + datum;
		setupWorkingBlock();
	}
	
	/**
	 * processes a new block and determines whether or not it should be added to this node's chain
	 * @param block
	 */
	public void processNewBlock(Block block) {
		if(localChain.processNewBlock(block)) {
			System.out.println(this + " has accepted a new block!");
			setupWorkingBlock();
		} else {
			System.out.println(this + " has rejected a new block.");
		}
	}
	
	/**
	 * starts or resumes mining
	 */
	public void startWork() {
		working = true;
	}
	
	/**
	 * stop or pause mining
	 */
	public void interruptWork() {
		working = false;
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
}