package tsp;

import java.util.ArrayList;

public class Tour {
	
	private static ArrayList<Integer> nnTour = new ArrayList<Integer>();
	
	public static void addNode(Integer n) {
        getTour().add(n);
    }
	
	public static boolean exists(Integer n){
		for(int i = 0; i<size();i++){
    		if(getTour().get(i) == n){
    			return false;
    		}
    	}
		return true;
	}
    
    public static int size(){
        return getTour().size();
    }
    
    public static double tourDistance(ArrayList<Integer> tour){
    	
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

	/**
	 * @return the tour
	 */
	public static ArrayList<Integer> getTour() {
		return nnTour;
	}

	/**
	 * @param tour the tour to set
	 */
	public static void setTour(ArrayList<Integer> tour) {
		Tour.nnTour = tour;
	}
}
