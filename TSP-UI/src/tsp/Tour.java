package tsp;

import java.util.ArrayList;

public class Tour {
	
	private static ArrayList<Integer> tour = new ArrayList<Integer>();
	
	public static void addNode(Integer n) {
        tour.add(n);
    }
	
	public static boolean exists(Integer n){
		for(int i = 0; i<size();i++){
    		if(tour.get(i) == n){
    			return false;
    		}
    	}
		return true;
	}
    
    public static int size(){
        return tour.size();
    }
    
    public static double printTour(){
    	
    	//System.out.print("The tour is: ");
    	double total=0;
    	for(int i = 0; i<size();i++){
    		if( i+1 == size() ){
    			total += Node.edge(NodeList.findNode(tour.get(i)), NodeList.findNode(tour.get(0)));
    		}
    		else{
    			total += Node.edge(NodeList.findNode(tour.get(i)), NodeList.findNode(tour.get(i+1)));
    		}
    		//System.out.print(tour.get(i)+"->");
    	}
    	//System.out.println("Total: "+total);
    	return total; 
    }
}
