import java.io.*;

public class BlockIO {
	
	BlockChain chain;
	
	private final static String FILENAME = "SPAMCOIN.chain";
	
	public static void save(BlockChain chain) {
		try(PrintWriter out = new PrintWriter(BlockIO.FILENAME);){
			for(int i = 0; i < chain.length(); i++) {
				out.println(chain.get(i));
			}
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public static BlockChain load() {
		BlockChain loaded = new BlockChain();
		
		try {
			FileReader filereader = new FileReader(BlockIO.FILENAME);
			BufferedReader buffered = new BufferedReader(filereader);
			String blockString = buffered.readLine();
			String[] genesisBlock = blockString.split("\\|");
						
			
			blockString = buffered.readLine();
			int counter = 1;
			while(blockString != null) {
				loaded.addBlock(new Block(blockString, false));
				blockString = buffered.readLine();
				counter++;
			}
			loaded.removeHead();
		}catch(IOException e) {
			
		}
		
		return loaded;
	}

}
