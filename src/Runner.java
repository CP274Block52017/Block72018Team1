
public class Runner {
	public static LocalNetwork GLOBAL_NETWORK = new LocalNetwork();
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			new Node(i, i).addToNetwork(Runner.GLOBAL_NETWORK);
		}
		
		Database.createDBAndUse();
		new UIFrame();  
		
	}
}
