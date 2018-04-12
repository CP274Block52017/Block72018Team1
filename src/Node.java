public class Node implements Runnable {
	private String data;
	
	private BlockChain localChain;
	private Block workingBlock;
	private boolean working;
	
	public Node() {
		working = false;
		new Thread(this).start();
	}
	
	public void run() {
		while(working) {
			working = !work();
		}
	}
	
	public void setupWorkingBlock() {
		workingBlock = new Block(data, localChain.getHead().generateHash());
	}
	
	public void addData(String datum) {
		data += "\n" + datum;
	}
	
	public void startWork() {
		setupWorkingBlock();
		working = true;
	}
	
	public void interruptWork() {
		working = false;
	}
	
	public boolean work() {
		if(localChain.tryNewBlock(workingBlock)) {
			return true;
		} 

		workingBlock.nextState();
		return false;
	}
}