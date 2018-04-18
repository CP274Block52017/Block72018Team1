import static org.junit.Assert.*;
import org.junit.*;

public class IDGeneratorTests {
	
	// makes sure that ids are generated randomly
	@Test
	public void testIDGenerator() {
		assertFalse(IDGenerator.generateRandomID().equals(IDGenerator.generateRandomID()));
	}
}