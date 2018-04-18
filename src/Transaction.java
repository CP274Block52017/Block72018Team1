import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * a class that holds all information necessary for a transaction
 * @author Case Regan
 *
 */
public class Transaction {
	private int senderKey;
	private int recieverKey;
	private int signature;
	private int n;
	private double amount;
	private long id;
	// signature?
	
	public Transaction(int senderKey, int recieverKey, int n, long id, double amount) {
		this.senderKey = senderKey;
		this.recieverKey = recieverKey;
		this.signature = signature;
		this.n= n;
		this.id=id;
		this.amount = amount;
	}
	
	public int message()
	{
		return senderKey+recieverKey+n+(int)amount;
	}
	
	public int getSignature() {
		return signature;
	}

	public void setSignature(int signature) {
		this.signature = signature;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSenderKey(int senderKey) {
		this.senderKey = senderKey;
	}

	public void setRecieverKey(int recieverKey) {
		this.recieverKey = recieverKey;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * getter methods for senderKey, recieverKey, and amount
	 * @return
	 */
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
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
		if(senderKey == Block.GENERATOR_KEY) {
			return recieverKey + " recieved " + amount + " from mining rewards at "+timeStamp;
		}
		return "Sender " + senderKey + " sent " + amount + " to " + recieverKey+" at "+timeStamp;
	}
}