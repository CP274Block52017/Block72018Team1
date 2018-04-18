/**
 * a class that holds all information necessary for a transaction
 * @author Case Regan
 *
 */
public class Transaction {
	private int senderKey;
	private int receiverKey;
	private int signature;
	private int n;
	private double amount;
	private int id;
	// signature?
	
	public Transaction(int senderKey, int recieverKey, int n, int id, double amount) {
		this.senderKey = senderKey;
		this.receiverKey = recieverKey;
		this.signature = signature;
		this.n= n;
		this.id=id;
		this.amount = amount;
	}
	
	public Transaction(String data) {
		String[] datas = data.split("\\$");
		senderKey = Integer.parseInt(datas[0]);
		receiverKey = Integer.parseInt(datas[1]);
		signature = Integer.parseInt(datas[2]);
		n = Integer.parseInt(datas[3]);
		amount = Double.parseDouble(datas[4]);
		id = Integer.parseInt(datas[5]);

	}
	
	public int message()
	{
		return senderKey+receiverKey+n+(int)amount;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSenderKey(int senderKey) {
		this.senderKey = senderKey;
	}

	public void setRecieverKey(int recieverKey) {
		this.receiverKey = recieverKey;
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
	
	public int getReceiverKey() {
		return receiverKey;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public String saveString() {
		String data = senderKey + "$" + receiverKey + "$" + signature + "$" + n + "$" + amount + "$" + id;
		return data;
	}
	
	/**
	 * explains what this transaction is with a String
	 */
	public String toString() {
		if(senderKey == Block.GENERATOR_KEY) {
			return receiverKey + " recieved " + amount + " from mining rewards";
		}
		return "Sender " + senderKey + " sent " + amount + " to " + receiverKey;
	}
}