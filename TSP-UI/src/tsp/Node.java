/**  
 * This class holds a node object with x, y coordinate
 * @author Cosmin
 */
package tsp;

public class Node {
	
	private double x;
    private double y;
    
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
    
    // set x coordinate
    public void setX(double x){
    	this.x = x;
    }
    
    // get y coordinate
    public double getY(){
        return this.y;
    }
    
    // set y coordinate
    public void setY(double y){
    	this.y = y;
    }
    
}
