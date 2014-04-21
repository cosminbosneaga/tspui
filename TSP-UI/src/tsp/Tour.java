package tsp;

import java.util.ArrayList;

public class Tour {
	
	private ArrayList<Integer> tour = new ArrayList<Integer>();
	
	public ArrayList<Integer> getTour() {
		return tour;
	}

	public void setTour(ArrayList<Integer> tour) {
		this.tour = tour;
	}

	public void addNode(Integer n) {
        tour.add(n);
    }
	
	public Integer getNode(Integer i){
		return tour.get(i);
	}
	
	public boolean exists(Integer n){
		for(int i = 0; i<size();i++){
    		if(tour.get(i) == n){
    			return false;
    		}
    	}
		return true;
	}
    
    public int size(){
        return tour.size();
    }
    
    public double tourTotal(){
    	
    	double total=0;
    	for(int i = 0; i<size()-1;i++){
    		total += Instance.edge(tour.get(i), tour.get(i+1));
    	}
    		
    	//System.out.println("Total: "+total);
    	return total; 
    }
    
    public void interchange(int i, int j){
		int temp = tour.get(i);
		tour.set(i, tour.get(j));
		tour.set(j, temp);
	}

}
