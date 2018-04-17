
public class GUIFunctions {
_
  private ApplicationUI frame;

  public GUIFunctions(ApplicationUI _frame){
    frame = _frame;
  }

	public static void join() {
		//switch to the UserIDUI state

	}

	public static void start(int[] keyPair, ApplicationUI frame) {
		Node localNode = new Node(keyPair[0], keyPair[1]);
		localNode.addToNetwork(Runner.GLOBAL_NETWORK);
		frame.initializeLocalNode(localNode);
		frame.setState(new Dashboard_fx());
	}

	public static void mine() {
    Node localNode = new Node(keyPair[0], keyPair[1]);
    localNode.addToNetWork(Runner.GLOBAL_NETWORK);
    localNode.startWork();
    frame.initializeLocalNode(localNode);
	}

	public static void send(int senderKey, int receiverKey, double amount) {
    frame.getLocalNode().pushTransaction(new Transaction(senderKey, receiverKey, amount));
    updateBalance(frame.getLocalNode().getBalance());
	}


}
