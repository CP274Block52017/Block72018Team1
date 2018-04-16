import java.util.ArrayList;

public class LocalNetwork implements Network {
	private ArrayList<Node> nodes;
	
	public LocalNetwork() {
		nodes = new ArrayList<Node>();
	}
	
	/**
	 * This method returns the number of 0's required
	 * @return currently one 0 is required
	 */
	public int getRequiredZeros() {
		return 1;
	}
	/**
	 * This method add nodes to the local network
	 * @param node to add
	 */
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	/**
	 * This method gets every node in the local network process transaction info
	 */
	public void broadcastNewTransaction(String transaction) {
		for(Node node : nodes) {
			node.processNewData(transaction);
		}
	}
	
	/**
	 * This method gets every node in the local network process a new block
	 */
	public void broadcastNewBlock(Block block) {
		System.out.println(this + "is sending a block to all nodes...");
		for(Node node : nodes) {
			node.processNewBlock(block);
		}
	}
	
	public ArrayList<Node> getNodes(){
		return nodes;
	}
}