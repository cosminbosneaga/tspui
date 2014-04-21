package tsp;

public class Node {
	
	double x;
    double y;
    
    // create a random new node
    public Node(){
    	this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
    }
    
    // create a new node at location
    public Node(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    // get x coordinate
    public double getX(){
        return this.x;
    }
    
    // get y coordinate
    public double getY(){
        return this.y;
    }
    
}
