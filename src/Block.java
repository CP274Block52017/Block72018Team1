import java.util.Date;

public class Block {
	private String data;
	private String hash;
	private String previousHash;
	public static boolean testing = false;
	private long timestamp;
	private long nonce;
	public Block(String data, String previousHash) {
		this.data = data;
		this.hash = generateHash();
		this.previousHash = previousHash;
		
		timestamp = new Date().getTime();
		
		nonce = 0;
	}
	
	/**
	 * generates and returns a SHA256 hash that is unique to this block
	 * @return this block's hash
	 */
	public String generateHash() {
		hash = new SHA256Hasher().hash(previousHash + nonce + data + timestamp);
		return hash;
	}

	/**
	 * verifies if a block passes proof of work by counting digits of 0's
	 * @param zeroCount
	 * @return true if the block is verified to pass the proof of work
	 */
	public boolean passesProofOfWork(int zeroCount) {
		if(!testing)
			generateHash();
		for(int i = 0; i < zeroCount; i++) {
			if(hash.charAt(i) != '0') {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * getter for the data stored in this block
	 * @return the data stored in this block
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * getter for the hash of the previous block in this block's chain
	 * @return the hash of the previous block in this block's chain
	 */
	public String getPreviousHash() {
		return previousHash;
	}
	
	public String getHash()
	{
		return hash;
	}
	
	/**
	 * setter method for testing the validity of the passesProofOfWork()
	 * @param hash
	 */
	public void setHash(String hash)
	{
		this.hash = hash;
	}
	
	/**
	 * a function to slightly chance the state of this block
	 * since hashing functions generate very different hashes with
	 * similar but not identical data, this slight change will result
	 * in a very different hash when the generateHash function is run
	 */
	public void nextState() {
		nonce++;
	}
}