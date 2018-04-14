import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestLocalNetwork {

	private LocalNetwork testNetwork;
	private Node nodeOne;
	private Node nodeTwo;
	private ArrayList<Node> nodes;
	
	@Before
	public void setUp() throws Exception {
		testNetwork = new LocalNetwork();
		nodeOne = new Node(0,0);
		nodeTwo = new Node(100,1000);
	}

	@Test
	public void testGetRequiredZeros() {
		assertEquals(1,testNetwork.getRequiredZeros());
	}
	
	@Test
	public void testGetEmptyNodes() {
		nodes = testNetwork.getNodes();
		assertEquals(0,nodes.size());
	}
	
	@Test
	public void testGetNodes() {
		testNetwork.addNode(nodeOne);
		testNetwork.addNode(nodeTwo);
		nodes = testNetwork.getNodes();
		assertEquals(2,nodes.size());
	}
	
	@Test
	public void testAddNodes() {
		testNetwork.addNode(nodeOne);
		testNetwork.addNode(nodeTwo);
		nodes = testNetwork.getNodes();
		assertEquals(nodeOne,nodes.get(0));
		assertEquals(nodeTwo,nodes.get(1));
	}
	
	@Test
	public void testBroadcastData() {
		testNetwork.addNode(nodeOne);
		testNetwork.addNode(nodeTwo);
		String transactionInfo = "First transaction added!";
		testNetwork.broadcastNewTransaction(transactionInfo);
		nodes = testNetwork.getNodes();
		assertEquals(transactionInfo,nodes.get(0).getData().substring(1,transactionInfo.length()+1));
		assertEquals(transactionInfo,nodes.get(1).getData().substring(1,transactionInfo.length()+1));  
	}

}
