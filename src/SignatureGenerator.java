import java.io.*;


public class SignatureGenerator {
	
	private int publicKey;
	private int privateKey;
	private int n_value;
	private int message;
	private FileReader filereader;
	private BufferedReader buffered;
	
	private final static String FILENAME = "SPAMCOIN.wlt";
	
	public SignatureGenerator(int _message) {
		

		message = _message;
		try {
			filereader = new FileReader(FILENAME);
			buffered = new BufferedReader(filereader);
			publicKey = Integer.parseInt(buffered.readLine());
			privateKey = Integer.parseInt(buffered.readLine());
			n_value = Integer.parseInt(buffered.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	  /**
	   * Produces the signature according to the keyPair and messages
	   * @param keyPair the keyPair generated by generateKeyPair
	   * @param message the message to be sent
	   * @return the signature.
	   */
	  public int signature() {
		  int cipherText = (int) (Math.pow(message, publicKey) % n_value);

		  return ((int)(Math.pow(cipherText, privateKey) % n_value));
	  }

}
