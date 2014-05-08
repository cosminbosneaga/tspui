package test;

import static org.junit.Assert.*;

import org.junit.Test;

import tsp.Instance;
import tsp.Node;

public class TestInstance {

	@Test
	public final void testAddNode() {
		Node n = new Node();
		Instance i = new Instance();
		i.addNode(n);
		assertEquals(1,i.size());
	}

	@Test
	public final void testFindNode() {
		Node n = new Node();
		Instance i = new Instance();
		i.addNode(n);
		
		Node found = new Node();
		found = null;
		found = i.findNode(0);
		assertTrue(found!=null);
	}

	@Test
	public final void testRemoveAll() {
		Node n = new Node();
		Instance i = new Instance();
		i.addNode(n);
		i.removeAll();
		assertEquals(0,i.size());
	}

	@Test
	public final void testSize() {
		Node n = new Node();
		Instance i = new Instance();
		i.addNode(n);
		
		assertEquals(1,i.size());
	}

	@Test
	public final void testEdge() {
		Node n1 = new Node(10,20);
		Node n2 = new Node(10,20);
		Instance i = new Instance();
		i.addNode(n1);
		i.addNode(n2);
		
		double cost = i.edge(0,1);
		assertEquals(0,cost,0.01);
	}

}
