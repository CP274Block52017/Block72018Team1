import java.util.ArrayList;

public class LocalNetwork implements Network {
	private ArrayList<Node> nodes;
	
	public LocalNetwork() {
		nodes = new ArrayList<Node>();
	}
	
	public int getRequiredZeros() {
		return 1;
	}
	
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	public void broadcastNewTransaction(String transaction) {
		for(Node node : nodes) {
			node.processNewData(transaction);
		}
	}
	
	public void broadcastNewBlock(Block block) {
		System.out.println(this + "is sending a block to all nodes...");
		for(Node node : nodes) {
			node.processNewBlock(block);
		}
	}
}