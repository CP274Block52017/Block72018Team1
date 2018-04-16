
public class TestRun {

	public static void main(String[] args) {
		NumberMagic nm = new NumberMagic();
		for(int i = 0; i < 3; i++) {
			System.out.println(nm.generateKeyPair()[i]);
		}		
	}

}
