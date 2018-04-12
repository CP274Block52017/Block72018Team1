import java.util.ArrayList;

public class BlockChain {
	private ArrayList<Block> chain;
	
	public BlockChain() {
		chain = new ArrayList<Block>();
	}
	
	public boolean isValid() {
		Block currentBlock;
		Block previousBlock;
		
		for(int i = 1; i < chain.size(); i++) {
			currentBlock = chain.get(i);
			previousBlock = chain.get(i - 1);
			if(previousBlock.generateHash() != currentBlock.getPreviousHash()) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean hashPassesProofOfWork(String hash) {
		for(int i = 0; i < 30; i++) {
			if(hash.charAt(i) != '0') {
				return false;
			}
		}
		
		return true;
	}
	
	private void addBlock(Block block) {
		chain.add(block);
	}
	
	public boolean tryNewBlock(Block block) {
		if(!hashPassesProofOfWork(block.generateHash())) {
			return false;
		}
		
		addBlock(block);
		
		return true;
	}
	
	public Block getHead() {
		return chain.get(chain.size() - 1);
	}
}