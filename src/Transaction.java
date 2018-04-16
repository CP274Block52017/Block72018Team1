import java.util.regex.*;
import java.util.ArrayList;

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
	
	// subject to update
	/**
	 * transactionStrings are parsed from Blocks and are formatted like this:
	 * senderKey|recieverKey|amount 
	 * @param transactionString
	 */
	public Transaction(String transactionString) {
		// parse transaction from String stored in Block's data
		processTransactionString(transactionString);
	}
	
	private void processTransactionString(String transactionString) {
		String patternString = "\\d*[.]?\\d+"; 
		
		Pattern pattern = Pattern.compile(patternString);
		
		ArrayList<String> matches = new ArrayList<String>();
		Matcher matcher = pattern.matcher(transactionString);
		
		while(matcher.find()) {
			matches.add(matcher.group());
		}
		
		if(matches.size() < 3) {
			return;
		}
		
		senderKey = Integer.parseInt(matches.get(0));
		recieverKey = Integer.parseInt(matches.get(1));
		amount = Double.parseDouble(matches.get(2));
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
		return "Sender " + senderKey + " sent " + amount + " to " + recieverKey;
	}
}