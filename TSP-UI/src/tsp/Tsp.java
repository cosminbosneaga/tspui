package tsp;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Tsp {

	public static void main(String[] args) {
		/*
		Node n = new Node(47,15);
		NodeList.addNode(n);
		
		Node n1 = new Node(35,26);
		NodeList.addNode(n1);
		
		Node n2 = new Node(12,30);
		NodeList.addNode(n2);
		
		Node n3 = new Node(1,60);
		NodeList.addNode(n3);
		
		Node n4 = new Node(15,68);
		NodeList.addNode(n4);
		
		Node n5 = new Node(56,55);
		NodeList.addNode(n5);
		
		Node n6 = new Node(94,80);
		NodeList.addNode(n6);
		*/
		
		/*
		Node city = new Node(60, 200);
        NodeList.addNode(city);
        Node city2 = new Node(180, 200);
        NodeList.addNode(city2);
        Node city3 = new Node(80, 180);
        NodeList.addNode(city3);
        Node city4 = new Node(140, 180);
        NodeList.addNode(city4);
        Node city5 = new Node(20, 160);
        NodeList.addNode(city5);
        Node city6 = new Node(100, 160);
        NodeList.addNode(city6);
        Node city7 = new Node(200, 160);
        NodeList.addNode(city7);
        Node city8 = new Node(140, 140);
        NodeList.addNode(city8);
        Node city9 = new Node(40, 120);
        NodeList.addNode(city9);
        Node city10 = new Node(100, 120);
        NodeList.addNode(city10);
        Node city11 = new Node(180, 100);
        NodeList.addNode(city11);
        Node city12 = new Node(60, 80);
        NodeList.addNode(city12);
        Node city13 = new Node(120, 80);
        NodeList.addNode(city13);
        Node city14 = new Node(180, 60);
        NodeList.addNode(city14);
        Node city15 = new Node(20, 40);
        NodeList.addNode(city15);
        Node city16 = new Node(100, 40);
        NodeList.addNode(city16);
        Node city17 = new Node(200, 40);
        NodeList.addNode(city17);
        Node city18 = new Node(20, 20);
        NodeList.addNode(city18);
        Node city19 = new Node(60, 20);
        NodeList.addNode(city19);
        Node city20 = new Node(160, 20);
        NodeList.addNode(city20);
		*/
		
		Genetic.evolutionary(5,20);
		//Dynamic.findPath();
	}
}
