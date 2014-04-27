package tsp;

import java.util.ArrayList;

public class Instance {
	
	private ArrayList<Node> nodes = new ArrayList<Node>();
		
	public void addNode(Node n) {
        nodes.add(n);
    }
    
    public Node findNode(int index){
        return nodes.get(index);
    }
    
    public void removeAll(){
    	nodes = new ArrayList<Node>();
    }
    
    public int size(){
        return nodes.size();
    }
    
    public double edge(int node1, int node2){
    	Node startNode = findNode(node1);
    	Node endNode = findNode(node2);
    	double dx = startNode.getX() - endNode.getX();
    	double dy = startNode.getY() - endNode.getY();
    	
    	return Math.sqrt(dx*dx+dy*dy);
    }

}
