package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import tsp.Instance;
import tsp.Node;
import tsp.Tour;

public class TestTour {

	@Test
	public final void testTourArrayListOfInteger() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(4);
		Tour t = new Tour(arr);
		assertEquals(arr,t.getTour());
	}

	@Test
	public final void testGetTour() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(4);
		Tour t = new Tour(arr);
		assertEquals(arr,t.getTour());
	}

	@Test
	public final void testAddNode() {
		Tour t = new Tour();
		t.addNode(3);
		int node = t.getNode(0);
		assertEquals(3,node);
	}

	@Test
	public final void testGetNode() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(3);
		Tour t = new Tour(arr);
		assertEquals(arr.get(0),t.getNode(0));
	}

	@Test
	public final void testExists() {
		Tour t = new Tour();
		t.addNode(3);
		assertTrue(t.exists(3));
	}

	@Test
	public final void testSize() {
		Tour t = new Tour();
		t.addNode(3);
		assertEquals(1,t.size());
	}

	@Test
	public final void testTourTotal() {
		Node n1 = new Node(10,20);
		Node n2 = new Node(20,20);
		Node n3 = new Node(20,30);
		Node n4 = new Node(10,30);
		Instance i = new Instance();
		i.addNode(n1);i.addNode(n2);i.addNode(n3);i.addNode(n4);
		Tour t = new Tour();
		t.addNode(0);t.addNode(1);t.addNode(2);t.addNode(3);t.addNode(0);
		assertEquals(40,t.tourTotal(i),0.01);
	}

	@Test
	public final void testInterchange() {
		ArrayList<Integer> start = new ArrayList<Integer>();
		start.add(1);start.add(2);start.add(3);start.add(4);start.add(5);start.add(6);
		ArrayList<Integer> changed = new ArrayList<Integer>();
		changed.add(1);changed.add(5);changed.add(4);changed.add(3);changed.add(2);changed.add(6);
		Tour t = new Tour(start);
		t.interchange(1,4);
		assertEquals(changed, t.getTour());
	}

}
