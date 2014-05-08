package test;

import static org.junit.Assert.*;

import org.junit.Test;

import tsp.DistanceMatrix;
import tsp.Instance;
import tsp.Node;

public class TestDistanceMatrix {

	@Test
	public final void testCreateMatrix() {
		Node n1 = new Node(10,20);
		Node n2 = new Node(20,20);
		Node n3 = new Node(20,30);
		Node n4 = new Node(10,30);
		Instance ins = new Instance();
		ins.addNode(n1);ins.addNode(n2);ins.addNode(n3);ins.addNode(n4);
		
		double[][] expected = new double[4][4];
		expected[0][0] = 0.00;
		expected[0][1] = 10.00;
		expected[0][2] = 14.14;
		expected[0][3] = 10.00;
		
		expected[1][0] = 10.00;
		expected[1][1] = 0.00;
		expected[1][2] = 10.00;
		expected[1][3] = 14.14;
		
		expected[2][0] = 14.14;
		expected[2][1] = 10.00;
		expected[2][2] = 0.00;
		expected[2][3] = 10.00;
		
		expected[3][0] = 10.00;
		expected[3][1] = 14.14;
		expected[3][2] = 10.00;
		expected[3][3] = 0.00;
		
		DistanceMatrix dm = new DistanceMatrix();
		dm.createMatrix(ins);
		
		double[][] result;
		result = dm.getMatrix();
		
		for( int i=0;i<expected.length;i++){
			for( int j=0;j<expected.length;j++){
				assertEquals(expected[i][j],result[i][j],0.01);
			}
		}
	}

}
