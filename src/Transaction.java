/**
 * a class that holds all information necessary for a transaction
 * @author Case Regan
 *
 */
public class Transaction {
	private int senderKey;
	private int recieverKey;
	private double amount;
	// signature?
	
	public Transaction(int senderKey, int recieverKey, double amount) {
		this.senderKey = senderKey;
		this.recieverKey = recieverKey;
		this.amount = amount;
	}
	
	public int getSenderKey() {
		return senderKey;
	}
	
	public int getRecieverKey() {
		return recieverKey;
	}
	
	public double getAmount() {
		return amount;
	}
	
	/**
	 * explains what this transaction is with a String
	 */
	public String toString() {
		if(senderKey == Block.GENERATOR_KEY) {
			return recieverKey + " recieved " + amount + " from mining rewards";
		}
		return "Sender " + senderKey + " sent " + amount + " to " + recieverKey;
	}
}