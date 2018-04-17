/**
 * contains the main method, runs a simple network with 10 auto-generated nodes and one that the user can control
 * @author Case Regan
 *
 */
public class Runner {
	public static LocalNetwork GLOBAL_NETWORK = new LocalNetwork();
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			int[] keyPair = new KeyPairGenerator().generateNext();
			Node node = new Node(keyPair[0], keyPair[1]);
			node.addToNetwork(Runner.GLOBAL_NETWORK);
		}
		
		new UIFrame();
	}
}