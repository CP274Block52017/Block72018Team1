/**
 * Class with static method to generate random user IDs
 */

import java.util.Random;

public class IDGenerator {
	
	private static final int ID_LENGTH = 10;
	private static final String ID_ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ID_ALPHABET_LOWER = ID_ALPHABET_UPPER.toLowerCase();
	private static final String ID_NUMBER = "0123456789";
	private static final String ID_CHARS = ID_ALPHABET_UPPER + ID_ALPHABET_LOWER + ID_NUMBER;
	
	/**
	 * This method generates a random alphanumeric user id.
	 * @return user id string 
	 */
	public static String generateRandomID() {
		String userID = "";
		Random random = new Random();
		for (int i = 0; i < ID_LENGTH; i++) {
			int randomIndex = random.nextInt(ID_CHARS.length());
			userID += ID_CHARS.charAt(randomIndex);
		}
		return userID;
	}
}