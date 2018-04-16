/**
 * no computation should take place in the network — it should all be done by the nodes
 * however, the network should help the nodes communicate
 * @author Case Regan
 *
 */
public interface Network {
	/**
	 * gets the longest valid chain that nodes are using
	 * @return
	 */
	public BlockChain getBestChain();
	
	/**
	 * gets the number of zeros a hash needs in front of it to pass the proof of work algorithm
	 * @return the number of zeros
	 */
	public int getRequiredZeros();
	
	/**
	 * adds a node to the network
	 * @param node
	 */
	public void addNode(Node node);
	
	/**
	 * broadcasts a block sent by one node to the rest of the nodes
	 * @param block
	 */
	public void broadcastNewBlock(Block block);
	
	/**
	 * broadcasts a transaction sent by one node to the rest of the nodes
	 * @param transactionString
	 */
	public void broadcastNewTransaction(Transaction transaction);
}