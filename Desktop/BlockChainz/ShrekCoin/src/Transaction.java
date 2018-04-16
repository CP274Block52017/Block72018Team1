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
		
	public String toString() {
		return "Sender " + senderKey + " sent " + amount + " to " + recieverKey;
	}
}