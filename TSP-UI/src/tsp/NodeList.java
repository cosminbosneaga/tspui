package tsp;

import java.util.ArrayList;

public class NodeList {
	
	private static ArrayList<Node> nodes = new ArrayList<Node>();
	private static String user;
		
	public static void addNode(Node n) {
        nodes.add(n);
    }
    
    public static Node findNode(int index){
        return nodes.get(index);
    }
    
    public static void removeAll(){
    	nodes = new ArrayList<Node>();
    }
    
    public static int size(){
        return nodes.size();
    }

	/**
	 * @return the user
	 */
	public static String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public static void setUser(String user) {
		NodeList.user = user;
	}

}
