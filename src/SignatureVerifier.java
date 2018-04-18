
public class SignatureVerifier {
	
	private int signature;
	private int senderPK;
	private int senderNValue;
	private int cipherText;
	
	public SignatureVerifier(int _signature, int _senderPK, int _senderNValue, int _cipherText) {
		signature = _signature;
		senderPK = _senderPK;
		senderNValue = _senderNValue;
		cipherText = _cipherText;
	}
	
	  /**
	   * Verify the keypairs and message
	   * @param keyPair the keypair that is generated by generateKeyPair
	   * @param message the message
	   * @return true if verified, false if not.
	   */
	  public boolean verify() {
		  
		  return ((int)(Math.pow(signature, senderPK) % senderNValue) == cipherText);
	  }

}