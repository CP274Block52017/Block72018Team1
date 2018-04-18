import static org.junit.Assert.*;
import org.junit.*;

public class IDGeneratorTests {
	@Test
	public void testIDGenerator() {
		assertFalse(IDGenerator.generateRandomID().equals(IDGenerator.generateRandomID()));
	}
}
