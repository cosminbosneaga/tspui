package tsp;

import java.util.ArrayList;
import java.util.Collections;

public class Tour {
	
	private ArrayList<Integer> tour = new ArrayList<Integer>();
	
	public Tour(){
		
	}
	
	public Tour(ArrayList<Integer> newTour){
		this.tour = new ArrayList<Integer>(newTour);
	}
	
	public ArrayList<Integer> getTour() {
		return tour;
	}

	public void setTour(ArrayList<Integer> tour) {
		this.tour = tour;
	}

	public void addNode(Integer n) {
        tour.add(n);
    }
	
	// return the node that is on position i in array
	public Integer getNode(Integer i){
		return tour.get(i);
	}
	
	public boolean exists(Integer n){
		for(int i = 0; i<size();i++){
    		if(tour.get(i) == n){
    			return true;
    		}
    	}
		return false;
	}
    
    public int size(){
        return tour.size();
    }
    
    public double tourTotal(Instance tsp){
    	
    	double total=0;
    	for(int i = 0; i<size()-1;i++){
    		total += tsp.edge(tour.get(i), tour.get(i+1));
    		
    	}
    		
    	//System.out.println("Total: "+total);
    	return total; 
    }
    
    public void interchange(int i, int j){
    
    	ArrayList<Integer> first = new ArrayList<Integer>(tour.subList(0, i));
    	ArrayList<Integer> middle = new ArrayList<Integer>(tour.subList(i, j+1));
    	ArrayList<Integer> end = new ArrayList<Integer>(tour.subList(j+1, tour.size()));
    	Collections.reverse(middle);
    	tour = new ArrayList<Integer>();
    	tour.addAll(first); //System.out.println("first" + first.toString());
    	tour.addAll(middle); //System.out.println("mid" + middle.toString());
    	tour.addAll(end);//System.out.println("end" + end.toString());
    	
    	/*
		int temp = tour.get(i);
		tour.set(i, tour.get(j));
		tour.set(j, temp);*/
	}

}
