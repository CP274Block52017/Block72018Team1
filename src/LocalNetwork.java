import java.util.ArrayList;

/**
 * an implementation of Network that runs locally for easy testing
 * @author Case Regan
 *
 */
public class LocalNetwork implements Network {
	private ArrayList<Node> nodes;
	
	// TODO implement
	public BlockChain getBestChain() {
		return null;
	}
	
	public LocalNetwork() {
		nodes = new ArrayList<Node>();
	}
	
	/**
	 * get requied zeros for hashing
	 */
	public int getRequiredZeros() {
		return 1;
	}
	
	/**
	 * adds node to the local network
	 */
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	/**
	 * broadcasts a transaction among all the nodes
	 * @param transaction object
	 */
	public void broadcastNewTransaction(Transaction transaction) {
		for(Node node : nodes) {
			node.processNewTransaction(transaction);
		}
	}
	
	/**
	 * broadcasts a block sent by one node to the rest of the nodes
	 * @param block
	 */
	public void broadcastNewBlock(Block block) {
		System.out.println(this + "is sending a block to all nodes...");
		for(Node node : nodes) {
			node.processNewBlock(block);
		}
	}
}