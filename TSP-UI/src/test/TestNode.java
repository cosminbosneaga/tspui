package test;

import static org.junit.Assert.*;

import org.junit.Test;

import tsp.Node;

public class TestNode {

	@Test
	public final void testNodeDoubleDouble() {
		Node n = new Node(234.12,443.55);
		assertEquals(234.12,n.getX(),0.01);
		assertEquals(443.55,n.getY(),0.01);
	}

	@Test
	public final void testGetX() {
		Node n = new Node();
		double testVar;
		testVar = 500;
		testVar = 145.33;
		testVar = 0;
		testVar = -500;
		testVar = -342.59;
		n.setX(testVar);
		assertEquals(testVar, n.getX(), 0.01);
	}

	@Test
	public final void testGetY() {
		Node n = new Node();
		double testVar;
		testVar = 500;
		testVar = 145.33;
		testVar = 0;
		testVar = -500;
		testVar = -342.59;
		n.setY(testVar);
		assertEquals(testVar, n.getY(), 0.01);
	}

}
