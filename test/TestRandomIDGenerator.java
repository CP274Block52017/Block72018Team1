import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestRandomIDGenerator {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIDGenerator() {
		assertFalse(IDGenerator.generateRandomID().equals(IDGenerator.generateRandomID()));
	}

} 
